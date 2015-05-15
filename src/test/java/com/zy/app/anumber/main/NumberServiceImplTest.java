package com.zy.app.anumber.main;

import com.zy.app.anumber.dao.ANumberDao;
import com.zy.app.anumber.model.ANumber;
import com.zy.app.anumber.model.ANumberRange;
import com.zy.app.common.main.UtilService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.zy.app.anumber.model.builder.ANumberBuilder.anANumber;
import static com.zy.app.anumber.model.builder.ANumberRangeBuilder.anANumberRange;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NumberServiceImplTest {
    private static final LocalDateTime now = LocalDateTime.now();
    public static final int NUMBER = 61660020;

    @Mock
    ANumberDao aNumberDao;
    @Mock
    UtilService utilService;

    @InjectMocks
    NumberServiceImpl numberService;

    @Before
    public void setUp() throws Exception {
        when(utilService.getCurrentDateTime()).thenReturn(now);
        when(utilService.getRandomKey()).thenReturn("test1");
    }

    @Test(expected = RuntimeException.class)
    public void testWrongNumberRange() {
        ANumberRange range = anANumberRange()
                    .withFirstNumber(123)
                    .withLastNumber(122)
                .build();
        numberService.createNumberRange(range);
    }

    @Test
    public void testOneNumberInRange() {
        ANumberRange range = anANumberRange()
                    .withFirstNumber(123)
                    .withLastNumber(123)
                .build();
        numberService.createNumberRange(range);
        verify(aNumberDao).createANumber(anANumber()
                    .withNumber(123)
                    .withStatus(ANumber.Status.OPEN)
                    .withType(ANumber.Type.NORMAL)
                    .withCreated(now)
                .build());
    }

    @Test
    public void testRangeWithFiveNumbers() {
        ANumberRange range = anANumberRange()
                    .withFirstNumber(123)
                    .withLastNumber(127)
                .build();
        numberService.createNumberRange(range);
        verify(aNumberDao, times(5)).createANumber(any(ANumber.class));
    }


    @Test
    public void testGetRandomANumber() {
        when(aNumberDao.getOpenNumbers(1000, ANumber.Type.NORMAL)).thenReturn(new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8)));
        List<Integer> numbers = numberService.getRandomANumbers(4, ANumber.Type.NORMAL);
        assertThat(numbers.size(), equalTo(4));
    }

    @Test
    public void testGetRandomANumberNotEnough() {
        when(aNumberDao.getOpenNumbers(1000, ANumber.Type.NORMAL)).thenReturn(new ArrayList(Arrays.asList(1,2,3,4,5)));
        List<Integer> numbers = numberService.getRandomANumbers(10, ANumber.Type.NORMAL);
        assertThat("5 numbers are returned", numbers.size(), equalTo(5));
    }

    @Test(expected = RuntimeException.class)
    public void testReserveNonExistentNumber() {
        when(aNumberDao.getANumber(NUMBER)).thenReturn(null);
        numberService.reserveANumber(NUMBER);
    }

    @Test(expected = RuntimeException.class)
    public void testActiveNumberCantReserve() {
        when(aNumberDao.getANumber(anyInt()))
                .thenReturn(
                        anANumber()
                            .withNumber(NUMBER)
                            .withStatus(ANumber.Status.ACTIVE)
                        .build());
        numberService.reserveANumber(NUMBER);
    }

    @Test(expected = RuntimeException.class)
    public void testReservedNumberCantReserve() {
       when(aNumberDao.getANumber(anyInt()))
               .thenReturn(
                       anANumber()
                               .withNumber(NUMBER)
                               .withStatus(ANumber.Status.OPEN)
                               .withReservedUntil(now.plusMinutes(5))
                               .build()
               );
        numberService.reserveANumber(NUMBER);
    }


    @Test
    public void testReserveANumberReturnReservationKey() {
        when(aNumberDao.getANumber(anyInt())).thenReturn(
                anANumber()
                        .withNumber(NUMBER)
                        .withStatus(ANumber.Status.OPEN)
                        .build());
        String reservationId = numberService.reserveANumber(NUMBER);
        assertThat("reservation id is not null", reservationId, notNullValue());
        assertThat("reservation id is not empty", reservationId, not(isEmptyString()));
    }

    @Test
    public void testANumberUpdated() {
        when(aNumberDao.getANumber(anyInt())).thenReturn(
                anANumber()
                        .withNumber(NUMBER)
                        .withStatus(ANumber.Status.OPEN)
                        .build());
        numberService.reserveANumber(NUMBER);
        //reserved for 15 minutes with random reservation id
        verify(aNumberDao).updateANumber(
                anANumber()
                        .withNumber(NUMBER)
                        .withStatus(ANumber.Status.OPEN)
                        .withReservedUntil(now.plusMinutes(15))
                        .withReservationId("test1")
                        .build());
    }

}
package com.zy.app.fee.main;

import com.zy.app.common.main.UtilService;
import com.zy.app.common.model.ChargeLine;
import com.zy.app.fee.dao.FeeDao;
import com.zy.app.fee.dao.RunningFeeDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.zy.app.fee.main.FeeServiceTestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FeeServiceImplTest {
    
    @Mock
    FeeDao feeDao;
    @Mock
    RunningFeeDao runningFeeDao;
    @Mock
    UtilService utilService;

    @InjectMocks
    FeeServiceImpl feeService;

    @Before
    public void setUp() throws Exception {
        when(utilService.getCurrentDateTime()).thenReturn(CHARGEDATE);
        when(utilService.getCurrentDate()).thenReturn(FEEDATE);
    }

    @Test
    public void testChargeLineIsCorrect() {
        //given
        when(feeDao.findFeeByCode("fee1")).thenReturn(prepaidFee());

        //verify
        ChargeLine line = feeService.chargeFee(savedRunningFee());
        assertThat("charge line is created as expected", line, equalTo(newFeeChargeLine()));
    }

    @Test
    public void testRunningFeeIsUpdated() {
        //given
        when(feeDao.findFeeByCode("fee1")).thenReturn(prepaidFee());

        //verify
        feeService.chargeFee(savedRunningFee());
        verify(runningFeeDao).updateRunningFee(updatedRunningFee());
    }

    @Test
    public void testOnceFeeIsDeactivatedAfterCharge() {
        when(feeDao.findFeeByCode("fee1")).thenReturn(onceFee());

        //verify ONCE fee is charged and updated correctly
        ChargeLine line = feeService.chargeFee(savedRunningFee());
        assertThat(line, equalTo(newFeeChargeLine()));
        verify(runningFeeDao).updateRunningFee(terminatedRunningFeeOnce());
    }
}
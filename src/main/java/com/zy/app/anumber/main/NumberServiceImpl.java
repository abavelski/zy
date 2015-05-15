package com.zy.app.anumber.main;

import com.zy.app.anumber.dao.ANumberDao;
import com.zy.app.anumber.model.ANumber;
import com.zy.app.anumber.model.ANumberRange;
import com.zy.app.common.main.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.zy.app.anumber.model.builder.ANumberBuilder.anANumber;

/**
 * aba
 * 15/03/15
 */
@Component
public class NumberServiceImpl implements NumberService {

    @Autowired
    ANumberDao aNumberDao;
    @Autowired
    UtilService utilService;

    @Override
    @Transactional
    public void createNumberRange(ANumberRange range) {
        if (range.getFirstNumber()>range.getLastNumber()) {
            throw new RuntimeException("wrong range");
        }
        for (int number=range.getFirstNumber();number<=range.getLastNumber(); number++) {
            aNumberDao.createANumber(
                anANumber()
                    .withNumber(number)
                    .withStatus(ANumber.Status.OPEN)
                    .withType(ANumber.Type.NORMAL)
                    .withCreated(utilService.getCurrentDateTime())
                .build());
        }

    }

    @Override
    public List<Integer> getRandomANumbers(int nr, ANumber.Type type) {
        List<Integer> numbers = aNumberDao.getOpenNumbers(1000, ANumber.Type.NORMAL);
        List<Integer> result = new ArrayList<>();

        while (result.size()<nr && numbers.size()>0) {
            Random random = new Random();
            int i = random.nextInt(numbers.size());
            result.add(numbers.remove(i));
        }
        return result;
    }

    @Override
    public String reserveANumber(Integer number) {
        String reservationId = utilService.getRandomKey();
        ANumber aNumber = aNumberDao.getANumber(number);
        if (aNumber==null) {
            throw new RuntimeException("number doesn't exist");
        }
        if (aNumber.getStatus() != ANumber.Status.OPEN) {
            throw new RuntimeException("number is not open");
        }
        if (aNumber.getReservedUntil()!=null &&
                aNumber.getReservedUntil().isAfter(utilService.getCurrentDateTime())) {
            throw new RuntimeException("already reserved");
        }
        aNumber.setReservationId(reservationId);
        aNumber.setReservedUntil(utilService.getCurrentDateTime().plusMinutes(15));
        aNumberDao.updateANumber(aNumber);
        return reservationId;
    }


}

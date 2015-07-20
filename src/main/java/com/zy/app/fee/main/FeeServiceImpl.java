package com.zy.app.fee.main;

import com.zy.app.common.main.UtilService;
import com.zy.app.common.model.ChargeLine;
import com.zy.app.fee.dao.FeeDao;
import com.zy.app.fee.dao.RunningFeeDao;
import com.zy.app.fee.model.Fee;
import com.zy.app.fee.model.RunningFee;
import com.zy.app.invoice.main.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.zy.app.common.model.ChargeLineBuilder.aChargeLine;


@Component
public class FeeServiceImpl implements FeeService {

    @Autowired
    UtilService utilService;
    @Autowired
    FeeDao feeDao;
    @Autowired
    RunningFeeDao runningFeeDao;
    @Autowired
    InvoiceService invoiceService;


    @Override
    public ChargeLine chargeFee(RunningFee runningFee) {

        Fee fee = feeDao.findFeeByCode(runningFee.getFeeCode());
        if (Fee.Type.ONCE.equals(fee.getType())) {
            runningFee.setStatus(RunningFee.Status.TERMINATED);
            runningFee.setNextChargeDate(null);
        } else {
            runningFee.setNextChargeDate(getNextChargeDate(fee));
        }
        runningFeeDao.updateRunningFee(runningFee);

        return aChargeLine()
                    .withChargeDate(utilService.getCurrentDateTime())
                    .withDescription(fee.getDescription())
                    .withSubscriptionId(runningFee.getSubscriptionId())
                    .withTotal(fee.getAmount())
                .build();
    }

    @Override
    @Transactional
    public int chargeAllFees() {
        List<RunningFee> readyFees = runningFeeDao.getReadyToChargeFees();
        for (RunningFee runningFee : readyFees) {
            ChargeLine chargeLine = chargeFee(runningFee);
            invoiceService.addChargeToInvoice(chargeLine);
        }
        return readyFees.size();
    }

    private LocalDate getNextChargeDate(Fee fee) {
        LocalDate today = utilService.getCurrentDate();
        switch (fee.getPeriod()) {
            case MONTH:
                return  today.minusMonths(-1);
            case WEEK:
                return today.minusDays(-7);
            case TWO_WEEKS:
                return today.minusDays(-14);
            case YEAR:
                return today.minusYears(-1);
            default:
                return null;
        }
    }



}

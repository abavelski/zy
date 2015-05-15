package com.zy.app.rating.main;

import com.zy.app.cdr.dao.BillingRecordDao;
import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.crm.dao.RatingDao;
import com.zy.app.crm.model.Subscription;
import com.zy.app.invoice.main.InvoiceService;
import com.zy.app.rating.dao.PricePlanDao;
import com.zy.app.rating.model.RatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zy.app.rating.model.buillder.RatingCodeRequestBuilder.aRatingCodeRequest;
import static com.zy.app.rating.model.buillder.RatingRequestBuilder.aRatingRequest;

/**
 * aba
 * 24/03/15
 */
@Component
public class CdrServiceImpl implements CdrService {

    @Autowired
    BillingRecordDao billingRecordDao;
    @Autowired
    RatingDao ratingDao;
    @Autowired
    RatingService ratingService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    TrafficPlanService trafficPlanService;
    @Autowired
    PricePlanDao pricePlanDao;

    @Override
    public void rate(BillingRecord br) {

        Subscription subscription = ratingDao.getSubscriptionByPhoneNumber(br.getPhoneNumber());

        String ratingCode = trafficPlanService.getRatingCodeFromUsage(aRatingCodeRequest()
                    .withTrafficType(br.getTrafficType())
                    .withUsageType(br.getUsageType())
                    .withMappings(pricePlanDao.getTrafficMappingsForPricePlan(subscription.getPricePlanCode()))
                .build());

        br.setStatus(BillingRecord.Status.INITIAL);
        int billingRecordId = billingRecordDao.insertBillingRecord(br);
        RatingRequest request = aRatingRequest()
                    .withAmount(br.getAmount())
                    .withDestination(br.getDestination())
                    .withChargeDate(br.getChargeDate())
                    .withPricePlanCode(subscription.getPricePlanCode())
                    .withRatingCode(ratingCode)
                    .withSubscriptionId(subscription.getId())
                    .withReferenceId(billingRecordId)
                .build();

        try {
            invoiceService.updateInvoiceAndBillingRecord(ratingService.rate(request), br);
        } catch (Exception e) {
            br.setStatus(BillingRecord.Status.ERROR);
            billingRecordDao.updateBillingRecord(br);
            throw new RuntimeException("failed to rate billing record");
        }
    }

}

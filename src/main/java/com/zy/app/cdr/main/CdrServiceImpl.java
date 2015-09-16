package com.zy.app.cdr.main;

import com.zy.app.cdr.dao.BillingRecordDao;
import com.zy.app.cdr.model.BillingRecord;
import com.zy.app.crm.dao.RatingDao;
import com.zy.app.crm.model.Subscription;
import com.zy.app.invoice.main.InvoiceService;
import com.zy.app.rating.campaign.main.SubscriptionCampaignService;
import com.zy.app.rating.standard.dao.PricePlanDao;
import com.zy.app.rating.standard.main.RatingService;
import com.zy.app.rating.standard.main.TrafficPlanService;
import com.zy.app.rating.standard.model.RatingRequest;
import com.zy.app.rating.standard.model.RatingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.zy.app.rating.standard.model.buillder.RatingCodeRequestBuilder.aRatingCodeRequest;
import static com.zy.app.rating.standard.model.buillder.RatingRequestBuilder.aRatingRequest;

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
    SubscriptionCampaignService subscriptionCampaignService;
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
                    .withUnits(br.getAmount())
                    .withDestination(br.getDestination())
                    .withChargeDate(br.getChargeDate())
                    .withPricePlanCode(subscription.getPricePlanCode())
                    .withRatingCode(ratingCode)
                    .withSubscriptionId(subscription.getId())
                    .withReferenceId(billingRecordId)
                .build();

        try {
            RatingResponse response;
            RatingResponse campaignResponse = subscriptionCampaignService.rate(request);
            if (campaignResponse.getRatingRequest()==null) {
                response = campaignResponse;
            } else {
                response = ratingService.rate(request);
                response.getChargeLines().addAll(campaignResponse.getChargeLines());
            }
            invoiceService.updateInvoiceAndBillingRecord(response, br);
        } catch (Exception e) {
            br.setStatus(BillingRecord.Status.ERROR);
            billingRecordDao.updateBillingRecord(br);
            throw new RuntimeException("failed to rate billing record", e);
        }
    }

}

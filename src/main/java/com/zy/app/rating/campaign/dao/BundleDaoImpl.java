package com.zy.app.rating.campaign.dao;

import com.zy.app.rating.campaign.model.Bundle;
import com.zy.app.common.dao.Dao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;

import static com.zy.app.rating.campaign.model.builder.BundleBuilder.aBundle;
import static com.zy.app.common.util.DbUtil.fromDB;
import static com.zy.app.common.util.DbUtil.toDB;


/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class BundleDaoImpl extends Dao implements BundleDao {

    @Override
    public int createBundle(Bundle bundle) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into bundle(campaign_code, subscription_campaign_id, remaining_amount, next_release_date)" +
                                    "values( ?, ? ,? ,?)", new String[] {"id"});
                    ps.setString(1, bundle.getCampaignCode());
                    ps.setInt(2, bundle.getSubscriptionCampaignId());
                    ps.setLong(3, bundle.getRemainingAmount());
                    ps.setDate(4, toDB(bundle.getNextResetDate()));
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateBundle(Bundle bundle) {
        jdbcTemplate.update("update bundle set campaign_code=?, " +
                        "subscription_campaign_id=?, " +
                        "remaining_amount=?, " +
                        "next_release_date=? " +
                        "where id=?",
                bundle.getCampaignCode(),
                bundle.getSubscriptionCampaignId(),
                bundle.getRemainingAmount(),
                toDB(bundle.getNextResetDate()),
                bundle.getId());
    }

    @Override
    public Bundle getBundleBySubscriptionCampaignIdAndCampaignCode(Integer subscriptionCampaignId, String campaignCode) {
        return jdbcTemplate.queryForObject("select * from bundle where subscription_campaign_id = ? and campaign_code=?",
                new Object[]{subscriptionCampaignId, campaignCode},
                (rs, rowNum) -> {
                    return aBundle()
                            .withId(rs.getInt("id"))
                            .withCampaignCode(rs.getString("campaign_code"))
                            .withSubscriptionCampaignId(rs.getInt("subscription_campaign_id"))
                            .withRemainingAmount(rs.getLong("remaining_amount"))
                            .withNextResetDate(fromDB(rs.getDate("next_release_date")))
                            .build();
                });
    }



}

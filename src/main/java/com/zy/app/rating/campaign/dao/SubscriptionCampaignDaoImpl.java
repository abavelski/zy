package com.zy.app.rating.campaign.dao;

import com.zy.app.rating.campaign.main.CampaignType;
import com.zy.app.rating.campaign.model.SubscriptionCampaign;
import com.zy.app.common.dao.Dao;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

import static com.zy.app.rating.campaign.model.builder.SubscriptionCampaignBuilder.aSubscriptionCampaign;

/**
 * alexei.bavelski@gmail.com
 * 19/07/15
 */
@Component
public class SubscriptionCampaignDaoImpl extends Dao implements SubscriptionCampaignDao {
    @Override
    public List<SubscriptionCampaign> getSubscriptionCampaignsForSubscription(Integer subscriptionId) {

        return jdbcTemplate.query("select * from subscription_campaign where subscription_id = "
                + subscriptionId, (rs, rowNum) -> {
            return aSubscriptionCampaign()
                    .withId(rs.getInt("id"))
                    .withSubscriptionId(rs.getInt("subscription_id"))
                    .withCampaignCode(rs.getString("campaign_code"))
                    .withCampaignPlugin(CampaignType.valueOf(rs.getString("campaign_plugin")))
                    .build();
        });
    }

    @Override
    public int createSubscriptionCampaign(SubscriptionCampaign sc) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                (connection) -> {
                    PreparedStatement ps = connection.prepareStatement(
                            "insert into subscription_campaign(subscription_id, campaign_plugin, campaign_code)" +
                                    "values( ?, ?::campaign_type ,?)", new String[] {"id"});
                    ps.setInt(1, sc.getSubscriptionId());
                    ps.setString(2, sc.getCampaignPlugin().toString());
                    ps.setString(3, sc.getCampaignCode());
                    return ps;
                }, keyHolder
        );
        return keyHolder.getKey().intValue();

    }
}

package com.zy.app.rating.prepaid.dao;

import com.zy.app.rating.prepaid.model.RatingSession;

/**
 * alexei.bavelski@gmail.com
 * 13/09/15
 */
public interface RatingSessionDao {

    void createSession(RatingSession ratingSession);
    RatingSession findSession(String sessionKey);
    void updateSession(RatingSession session);
    void deleteSession(String sessionKey);

}

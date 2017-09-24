package com.daffodilvarsity.diuinternationalaffairs.Model;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Jibunnisa on 5/8/2017.
 */

public class ApplyEvent {
    private int eventId;
    private String userId;
    private String applydate;
    private String applytime;

    public ApplyEvent(int eventId, String userId, String applydate, String applytime) {
        this.eventId = eventId;
        this.userId = userId;
        this.applydate = applydate;
        this.applytime = applytime;
    }

    public ApplyEvent() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplydate() {
        return applydate;
    }

    public void setApplydate(String applydate) {
        this.applydate = applydate;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }
}

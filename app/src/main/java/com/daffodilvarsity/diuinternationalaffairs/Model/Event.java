package com.daffodilvarsity.diuinternationalaffairs.Model;

/**
 * Created by Jibunnisa on 12/4/2016.
 */

public class Event {

    private String title;

    private String  picture;

    private String  deadline;

    private String description;

    private String eventtype;

    private String postdatetime;


    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String  picture) {
        this.picture = picture;
    }

    public String  getDeadline() {
        return deadline;
    }

    public void setDeadline(String  deadline) {
        this.deadline = deadline;
    }

    public void setPostdatetime(String postdatetime) {
        this.postdatetime = postdatetime;
    }

    public String getPostdatetime() {
        return postdatetime;
    }
}

package com.daffodilvarsity.diuinternationalaffairs.Model;

/**
 * Created by Jibunnisa on 5/8/2017.
 */

public class MOU {
    private String title;
    private String category;
    private String website;
    private String country;
    private String flagimageurl;

    public MOU() {
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFlagimageurl() {
        return flagimageurl;
    }

    public void setFlagimageurl(String flagimageurl) {
        this.flagimageurl = flagimageurl;
    }

    public MOU(String titile, String category, String website, String country, String flagimageurl) {
        this.title = titile;
        this.category = category;
        this.website = website;
        this.country = country;
        this.flagimageurl= flagimageurl;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return website;
    }

    public void setDetails(String website) {
        this.website = website;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

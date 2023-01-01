package com.example.rxjava.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airline {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("slogan")
    @Expose
    private String slogan;
    @SerializedName("head_quaters")
    @Expose
    private String headQuaters;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("established")
    @Expose
    private String established;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getHeadQuaters() {
        return headQuaters;
    }

    public void setHeadQuaters(String headQuaters) {
        this.headQuaters = headQuaters;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEstablished() {
        return established;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

}

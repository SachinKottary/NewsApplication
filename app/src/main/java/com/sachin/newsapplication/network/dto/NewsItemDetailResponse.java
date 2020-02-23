
package com.sachin.newsapplication.network.dto;

import android.text.format.DateUtils;

import androidx.core.util.TimeUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class NewsItemDetailResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;
    @SerializedName("time_created")
    @Expose
    private long timeCreated;
    @SerializedName("rank")
    @Expose
    private long rank;

    public NewsItemDetailResponse(long timeCreated, long rank) {
        this.timeCreated = timeCreated;
        this.rank = rank;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }



}

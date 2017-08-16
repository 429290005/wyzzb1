package com.song.zzb.wyzzb.bean;

import cn.bmob.v3.BmobObject;

/**
 *
 *
 * @author wwj_748
 */
public class Advertisement extends BmobObject {
    private String feedId;
    private String date;
    private String title;
    private String topicFrom;
    private String topic;
    private String imgUrl;

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    private Integer orderby;
    public Integer getIsAd() {
        return IsAd;
    }

    public void setIsAd(Integer isAd) {
        IsAd = isAd;
    }

    private Integer IsAd;
    private Integer width;
    private Integer height;


    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicFrom() {
        return topicFrom;
    }

    public void setTopicFrom(String topicFrom) {
        this.topicFrom = topicFrom;
    }



    public Integer getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



}

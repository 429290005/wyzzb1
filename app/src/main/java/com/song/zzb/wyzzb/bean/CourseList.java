package com.song.zzb.wyzzb.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by song on 2016/2/11.
 */
public class CourseList extends BmobObject {
    private String title;

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    private String videourl;
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitledsc1() {
        return titledsc1;
    }

    public void setTitledsc1(String titledsc1) {
        this.titledsc1 = titledsc1;
    }

    private String titledsc1;
    private String price;
    public Integer getFlagID() {
        return flagID;
    }

    public void setFlagID(Integer flagID) {
        this.flagID = flagID;
    }

    private Integer flagID;
    public String getTitledsc() {
        return titledsc;
    }

    public void setTitledsc(String titledsc) {
        this.titledsc = titledsc;
    }

    public BmobFile getPicFile() {
        return picFile;
    }

    public void setPicFile(BmobFile picFile) {
        this.picFile = picFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String titledsc;
    private BmobFile picFile;

    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    private Integer orderby;
}

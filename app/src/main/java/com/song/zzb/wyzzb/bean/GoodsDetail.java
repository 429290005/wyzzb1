package com.song.zzb.wyzzb.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by song on 2016/9/6.
 */
public class GoodsDetail extends BmobObject {
    private String price;//价格
    private String people;//购买人数

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    private Integer display;//是否显示

    public List<GoodsGive> getGoodsgive() {
        return goodsgive;
    }

    public void setGoodsgive(List<GoodsGive> goodsgive) {
        this.goodsgive = goodsgive;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitledsc1() {
        return titledsc1;
    }

    public void setTitledsc1(String titledsc1) {
        this.titledsc1 = titledsc1;
    }

    public String getTitledsc2() {
        return titledsc2;
    }

    public void setTitledsc2(String titledsc2) {
        this.titledsc2 = titledsc2;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPricedsc() {
        return pricedsc;
    }

    public void setPricedsc(String pricedsc) {
        this.pricedsc = pricedsc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String pricedsc;//价格描述
    private String titledsc2;//标题描述2
    private String titledsc1;//标题描述1
    private String flag;//商品标识
    private String title;//标题
    private String imgUrl;//图片地址
    private String  videodsc;//视频标题

    public String getVideoauther() {
        return videoauther;
    }

    public void setVideoauther(String videoauther) {
        this.videoauther = videoauther;
    }

    public String getVideodsc() {
        return videodsc;
    }

    public void setVideodsc(String videodsc) {
        this.videodsc = videodsc;
    }

    private String videoauther;//视频作者
    public String getVideodetailurl() {
        return videodetailurl;
    }

    public void setVideodetailurl(String videodetailurl) {
        this.videodetailurl = videodetailurl;
    }

    private String videodetailurl;//视频连接地址

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    private List<Course> course;//课程列表
    public Integer getOrderby() {
        return orderby;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    private Integer orderby;//排序
    private List<GoodsGive> goodsgive;//赠品

    public String getImgdetailurl() {
        return imgdetailurl;
    }

    public void setImgdetailurl(String imgdetailurl) {
        this.imgdetailurl = imgdetailurl;
    }

    private String imgdetailurl;//课程详情图片url

    public BmobFile getImgdetail() {
        return imgdetail;
    }

    public void setImgdetail(BmobFile imgdetail) {
        this.imgdetail = imgdetail;
    }

    private BmobFile imgdetail;//图片url

    public BmobFile getImg1() {
        return img1;
    }

    public void setImg1(BmobFile img1) {
        this.img1 = img1;
    }

    private BmobFile img1;//图片url
}

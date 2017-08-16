package com.song.zzb.wyzzb.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by song on 2016/8/9.
 */
public class VipRecord  extends BmobObject {
    private String nickname;//用户昵称

    public String getGoodsds() {
        return goodsds;
    }

    public void setGoodsds(String goodsds) {
        this.goodsds = goodsds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSystemflag() {
        return systemflag;
    }

    public void setSystemflag(String systemflag) {
        this.systemflag = systemflag;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    private String systemflag;//用户标识
    private String price;//最终售价
    private String goodsds;//商品描述
    private String phone;//手机号

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    private String address;//地址
    private String name;//真实姓名
    private String remark;//备注
    private String discount;//几折优惠

    public String getInitialprice() {
        return initialprice;
    }

    public void setInitialprice(String initialprice) {
        this.initialprice = initialprice;
    }

    private String initialprice;//原价
}

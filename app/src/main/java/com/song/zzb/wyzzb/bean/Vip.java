package com.song.zzb.wyzzb.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by song on 2016/8/8.
 */
public class Vip extends BmobObject {
    private String vipprice;//vip价格

    public String getMallprice() {
        return mallprice;
    }

    public void setMallprice(String mallprice) {
        this.mallprice = mallprice;
    }

    public String getVipprice() {
        return vipprice;
    }

    public void setVipprice(String vipprice) {
        this.vipprice = vipprice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamedsc() {
        return namedsc;
    }

    public void setNamedsc(String namedsc) {
        this.namedsc = namedsc;
    }

    private String mallprice;//市场价
    private String name;//商品名称
    private String namedsc;//商品描述
    private String flag;//所属标志
}

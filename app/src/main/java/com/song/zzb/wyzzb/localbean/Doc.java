package com.song.zzb.wyzzb.localbean;

import org.litepal.crud.DataSupport;

/**
 * Created by song on 2016/5/8.
 */
public class Doc extends DataSupport {
    private String yuanprice;//原价
    private String title;//标题
    private String size;//文件大小

    public String getYuanprice() {
        return yuanprice;
    }

    public void setYuanprice(String yuanprice) {
        this.yuanprice = yuanprice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLinetime() {
        return linetime;
    }

    public void setLinetime(String linetime) {
        this.linetime = linetime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public void setAbstract1(String abstract1) {
        this.abstract1 = abstract1;
    }

    private String price;//价格
    private String linetime;//上线时间
    private String format;//格式
    private Integer count;//浏览人数
    private String abstract1;//摘要

    public String getAuthor1() {
        return author1;
    }

    public void setAuthor1(String author1) {
        this.author1 = author1;
    }

    private String author1;
}

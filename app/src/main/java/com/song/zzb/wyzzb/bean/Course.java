package com.song.zzb.wyzzb.bean;

import java.io.Serializable;

/**
 * Created by song on 2016/9/6.
 */
public class Course implements Serializable {
    private static final long serialVersionUID = 1L;
    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    private String dsc;//描述

    public String getJie() {
        return jie;
    }

    public void setJie(String jie) {
        this.jie = jie;
    }

    private String jie;
}

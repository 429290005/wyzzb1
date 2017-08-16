package com.song.zzb.wyzzb.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by song on 2016/8/9.
 */
public class User extends BmobUser {

    public String vip;//身份




    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }



    public String getWhichyear() {
        return whichyear;
    }

    public void setWhichyear(String whichyear) {
        this.whichyear = whichyear;
    }

    public String getWantschool() {
        return wantschool;
    }

    public void setWantschool(String wantschool) {
        this.wantschool = wantschool;
    }

    public String getCurrentschool() {
        return currentschool;
    }

    public void setCurrentschool(String currentschool) {
        this.currentschool = currentschool;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String whichyear;//那年
    public String wantschool;
    public String currentschool;//地址
    public String gender;//真实姓名
    public String QQ;//备注

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String typename;//类别
    public String getSystemflag() {
        return systemflag;
    }

    public void setSystemflag(String systemflag) {
        this.systemflag = systemflag;
    }

    public String systemflag;//设备码
}

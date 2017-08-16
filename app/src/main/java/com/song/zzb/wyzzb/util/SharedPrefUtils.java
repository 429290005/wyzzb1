package com.song.zzb.wyzzb.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.song.zzb.wyzzb.bean.User;

import java.util.List;

import cn.bmob.v3.BmobUser;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by aspsine on 16/9/2.
 */

public class SharedPrefUtils {

    private static final String SHARED_PREF_LOGIN = "login";
    private static final String a = "umeng_comm_user_info";
    private static final String b = "umeng_comm_user_appkey";
    public static boolean isLogin(Context var0) {
        SharedPreferences var1 = var0.getSharedPreferences("umeng_comm_user_info", 0);
        return var1.contains("mobilephonenumber");
    }

    public static void logout(Context context) {
        SharedPreferences var0 = context.getSharedPreferences("umeng_comm_user_info", 0);
        var0.edit().clear().commit();


    }
    public static void saveLoginUserInfo(Context var0) {
        if(var0 != null) {
            SharedPreferences var2 = var0.getSharedPreferences("umeng_comm_user_info", 0);
            SharedPreferences.Editor var3 = var2.edit();
            var3.putString("username", (String) BmobUser.getObjectByKey(var0,"username"));
            //    LogUtil.i("mobilephonenumber2",(String) BmobUser.getObjectByKey(var0,"username"));
            var3.putString("mobilephonenumber", (String) BmobUser.getObjectByKey(var0,"mobilePhoneNumber"));
            //   LogUtil.i("mobilephonenumber2",(String) BmobUser.getObjectByKey(var0,"mobilePhoneNumber"));
            var3.putString("typename", (String) BmobUser.getObjectByKey(var0,"typename")== null?"":(String) BmobUser.getObjectByKey(var0,"typename"));
            //   LogUtil.i("mobilephonenumber3",(String) BmobUser.getObjectByKey(var0,"typename")== null?"":(String) BmobUser.getObjectByKey(var0,"typename"));
            var3.putString("QQ", (String) BmobUser.getObjectByKey(var0,"QQ") == null?"":(String) BmobUser.getObjectByKey(var0,"QQ"));
            //   LogUtil.i("mobilephonenumber4", (String) BmobUser.getObjectByKey(var0,"QQ")== null?"":(String) BmobUser.getObjectByKey(var0,"QQ"));
            var3.putString("gender",(String) BmobUser.getObjectByKey(var0,"gender") == null?"": (String) BmobUser.getObjectByKey(var0,"gender"));
            var3.putString("currentschool", (String) BmobUser.getObjectByKey(var0,"currentschool")== null?"":(String) BmobUser.getObjectByKey(var0,"currentschool"));
            //   LogUtil.i("mobilephonenumber5",(String) BmobUser.getObjectByKey(var0,"username")== null?"":(String) BmobUser.getObjectByKey(var0,"username"));
            var3.putString("wantschool",(String) BmobUser.getObjectByKey(var0,"wantschool")== null?"":(String) BmobUser.getObjectByKey(var0,"wantschool"));
            //   LogUtil.i("mobilephonenumber6", (String) BmobUser.getObjectByKey(var0,"wantschool")== null?"":(String) BmobUser.getObjectByKey(var0,"wantschool"));
            var3.putString("whichyear",(String) BmobUser.getObjectByKey(var0,"whichyear")== null?"":(String) BmobUser.getObjectByKey(var0,"whichyear"));
            //    LogUtil.i("mobilephonenumber7", (String) BmobUser.getObjectByKey(var0,"whichyear")== null?"":(String) BmobUser.getObjectByKey(var0,"whichyear"));
            ToastUtil.showToast(getApplicationContext(),""+(String) BmobUser.getObjectByKey(var0,"vip"));
            var3.putString("vip",(String) BmobUser.getObjectByKey(var0,"vip")== null?"":(String) BmobUser.getObjectByKey(var0,"vip"));

            var3.putString("systemflag",(String) BmobUser.getObjectByKey(var0,"systemflag") == null?"": (String) BmobUser.getObjectByKey(var0,"systemflag"));
//            LogUtil.i("mobilephonenumber8",(String) BmobUser.getObjectByKey(var0,"systemflag")== null?"":(String) BmobUser.getObjectByKey(var0,"systemflag"));
            var3.commit();

        }
    }
    public static User getLoginUser(Context var0) {
        if(!isLogin(var0)) {
            return new User();
        } else {
            String var1 = "";
            User var2 = new User();
            SharedPreferences var3 = var0.getSharedPreferences("umeng_comm_user_info", 0);
            var2.setUsername( var3.getString("username", ""));
            var2.setMobilePhoneNumber(var3.getString("mobilephonenumber",  ""));
            var2.setVip(var3.getString("vip",""));
//            String var5 = var3.getString("source", "");
//            Source var6 = Source.convertEnum(var5);
//            int var7 = var3.getInt("level", 0);
//            String var8 = var3.getString("level_title", "");
//            String var9 = var3.getString("open_uid", "");
//            String var10 = var3.getString("permissions", "");
//            List var11 = a(var10);
//            int var12 = var3.getInt("unread_count", 0);
//            if(TextUtils.isEmpty(var2.id)) {
//                return var2;
//            } else {
//                CommUser var13 = (CommUser)(new Select()).from(CommUser.class).where("_id=?", new Object[]{var2.id}).executeSingle();
//                if(var13 != null && !TextUtils.isEmpty(var13.id)) {
//                    var2 = var13;
//                }
//
//                var2.source = var6;
//                var2.token = var9;
//                var2.age = var4;
//                var2.level = var7;
//                var2.levelTitle = var8;
//                if(var2.point == 0) {
//                    var2.point = var3.getInt("user_point", 0);
//                }
//
//                var2.unReadCount = var12;
//                if(!isListEmpty(var11)) {
//                    var2.subPermissions.addAll(var11);
//                }

                return var2;

        }
    }
}

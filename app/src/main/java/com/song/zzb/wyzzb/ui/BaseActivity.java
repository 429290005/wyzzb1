package com.song.zzb.wyzzb.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.util.Constant;
import com.song.zzb.wyzzb.util.LogUtil;
import com.song.zzb.wyzzb.util.ToastUtil;

import com.umeng.message.PushAgent;

import cn.bmob.v3.Bmob;


/**
 * Created by song on 2016/1/28.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    protected String TAG;
    protected Context mContext;
    protected TextView actionbarTitle;
    protected TextView rightMenu;
    protected ImageView leftMenu;

//    Dialog dialog;
    protected void onCreate(Bundle arg0){
        super.onCreate(arg0);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        TAG=this.getClass().getSimpleName();

         LogUtil.i("base",TAG);
        mContext =this;
//        Bmob.initialize(this, Constant.BMOB_APP_ID);
        PushAgent.getInstance(this.mContext).onAppStart();
//初始化建表操作
//        BmobUpdateAgent.initAppVersion(this);
    }
    public void showToast(String text) {
        ToastUtil.showToast(this, text);
    }
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    public void initProgressDialog(String loadingMsgId) {

//        dialog.show();
    }

   public void dismissDialog() {

//            dialog.dismiss();

    }
    public void redictTo(Context context, Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, activity);
        if (bundle != null) {
            intent.putExtra("data", bundle);
        }
        startActivity(intent);
    }
    protected void setUpTitle(String title){
        actionbarTitle = (TextView)findViewById(R.id.actionbar_title);
        actionbarTitle.setText(title);
    }

    protected void setUpRightMenu(String text){
        rightMenu = (TextView)findViewById(R.id.actionbar_right_menu);
        rightMenu.setText(text);

        rightMenu.setOnClickListener(this);
    }

    protected void setUpLeftMenu(int visible){
        leftMenu = (ImageView)findViewById(R.id.actionbar_left_menu);
        leftMenu.setOnClickListener(this);
        if(visible == 1){
            leftMenu.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.actionbar_right_menu:
                onRightMenuClick();
                break;
            case R.id.actionbar_left_menu:
                onLeftMenuClick();
                break;
            case R.id.actionbar_title:

                break;
            default:
                break;
        }
    }
    protected void onLeftMenuClick() {
        // TODO Auto-generated method stub
        this.finish();
    }
    protected void onRightMenuClick() {
        // TODO Auto-generated method stub

    }

}

package com.song.zzb.wyzzb.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.util.SharedPrefUtils;

import cn.bmob.v3.BmobUser;


/**
 * Created by song on 2016/7/23.
 */
public class HelpActivity extends BaseActivity {
private TextView tv_about_intro;
    private LinearLayout l_setting_logout;
    private RelativeLayout rl_about_hideview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }

    private void initView() {
        onNewIntent(getIntent());
        //如果是来自答题卡
        //我的错题
        setUpTitle("设置");
        setUpLeftMenu(1);
         tv_about_intro =(TextView) findViewById(R.id.tv_about_intro);
        l_setting_logout =(LinearLayout) findViewById(R.id.l_setting_logout);
        rl_about_hideview =(RelativeLayout) findViewById(R.id.rl_about_hideview);
//        tv_about_intro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 Intent intent= new Intent();
////                intent.setClass(HelpActivity.this,AboutUsActivity.class);
////                startActivity(intent);
//                String url11 = "mqqwpa://im/chat?chat_type=group&uin=254516222&version=1";
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
//            }
//        });
        l_setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefUtils.logout(getApplicationContext());
                BmobUser.logOut(getApplication());

                finish();
            }
        });

    }

}

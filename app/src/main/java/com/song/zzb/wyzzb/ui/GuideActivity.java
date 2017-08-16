package com.song.zzb.wyzzb.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.song.zzb.wyzzb.R;

import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;


public class GuideActivity extends Activity {
    private static final String TAG = GuideActivity.class.getSimpleName();
    private BGABanner mBackgroundBanner;
    private BGABanner mForegroundBanner;
    private static final String SHAREDPREFERENCES_NAME = "my_pref";
    private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        setListener();
        processLogic();
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        mBackgroundBanner = (BGABanner) findViewById(R.id.banner_guide_background);
        mForegroundBanner = (BGABanner) findViewById(R.id.banner_guide_foreground);
    }

    private void setListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                SharedPreferences sharedPreferences=getSharedPreferences(SHAREDPREFERENCES_NAME, 0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(KEY_GUIDE_ACTIVITY, "false");
                editor.commit();
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });


    }

    private void processLogic() {
        mBackgroundBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(GuideActivity.this)
                        .load(model)
                        .placeholder(R.drawable.holder)
                        .error(R.drawable.holder)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });
        // 设置数据源
        // mBackgroundBanner.setData(R.drawable.uoko_guide_background_1, R.drawable.uoko_guide_background_2, R.drawable.uoko_guide_background_3);
       mBackgroundBanner.setData(Arrays.asList("http://bmob-cdn-83.b0.upaiyun.com/2017/01/21/a274910d400d602b80bb35c63df3fd11.png","http://bmob-cdn-83.b0.upaiyun.com/2017/01/21/5485289940a27f25806439abb4d3a40e.png", "http://bmob-cdn-83.b0.upaiyun.com/2017/01/21/e4f6232c403a7fa28044f66ba0728595.png"), Arrays.asList("http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg", "http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg", "http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg"));
        // mForegroundBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);

      // mForegroundBanner.setData(Arrays.asList("http://p0.so.qhmsg.com/t0189f7162009faa84f.png", "http://img.sfw.cn/admin/w/news/uploadfile/20121023115930557.jpg", "http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg"), Arrays.asList("http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg", "http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg", "http://pic77.nipic.com/file/20150916/18884165_161601234452_2.jpg"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 如果开发者的引导页主题是透明的，需要在界面可见时给背景 Banner 设置一个白色背景，避免滑动过程中两个 Banner 都设置透明度后能看到 Launcher
       mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}
package com.song.zzb.wyzzb.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;

import com.song.zzb.wyzzb.util.Constant;

import cn.bmob.v3.Bmob;
import cn.smssdk.SMSSDK;

/**
 * 引导页
 * 
 * @ClassName: SplashActivity
 * @Description: TODO
 * @author smile
 * @date 2014-6-4 上午9:45:43
 */
public class SplashActivity extends Activity {
	private TextView splash_tv_firstLoading;
	public final static int MAIN_ACTIVITY=1000;
	public final static int GUIDE_ACTIVITY=1001;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);


		Boolean mIsFirstEnter=IsFirstEnter(SplashActivity.this,SplashActivity.this.getClass().getName());

		if (mIsFirstEnter) {
			mHandler.sendEmptyMessageDelayed(GUIDE_ACTIVITY, 0);//5000是延迟的时间5秒
			Log.i("221","(GUIDE_ACTIVITY");
		}
		else {
			mHandler.sendEmptyMessageDelayed(MAIN_ACTIVITY, 1000);
			Log.i("221","MAIN_ACTIVITY");
		}
	}
	//判断是不是第一次启动的函数
	private static final String SHAREDPREFERENCES_NAME = "my_pref";
	private static final String KEY_GUIDE_ACTIVITY = "guide_activity";
	private Boolean IsFirstEnter(Context context, String name) {
		// TODO Auto-generated method stub
		if (context==null||name==null||"".equalsIgnoreCase(name)){
			return false;
		}//显示引导界面
		String mResultString=context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_WORLD_READABLE).getString(KEY_GUIDE_ACTIVITY, "");
		if (mResultString.equalsIgnoreCase("false")) {
			return false;
		}
		else {
			return true;//是第一次启动
		}


	}
	Handler mHandler=new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case GUIDE_ACTIVITY:
					Intent intent=new Intent();
					intent.setClass(SplashActivity.this, GuideActivity.class);
					SplashActivity.this.startActivity(intent);
					SplashActivity.this.finish();
					break;
				case MAIN_ACTIVITY:
					intent=new Intent();
					intent.setClass(SplashActivity.this, MainActivity.class);
					SplashActivity.this.startActivity(intent);
					SplashActivity.this.finish();
					break;

			}
			super.handleMessage(msg);
		};
	};
		//splash_tv_firstLoading = (TextView) findViewById(R.id.splash_tv_firstLoading);
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				Intent intent = new Intent();
//				intent.setClass(SplashActivity.this, MainActivity.class);
//
//
//				startActivity(intent);
//
//				finish();
//			}
//		}, 1000);
//	}

}

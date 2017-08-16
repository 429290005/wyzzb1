package com.song.zzb.wyzzb.fragment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import com.shizhefei.view.largeimage.LongImageView;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.CourseAdapter;
import com.song.zzb.wyzzb.ui.view.MediaController;
import com.song.zzb.wyzzb.ui.view.SuperVideoPlayer;
import com.song.zzb.wyzzb.util.DensityUtil;

import java.io.File;
import java.lang.reflect.Field;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by song on 2016/9/7.
 */
public class CourseListFragment extends BaseFragment {
    ImageLoader imageLoader = ImageLoader.getInstance();
    private CourseAdapter courseDscAdapter;
    private SuperVideoPlayer mSuperVideoPlayer;
    private FrameLayout video_fy;
    private Button mPlayBtnView;
    LongImageView test_image;
    Bundle bundle =null;
    private String detail[]= new String[5];
    private TextView id_test_tv,id_answer_tv,id_exchange_tv,id_download_tv,actionbar_right_menu,actionbar_title;
    //private String url="http://7xsa6o.com1.z0.glb.clouddn.com/computer1.1%E4%BF%A1%E6%81%AF%E4%B8%8E%E4%BF%A1%E6%81%AF%E6%8A%80%E6%9C%AF%E6%A6%82%E8%BF%B0.mp4";

    private String url="";
    private String title="";
 //   private List<Course> videocores = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
         bundle=getArguments();
//        initProgressDialog("拼命加载中...");
        if(bundle!=null){
            detail=bundle.getStringArray("detail");

            Log.i("name", "" + detail);
        }

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.fragment_coursedetail, container, false);
//        Bundle bundle=getArguments();
//        Log.i("name", "" + bundle.getSerializable("core"));
        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        // TODO Auto-generated method stub
        super.onHiddenChanged(hidden);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }
    /**
     * 播放器的回调函数
     */
    private SuperVideoPlayer.VideoPlayCallbackImpl mVideoPlayCallback = new SuperVideoPlayer.VideoPlayCallbackImpl() {
        /**
         * 播放器关闭按钮回调
         */
        @Override
        public void onCloseVideo() {
            mSuperVideoPlayer.close();//关闭VideoView
            mPlayBtnView.setVisibility(View.VISIBLE);
            mSuperVideoPlayer.setVisibility(View.GONE);
            resetPageToPortrait();
        }

        /**
         * 播放器横竖屏切换回调
         */
        @Override
        public void onSwitchPageType() {
            if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
            } else {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                mSuperVideoPlayer.setPageType(MediaController.PageType.EXPAND);
            }
        }

        /**
         * 播放完成回调
         */
        @Override
        public void onPlayFinish() {

        }
    };
    /***
     * 旋转屏幕之后回调
     *
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (null == mSuperVideoPlayer) return;
        /***
         * 根据屏幕方向重新设置播放器的大小
         */
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().getDecorView().invalidate();
            float height = DensityUtil.getWidthInPx(getActivity());
            float width = DensityUtil.getHeightInPx(getActivity());
            mSuperVideoPlayer.getLayoutParams().height = (int) width;
            mSuperVideoPlayer.getLayoutParams().width = (int) height;
        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            final WindowManager.LayoutParams attrs = getActivity().getWindow().getAttributes();
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getActivity().getWindow().setAttributes(attrs);
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            float width = DensityUtil.getWidthInPx(getActivity());
            float height = DensityUtil.dip2px(getActivity(), 200.f);
            mSuperVideoPlayer.getLayoutParams().height = (int) height;
            mSuperVideoPlayer.getLayoutParams().width = (int) width;
        }
    }

    /***
     * 恢复屏幕至竖屏
     */
    private void resetPageToPortrait() {
        if (getActivity().getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            mSuperVideoPlayer.setPageType(MediaController.PageType.SHRINK);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    protected void initViews(){
        mSuperVideoPlayer = (SuperVideoPlayer) findViewById(R.id.video_player_item_1);
        mPlayBtnView = (Button)findViewById(R.id.play_btn);
        actionbar_title=(TextView)findViewById(R.id.actionbar_title);
        mSuperVideoPlayer.setVideoPlayCallback(mVideoPlayCallback);
        test_image = (LongImageView)findViewById(R.id.imageView);
        actionbar_right_menu=(TextView)findViewById(R.id.actionbar_right_menu);
         video_fy =(FrameLayout)findViewById(R.id.video_fy);
        mPlayBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayBtnView.setVisibility(View.GONE);
                mSuperVideoPlayer.setVisibility(View.VISIBLE);
                mSuperVideoPlayer.setAutoHideController(false);
                Uri uri = Uri.parse(url);
                mSuperVideoPlayer.loadAndPlay(uri,0);
            }
        });
        if(bundle!=null) {
            BmobFile bmobfile = new BmobFile(detail[0], "", detail[1]);

            if(!detail[2].isEmpty()&&detail[2].equals("1")){
                video_fy.setVisibility(View.GONE);
                Log.i("detail[2]2",""+detail[2]);
            }else{
                url=detail[2];
                Log.i("detail[2]1",""+detail[2]);
                video_fy.setVisibility(View.VISIBLE);
                actionbar_title.setText(detail[3]);
                actionbar_right_menu.setText(detail[4]);
            }
            final File saveFile = new File(getActivity().getExternalCacheDir(), detail[0]);

            //   final  File saveFile = new File(Environment.getExternalStorageDirectory(), bmobfile.getFilename());
            bmobfile.download(getActivity(), saveFile, new DownloadFileListener() {
                @Override
                public void onSuccess(String s) {
                    Log.i("detail", "" + saveFile.getAbsolutePath() + "/" + detail[0]);
                    test_image.setImage(saveFile.getAbsolutePath());
                   // dismissDialog();

                }

                @Override
                public void onFailure(int i, String s) {
                    //dismissDialog();
                }
            });
        }else{

        }



    }
}

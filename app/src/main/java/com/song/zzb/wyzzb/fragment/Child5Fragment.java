package com.song.zzb.wyzzb.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.User;
import com.song.zzb.wyzzb.ui.HelpActivity;
import com.song.zzb.wyzzb.ui.PersonInforActivity;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.ToastUtil;
import com.song.zzb.wyzzb.widget.RegisterPage;
import com.song.zzb.wyzzb.widget.RoundImageView;
import cn.bmob.v3.BmobUser;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Child5Fragment extends Fragment implements
        View.OnClickListener{
    private ImageView userinfo_headicon,userinfo_headicon_nologin;
    private ImageView umeng_comm_setting;
    public static final String TAG = Child5Fragment.class.getSimpleName();
    TextView user_name_tv_nologin,user_name_tv,user_fanscount;
    private RelativeLayout umeng_comm_mine,umeng_comm_favortes,umeng_comm_sponsor,umeng_comm_mynotify,user_haveno_login,user_have_login;
    public static Fragment newInstance() {
        Child5Fragment fragment = new Child5Fragment();

        return fragment;
    }

    public Child5Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id. user_haveno_login:
                //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                        if (result == SMSSDK.RESULT_COMPLETE) {

                        }
                    }
                });
                registerPage.show(getContext());
                break;
            case R.id.umeng_comm_mynotify:
                break;
            case R.id. umeng_comm_sponsor:
                break;
            case R.id.umeng_comm_favortes:
                break;
            case R.id.umeng_comm_mine:
                break;
            case R.id.user_have_login:
                Intent intent = new Intent(getContext(), PersonInforActivity.class);
                startActivity(intent);
                break;
            case R.id.umeng_comm_setting:
            Intent setting = new Intent(getContext(), HelpActivity.class);
            startActivity(setting);

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child5, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        umeng_comm_mine = (RelativeLayout) view.findViewById(R.id.umeng_comm_mine);
        umeng_comm_favortes = (RelativeLayout) view.findViewById(R.id.umeng_comm_favortes);
        umeng_comm_sponsor= (RelativeLayout) view.findViewById(R.id.umeng_comm_sponsor);
        umeng_comm_mynotify= (RelativeLayout) view.findViewById(R.id.umeng_comm_sponsor);
        user_haveno_login= (RelativeLayout) view.findViewById(R.id.user_haveno_login);
        user_have_login= (RelativeLayout) view.findViewById(R.id.user_have_login);//已登录
        user_name_tv=  (TextView) view.findViewById(R.id.user_name_tv);
        user_fanscount=  (TextView) view.findViewById(R.id.user_fanscount);
        umeng_comm_setting=  (ImageView) view.findViewById(R.id.umeng_comm_setting);
        umeng_comm_setting.setOnClickListener(this);

        userinfo_headicon_nologin= (ImageView) view.findViewById(R.id.userinfo_headicon_nologin);
        userinfo_headicon= (ImageView) view.findViewById(R.id.userinfo_headicon);
        user_name_tv_nologin= (TextView) view.findViewById(R.id.user_name_tv_nologin);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.morentuf);

        userinfo_headicon_nologin.setImageDrawable(new RoundImageView(bitmap));
        userinfo_headicon.setImageDrawable(new RoundImageView(bitmap));
        if(SharedPrefUtils.isLogin(getContext())){
            // 允许用户使用应用
            user_haveno_login.setVisibility(View.GONE);
            user_have_login.setVisibility(View.VISIBLE);
            user_name_tv.setText(  SharedPrefUtils.getLoginUser(getContext()).getMobilePhoneNumber());
//            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//            userinfo_headicon.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon.setBackGroundColor(getResources().getColor(R.color.umeng_comm_color_transparent));
        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            user_name_tv_nologin.setText("立即登陆");
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.umeng_comm_color_transparent));
        }
        user_haveno_login.setOnClickListener(this);
        umeng_comm_mynotify.setOnClickListener(this);
        umeng_comm_sponsor.setOnClickListener(this);
        umeng_comm_favortes.setOnClickListener(this);
        umeng_comm_mine.setOnClickListener(this);
        user_have_login.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {

        } else {


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }
    /**
     * 此时仅仅关心详情页的收藏字段
     */
//    private BroadcastManager.DefalutReceiver mReceiver = new BroadcastManager.DefalutReceiver() {
//        public void  onReceiveUser(Intent intent) {
////            User user= getUser(intent);
////
////            user_name_tv.setText(user.getMobilePhoneNumber());
//
//        }
//    };

    @Override
    public void onDestroyView() {

//        ToastUtil.showToast(getContext(),"onDestroyView");
        super.onDestroyView();
    }
//    public void onStart() {
//        if(User.getCurrentUser(getContext()) != null){
//            // 允许用户使用应用
//            user_haveno_login.setVisibility(View.GONE);
//            user_have_login.setVisibility(View.VISIBLE);
//            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//            user_name_tv_nologin.setText("立即登陆");
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.design_fab_shadow_end_color));
//        }
//        super.onStart();
//    }
//    public void onPause() {
//        if(SharedPrefUtils.isLogin(getContext())){
//            // 允许用户使用应用
//            user_haveno_login.setVisibility(View.GONE);
//            user_have_login.setVisibility(View.VISIBLE);
//          ;
//            user_name_tv.setText(  SharedPrefUtils.getLoginUser(getContext()).getMobilePhoneNumber());
////            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//            ToastUtil.showToast(getContext(),"onPause1");
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//            user_name_tv_nologin.setText("立即登陆");
//            user_haveno_login.setVisibility(View.VISIBLE);
//            user_have_login.setVisibility(View.GONE);
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.design_fab_shadow_end_color));
//            ToastUtil.showToast(getContext(),"onPause2");
//        }
//
//        super.onPause();
//    }
//    public void onStart() {
//        if(SharedPrefUtils.isLogin(getContext())){
//            // 允许用户使用应用
//            user_haveno_login.setVisibility(View.GONE);
//            user_have_login.setVisibility(View.VISIBLE);
//            user_name_tv.setText(  SharedPrefUtils.getLoginUser(getContext()).getMobilePhoneNumber());
////            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//            ToastUtil.showToast(getContext(),"onStart2");
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//            user_name_tv_nologin.setText("立即登陆");
//            user_haveno_login.setVisibility(View.VISIBLE);
//            user_have_login.setVisibility(View.GONE);
//            ToastUtil.showToast(getContext(),"onStart1");
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.design_fab_shadow_end_color));
//            user_name_tv.setText("");
//            ToastUtil.showToast(getContext(),"onStart");
//        }
//        //
//        super.onStart();
//    }
    public void onResume() {
        if(SharedPrefUtils.isLogin(getContext())){
            // 允许用户使用应用
            user_haveno_login.setVisibility(View.GONE);
            user_have_login.setVisibility(View.VISIBLE);
            SharedPreferences pref = getActivity().getSharedPreferences("umeng_comm_user_info",0);
            String name = pref.getString("mobilephonenumber","");//第二个参数为默认值
            user_name_tv.setText(  SharedPrefUtils.getLoginUser(getContext()).getMobilePhoneNumber());
//            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//            userinfo_headicon.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon.setBackGroundColor(getResources().getColor(R.color.umeng_comm_color_transparent));

        }else{
            //缓存用户对象为空时， 可打开用户注册界面…
            user_name_tv_nologin.setText("立即登陆");
            user_haveno_login.setVisibility(View.VISIBLE);
            user_have_login.setVisibility(View.GONE);
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.umeng_comm_color_transparent));
//            ((RoundImageView)this.getView().findViewById(ResFinder.getId("userinfo_headicon_nologin"))).setImageDrawable(ResFinder.getDrawable("morentuf"));
//            ((RoundImageView)this.getView().findViewById(ResFinder.getId("userinfo_headicon_nologin"))).setBackGroundColor(ResFinder.getColor("umeng_comm_color_transparent"));
//            ToastUtil.showToast(getContext(),"onResume1");
            user_name_tv.setText("");
        }
       // ToastUtil.showToast(getContext(),"onResume");
        super.onResume();
    }
//    public void onStop() {
//        if(User.getCurrentUser(getContext()) != null){
//            // 允许用户使用应用
//            user_haveno_login.setVisibility(View.GONE);
//            user_have_login.setVisibility(View.VISIBLE);
//            user_name_tv.setText(  SharedPrefUtils.getLoginUser(getContext()).getMobilePhoneNumber());
////            user_name_tv.setText( (String) BmobUser.getObjectByKey(getContext(),"mobilePhoneNumber"));
//            ToastUtil.showToast(getContext(),"onStop2");
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//            user_name_tv_nologin.setText("立即登陆");
//            user_haveno_login.setVisibility(View.VISIBLE);
//            user_have_login.setVisibility(View.GONE);
//            userinfo_headicon_nologin.setImageDrawable( getResources().getDrawable(R.mipmap.xuetang_avatar_defalt) );
//            userinfo_headicon_nologin.setBackGroundColor(getResources().getColor(R.color.design_fab_shadow_end_color));
//
//            user_name_tv.setText("");
//            ToastUtil.showToast(getContext(),"onStop");
//        }
//
//        super.onStop();
//    }
    public void onDestroy(){

//        ToastUtil.showToast(getContext(),"onDestroy");

        super.onDestroy();
    }
}

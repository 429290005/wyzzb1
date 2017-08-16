/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Umeng, Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.song.zzb.wyzzb.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.User;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.ToastUtil;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 设置页面 注意：此Activity的名字不能修改，数据层需要回调此Activity
 */
public class PersonInforActivity extends Activity implements View.OnClickListener{

    private TextView mTitleTextView;
    private String gender;
    private LinearLayout ll_activity_myinfo_undergraduate_schoollayout,ll_activity_myinfo_yearlayout,
            ll_activity_myinfo_majorlayout,ll_activity_myinfo_masterschoollayout;
    private TextView mSaveButton;
    private TextView setting_schoolname,setting_wantname,setting_yearname,setting_typename;
    private EditText nikcnameEdit,setting_qqname,setting_phone;
    private ImageView umeng_comm_setting_back;
    private Dialog mDialog;
    private RadioButton manButton,womanButton;
    private RadioGroup genderGroup;
    private Dialog mProgressDialog;
    private boolean isFirst = false;
    private boolean isRegisterUserNameInvalid = false;
    private int loginStyle  = 0;
    private String ObjectId;
     String tmDevice;


    protected void onCreate(Bundle bundle) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(bundle);
        setContentView(R.layout.activity_myinfo);
        initViews();
    }

    /**
     * 初始化相关View</br>
     */
    private void initViews() {
        umeng_comm_setting_back=(ImageView) findViewById(R.id.umeng_comm_setting_back);
        umeng_comm_setting_back.setOnClickListener(this);
        mSaveButton=(TextView) findViewById(R.id.umeng_comm_save_bt);
        mSaveButton.setVisibility(View.VISIBLE);
        mSaveButton.setOnClickListener(this);
        nikcnameEdit = (EditText) findViewById(R.id.setting_username);
        closeInputMethod();
        setting_schoolname = (TextView)findViewById(R.id.setting_schoolname);
        setting_wantname = (TextView)findViewById(R.id.setting_wantname);
        setting_qqname =  (EditText) findViewById(R.id.setting_qqname);
        setting_phone=  (EditText) findViewById(R.id.setting_phone);
        setting_typename=  (TextView) findViewById(R.id.setting_typename);
        setting_yearname = (TextView) findViewById(R.id.setting_yearname);
        genderGroup = (RadioGroup)findViewById(R.id.genderGroup);
        manButton = (RadioButton)findViewById(R.id.maleButton);
        womanButton = (RadioButton)findViewById(R.id.femaleButton);

        genderGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == manButton.getId()){
                    gender="男";

                    manButton.setChecked(true);
                }else {
                    gender="女";

                    womanButton.setChecked(true);
                }

            }
        });
        if(User.getCurrentUser(getApplicationContext()) != null){
            // 允许用户使用应用

            nikcnameEdit.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"username"));
            setting_schoolname.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"currentschool"));
            setting_wantname.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"wantschool"));
            setting_qqname.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"QQ"));
            setting_phone.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"username"));
            setting_yearname.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"whichyear"));
            setting_typename.setText( (String) BmobUser.getObjectByKey(getApplicationContext(),"username"));
            setting_typename.setText((String) BmobUser.getObjectByKey(getApplicationContext(),"typename"));
            if(  BmobUser.getObjectByKey(getApplicationContext(),"gender").equals("男")){
                manButton.setChecked(true);
            }else if( BmobUser.getObjectByKey(getApplicationContext(),"gender").equals("女")){
                womanButton.setChecked(true);
            }


        }
        final TelephonyManager tm = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);

        tmDevice = "" + tm.getDeviceId();
        if(this.getIntent().getStringExtra("phone")!=null){
            setting_phone.setText(this.getIntent().getStringExtra("phone").toString())  ;
        }

        ll_activity_myinfo_undergraduate_schoollayout = (LinearLayout) findViewById(R.id.ll_activity_myinfo_undergraduate_schoollayout);
        ll_activity_myinfo_undergraduate_schoollayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new MaterialDialog.Builder(PersonInforActivity.this)
                                .title("请选择当前学校")
                                .titleGravity(GravityEnum.CENTER)
                                .items(R.array.school_current)
                                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                                    @Override
                                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                        /**
                                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                         * returning false here won't allow the newly selected radio button to actually be selected.
                                         **/

                                        if(!TextUtils.isEmpty(text)){
                                            setting_schoolname.setText(text.toString());
                                        }


                                        return true;
                                    }
                                })
                                .positiveText("选择")
                                .negativeText("取消")
                                .cancelListener(new DialogInterface.OnCancelListener() {
                                    @Override
                                    public void onCancel(DialogInterface dialog) {
                                        dialog.dismiss();
                                    }
                                })

                                .show();
                    }
                }
        );
        ll_activity_myinfo_yearlayout = (LinearLayout) findViewById(R.id.ll_activity_myinfo_yearlayout);
        ll_activity_myinfo_yearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(PersonInforActivity.this)
                        .title("考试时间")
                        .titleGravity(GravityEnum.CENTER)
                        .items(R.array.year)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                if(!TextUtils.isEmpty(text)){
                                    setting_yearname.setText(text.toString());
                                }

                                return true;
                            }
                        })
                        .positiveText("选择")
                        .negativeText("取消")
                        .cancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        })

                        .show();

            }
        });
        ll_activity_myinfo_majorlayout = (LinearLayout) findViewById(R.id.ll_activity_myinfo_majorlayout);
        ll_activity_myinfo_majorlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MaterialDialog.Builder(PersonInforActivity.this)
                        .title("选择考试的类别")
                        .titleGravity(GravityEnum.CENTER)
                        .items(R.array.question_type)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                if(!TextUtils.isEmpty(text)){
                                    setting_typename.setText(text.toString());
                                }
                                return true;
                            }
                        })
                        .positiveText("选择")
                        .negativeText("取消")
                        .cancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        })

                        .show();
//                showSingleChoiceDialog(v,"报考的学校",items);
            }
        });
        ll_activity_myinfo_masterschoollayout = (LinearLayout) findViewById(R.id.ll_activity_myinfo_masterschoollayout);
        ll_activity_myinfo_masterschoollayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String[] items={"Items_one","Items_two","Items_three"};
                new MaterialDialog.Builder(PersonInforActivity.this)
                        .title("选择报考的院校")
                        .titleGravity(GravityEnum.CENTER)
                        .items(R.array.school_target)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                if(!TextUtils.isEmpty(text)){
                                    setting_wantname.setText(text.toString());
                                }

                                return true;
                            }
                        })
                        .positiveText("选择")
                        .negativeText("取消")
                        .cancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                dialog.dismiss();
                            }
                        })

                        .show();
//                showSingleChoiceDialog(v,"报考的学校",items);
            }
        });



    }
    private void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(nikcnameEdit.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.umeng_comm_setting_back) { // 返回事件
                     finish();
        } else if (id == R.id.umeng_comm_save_bt) { // 保存事件
           BmobUser bmobUser = User.getCurrentUser(this.getApplication());
            if(bmobUser != null){
                updateUserInfo();
            }else{
                //缓存用户对象为空时， 可打开用户注册界面…
                registerUserInfo();

            }

            finish();
        }
//        else if (id == R.id.setting_gender){
//            showGenderDialog();
//        }else if (id == R.id.setting_gender){
//            showGenderDialog();
//        }else if (id == R.id.setting_gender){
//            showGenderDialog();
       }
//    }






    @Override
    protected void onDestroy() {
//        hideInputMethod();
        super.onDestroy();
    }



    /**
     * 处理back事件的逻辑。如果当前页面已经是设置页面，则直接返回(finish);否则回退到设置页面</br>
     */
    private void dealBackLogic() {
        finish();
    }

    /**
     * 注册用户信息</br>
     */
    private void registerUserInfo() {
        User user = new User();
        if (TextUtils.isEmpty(nikcnameEdit.getText().toString())){
            user.setUsername(nikcnameEdit.getHint().toString());
        }else{

        }
        user.setGender(gender);
        if (!TextUtils.isEmpty(setting_qqname.getText().toString())){
            user.setQQ(setting_qqname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_phone.getText().toString())){
            user.setMobilePhoneNumber(setting_phone.getText().toString());
            user.setPassword("111111");
            user.setUsername(setting_phone.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_schoolname.getText().toString())){
            user.setCurrentschool(setting_schoolname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_schoolname.getText().toString())){
            user.setCurrentschool(setting_schoolname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_wantname.getText().toString())){
            user.setWantschool(setting_wantname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_yearname.getText().toString())){
            user.setWhichyear(setting_yearname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_typename.getText().toString())){
            user.setTypename(setting_typename.getText().toString());
        }
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String DEVICE_ID = tm.getDeviceId();
        user.setSystemflag(DEVICE_ID);
        //注意：不能用save方法进行注册
        user.signUp(getApplicationContext(), new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showToast(getApplicationContext(),"注册成功");
            }
            @Override
            public void onFailure(int i, String s) {
                ToastUtil.showToast(getApplicationContext(),s.toString());
            }
        });
        SharedPrefUtils.saveLoginUserInfo(getApplication());
    }

    /**
     * 更新用户信息</br>
     */
    private void updateUserInfo() {
        User user = new User();
        if (TextUtils.isEmpty(nikcnameEdit.getText().toString())){
            user.setUsername(nikcnameEdit.getHint().toString());
        }else{

        }
        if (TextUtils.isEmpty(nikcnameEdit.getText().toString())){
            user.setUsername(nikcnameEdit.getHint().toString());
        }
        user.setGender(gender);
        if (!TextUtils.isEmpty(setting_qqname.getText().toString())){
            user.setQQ(setting_qqname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_phone.getText().toString())){
//            user.setMobilePhoneNumber(setting_phone.getText().toString());
            user.setPassword(setting_phone.getText().toString());
            user.setUsername(setting_phone.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_schoolname.getText().toString())){
            user.setCurrentschool(setting_schoolname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_wantname.getText().toString())){
            user.setWantschool(setting_wantname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_yearname.getText().toString())){
            user.setWhichyear(setting_yearname.getText().toString());
        }
        if (!TextUtils.isEmpty(setting_typename.getText().toString())){
            user.setTypename(setting_typename.getText().toString());
        }

        BmobUser bmobUser = User.getCurrentUser(getApplicationContext());
        user.update(getApplicationContext(), bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showToast(getApplicationContext(),"更新成功");
                SharedPrefUtils.saveLoginUserInfo(getApplication());

            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.showToast(getApplicationContext(),s);
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }



}

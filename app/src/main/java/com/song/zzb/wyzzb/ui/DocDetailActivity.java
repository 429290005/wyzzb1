package com.song.zzb.wyzzb.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.bdocreader.BDocInfo;
import com.baidu.bdocreader.downloader.DocDownloadManager;
import com.baidu.bdocreader.downloader.DocDownloadableItem;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.CourseDscAdapter;
import com.song.zzb.wyzzb.bean.Course;
import com.song.zzb.wyzzb.bean.GoodsDetail;
import com.song.zzb.wyzzb.fragment.CourseDetailFragment;
import com.song.zzb.wyzzb.fragment.CourseListFragment;
import com.song.zzb.wyzzb.pay.GoodsPayDemoActivity;
import com.song.zzb.wyzzb.pay.PayDemoActivity;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.ToastUtil;
import com.song.zzb.wyzzb.util.xlistview.XListView;
import com.song.zzb.wyzzb.widget.RegisterPage;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static com.song.zzb.wyzzb.R.id.id_tab_test;

/**
 * Created by song on 2016/9/6.
 */
public class DocDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView docdetail_item_title, docdetail_item_downloadcount, docdetail_item_price,docdetail_unbuy_lj1,
            docdetail_item_purchase_price,doc_detail_dir,doc_detail_dir_title,abstract1,size,format,onlinetime,docdetail_unbuy_vip,docdetail_unbuy_lj;
    private ImageView img,actionbar_left_menu,docdetail_buy_onlineread,docdetail_buy_lj,docdetail_unbuy_zx;
    private Button button_counsel,button_add;
    LinearLayout docdetail_unbuy;
    SharedPreferences var3;
    BDocInfo docInfo;
    private String value1[]= new String[2];
    Bundle bundle =new Bundle();
    // Warning: 如果将该对象引用 放在Application子类中，要确认用户名发生改变时，重新调用getInstance，会得到一个新的对象
    DocDownloadManager downloadManager;
    SampleObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_detail);
        initView();
        initClick();


    }

    private void initClick() {

        actionbar_left_menu.setOnClickListener(this);
        docdetail_buy_onlineread.setOnClickListener(this);;//在线阅读
        docdetail_buy_lj.setOnClickListener(this);//立即下载
        docdetail_unbuy_zx.setOnClickListener(this);//咨询
        docdetail_unbuy_lj.setOnClickListener(this);//立
        docdetail_unbuy_lj1.setOnClickListener(this);//已完成时点击
        docdetail_unbuy_vip.setOnClickListener(this);//开通会员

    }

    private void initView() {
        docdetail_unbuy_lj1 = (TextView) findViewById(R.id.docdetail_unbuy_lj1);
        actionbar_left_menu = (ImageView) findViewById(R.id.actionbar_left_menu);
         docdetail_unbuy= (LinearLayout) findViewById(R.id.docdetail_unbuy);
        docdetail_item_title = (TextView) findViewById(R.id.docdetail_item_title);
        docdetail_item_downloadcount = (TextView) findViewById(R.id.docdetail_item_downloadcount);
        docdetail_item_price = (TextView) findViewById(R.id.docdetail_item_price);
        docdetail_item_purchase_price= (TextView) findViewById(R.id.docdetail_item_purchase_price);
        doc_detail_dir= (TextView) findViewById(R.id.doc_detail_dir);
        doc_detail_dir_title= (TextView) findViewById(R.id.doc_detail_dir_title);
        docdetail_buy_onlineread = (ImageView) findViewById(R.id.docdetail_buy_onlineread);//在线阅读
        docdetail_buy_lj= (ImageView) findViewById(R.id.docdetail_buy_lj);//立即下载
        docdetail_unbuy_zx= (ImageView) findViewById(R.id.docdetail_unbuy_zx);//咨询
        docdetail_unbuy_lj= (TextView) findViewById(R.id.docdetail_unbuy_lj);//立刻购买
        docdetail_unbuy_vip= (TextView) findViewById(R.id.docdetail_unbuy_vip);//立刻购买
        abstract1= (TextView) findViewById(R.id.abstract1);
        size= (TextView) findViewById(R.id.size);
        format= (TextView) findViewById(R.id.format);
        onlinetime = (TextView) findViewById(R.id.onlinetime);
        String userName = "haoxuesheng";
        downloadManager = DocDownloadManager.getInstance(this, userName);
        setUpLeftMenu(1);
        setUpTitle("资料详情");
        if(!getIntent().getExtras().isEmpty()) {
            String value[]= getIntent().getExtras().getStringArray("FlagID");
            /**
             * 特注：因为token有时间限制，以下字段请填写为您自己的信息，否则文档无法显示。
             */
            String host = "BCEDOC"; // 百度云传回的host
            String docId = value[11]; // 百度云传回的docId
            String docType = "pdf"; // 文档类型 doc/ppt/ppts等
            String token = "TOKEN"; // 百度云传回的token
            String thisDocDir = ""; // 文档下载器会返回文档的下载路径，到时通过docInfo.setLocalFileDir更新。
            // !!特注!!：以上目录，指定为空串""时表示在线浏览
            int totalPage =  Integer.parseInt(value[10]);; // 总页数，必须准确填写 否则在离线浏览时会有问题
            String docTitle = value[0];
            int startPage = 1; // 起始浏览页，最小值为1，请不要填入小于1的值

            docInfo = new BDocInfo(host, docId, docType, token)
                    .setLocalFileDir(thisDocDir)
                    .setTotalPage(totalPage)
                    .setDocTitle(docTitle)
                    .setStartPage(startPage);
//        docInfo.enablePagePreview(2);
            observer = new SampleObserver(docdetail_unbuy_lj1 , docInfo);
            DocDownloadableItem item = downloadManager.getDocDownloadableItemByDocId(docInfo.getDocId());
            if (item != null) {
                docdetail_buy_lj.setVisibility(View.GONE);
                docdetail_unbuy_lj1.setVisibility(View.VISIBLE);
                docdetail_unbuy_lj1.setText("已下载");
//            docdetail_unbuy_lj1.setEnabled(false);
                item.addDocObserver(observer);
                observer.update(item);
            } else {
                docdetail_unbuy_lj1.setText("state: not started");
            }
            docdetail_item_title.setText(value[0]);
            docdetail_item_downloadcount.setText(value[1]+"次下载");
            docdetail_item_price.setText(value[2]);
            docdetail_item_purchase_price.setText(value[3]);
            docdetail_item_purchase_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            if(value[8]!=null){
                doc_detail_dir.setText(value[8]);//目录
            }else{
                doc_detail_dir.setVisibility(View.GONE);//目录
                doc_detail_dir_title.setVisibility(View.GONE);
            }

            abstract1.setText("文件描述: "+value[0]);//文件描述
            size.setText("大小："+value[5]);
            format.setText("格式："+value[7]);
            onlinetime.setText("上线时间："+value[6]);
            /*
            如果是VIP的话，
             */
            var3 = getApplicationContext().getSharedPreferences("umeng_comm_user_info", 0);
//            ToastUtil.showToast(getApplicationContext(),var3.getString("vip", ""));
            if(var3.getString("vip", "").equals("1")){
                    //会员还需要购买
                if (!TextUtils.isEmpty(value[9])) {
                    if (value[9].equals("2")) {
                        docdetail_unbuy.setVisibility(View.VISIBLE);
                        docdetail_buy_onlineread.setVisibility(View.GONE);  //在线阅读
                        docdetail_buy_lj.setVisibility(View.GONE);//立即下载
                        docdetail_unbuy_zx.setVisibility(View.VISIBLE);//咨询
                        docdetail_unbuy_lj.setVisibility(View.VISIBLE);//立刻购买
                        docdetail_unbuy_lj.setText("立刻购买");
                        docdetail_unbuy_vip.setVisibility(View.GONE);//开通会员
                    } else {
                        //会员显示在线阅读，和下载按钮，如果goodstype=1则还要显示立即购买和咨询客服
                        docdetail_buy_onlineread.setVisibility(View.VISIBLE);  //在线阅读
                        docdetail_buy_lj.setVisibility(View.VISIBLE);//立即下载
                        docdetail_unbuy_zx.setVisibility(View.GONE);//咨询
                        docdetail_unbuy_lj.setVisibility(View.GONE);//立刻购买
                        docdetail_unbuy.setVisibility(View.GONE);
                        docdetail_unbuy_vip.setVisibility(View.GONE);//开通会员
                    }
                }
            }else{
               // 不是会员的情况
                ToastUtil.showToast(getApplicationContext(),"2");

                docdetail_unbuy.setVisibility(View.VISIBLE);
                docdetail_buy_onlineread.setVisibility(View.GONE);  //在线阅读
                docdetail_buy_lj.setVisibility(View.GONE);//立即下载
                docdetail_unbuy_zx.setVisibility(View.VISIBLE);//咨询
                docdetail_unbuy_lj.setVisibility(View.VISIBLE);//立刻购买
                docdetail_unbuy_vip.setVisibility(View.VISIBLE);//开通会员
                docdetail_unbuy_lj.setText("立刻购买");
                docdetail_unbuy_vip.setText("开通VIP");
            }

        }else{
           // Log.i("FlagID","为空");
        }

    }
    public boolean joinQQGroup(String key) {
        try {
        if(ActivityUtil.isQQClientAvailable(this)) {
            Intent intent = new Intent();
            intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
            startActivity(intent);
            return true;
        }else{
            Toast.makeText(this, "您还没有安装QQ，请先安装QQ客户端",Toast.LENGTH_SHORT).show();
            return false;
        }
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            ToastUtil.showToast(getApplicationContext(),"您还未登录或者安装手QQ，请登录QQ再试"+e);
            return false;
        }
    }
    public void onClick(View v) {
        DocDownloadableItem downloadItem = downloadManager.getDocDownloadableItemByDocId(docInfo.getDocId());
        switch (v.getId()) {

            case R.id.docdetail_buy_onlineread:
                if(SharedPrefUtils.isLogin(getApplication())){

                    if (downloadItem != null) {
                        downloadItem.addDocObserver(observer);
                        observer.update(downloadItem);
                    } else {

                    }
                    if (downloadItem != null && downloadItem.getStatus() == DocDownloadableItem.DownloadStatus.COMPLETED) {
                        Intent intent = new Intent(this, DocViewActivity.class);
                        intent.putExtra("ONE_DOC", docInfo);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Downloading Task is not Completed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    //打开注册页面
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setRegisterCallback(new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                    // 解析注册结果
                            if (result == SMSSDK.RESULT_COMPLETE) {

                            }
                        }
                    });
                    registerPage.show(getApplicationContext());
                }



                break;

            case R.id.docdetail_buy_lj:
                if(SharedPrefUtils.isLogin(getApplication())){
                docdetail_buy_lj.setVisibility(View.GONE);
                docdetail_unbuy_lj1.setVisibility(View.VISIBLE);
//                downloadManager.startOrResumeDownloader(docInfo.getDocId(), docInfo.getToken(), docInfo.getHost(),
//                        observer);
                }else{
                    //打开注册页面
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setRegisterCallback(new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                            // 解析注册结果
                            if (result == SMSSDK.RESULT_COMPLETE) {

                            }
                        }
                    });
                    registerPage.show(getApplicationContext());


                }

                break;
            case R.id.docdetail_unbuy_lj1:
                if(SharedPrefUtils.isLogin(getApplication())){
                 if(docdetail_unbuy_lj1.getText().toString().equals("已下载完成")){
                     docdetail_unbuy_lj1.setEnabled(true);
                       downloadItem = downloadManager.getDocDownloadableItemByDocId(docInfo.getDocId());

                if (downloadItem != null && downloadItem.getStatus() == DocDownloadableItem.DownloadStatus.COMPLETED) {
                    Intent intent = new Intent(this, DocViewActivity.class);
                    intent.putExtra("ONE_DOC", docInfo);

                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Downloading Task is not Completed", Toast.LENGTH_SHORT).show();
                }}
        }else{
            //打开注册页面
                RegisterPage registerPage = new RegisterPage();
                registerPage.setRegisterCallback(new EventHandler() {
                  public void afterEvent(int event, int result, Object data) {
                    // 解析注册结果
                    if (result == SMSSDK.RESULT_COMPLETE) {

                     }
                 }
                   });
                 registerPage.show(getApplicationContext());


        }
                break;
            case R.id.docdetail_unbuy_zx:
                joinQQGroup("GTQd1qoOt49wca50Sh64yE2WPaoC_4rF");
                break;
            case R.id.docdetail_unbuy_lj:
                if(SharedPrefUtils.isLogin(getApplication())){
                    if(var3.getString("vip", "").equals("1")){
                        docdetail_unbuy_vip.setVisibility(View.GONE);
                    }
                    Intent intent = new Intent();
                    if(!TextUtils.isEmpty(docdetail_item_price.getText().toString()) &&!TextUtils.isEmpty(docdetail_item_title.getText().toString()) ){
                        value1[0] = docdetail_item_price.getText().toString();//价格
                        value1[1] = docdetail_item_title.getText().toString();//商品标题
                        //  Log.i("flag的值", "" + value[0]+"/value[1]="+value[1]);
                        bundle.putStringArray("value", value1);
                        intent.setClass(getApplication(), GoodsPayDemoActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);

                }
                }else{
                    //打开注册页面
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setRegisterCallback(new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                            // 解析注册结果
                            if (result == SMSSDK.RESULT_COMPLETE) {


                            }
                        }
                    });
                    registerPage.show(getApplicationContext());
                }

                break;
            case R.id.actionbar_left_menu:
                finish();
                break;
            case R.id.docdetail_unbuy_vip://开通会员
                if(SharedPrefUtils.isLogin(getApplication())){
                    if(var3.getString("vip", "").equals("1")){
                        docdetail_unbuy_vip.setVisibility(View.GONE);
                    }
                    Intent intent = new Intent();
                    intent.setClass(getApplication(), PayDemoActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);


                }else{
                    //打开注册页面
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setRegisterCallback(new EventHandler() {
                        public void afterEvent(int event, int result, Object data) {
                            // 解析注册结果
                            if (result == SMSSDK.RESULT_COMPLETE) {


                            }
                        }
                    });
                    registerPage.show(getApplicationContext());
                }
                break;
            default:
                break;

        }

    }





    @Override
    public void onStart() {
        super.onStart();


    }


    @Override
    public void onStop() {
        super.onStop();


    }
}

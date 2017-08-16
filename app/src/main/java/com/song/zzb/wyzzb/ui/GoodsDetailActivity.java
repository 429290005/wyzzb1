package com.song.zzb.wyzzb.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.CourseDscAdapter;
import com.song.zzb.wyzzb.bean.Course;
import com.song.zzb.wyzzb.bean.GoodsDetail;
import com.song.zzb.wyzzb.fragment.CourseDetailFragment;
import com.song.zzb.wyzzb.fragment.CourseListFragment;
import com.song.zzb.wyzzb.pay.GoodsPayDemoActivity;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.xlistview.XListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by song on 2016/9/6.
 */
public class GoodsDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView title1, title1dsc, price, pricedsc1, pricepeople,text_status;
    private ImageView img,actionbar_left_menu,detail;
    private Button button_counsel,button_add;
    private int index;
    Bundle bundle =new Bundle();
    private int currentTabIndex;
    private Fragment[] fragments;
    private LinearLayout[] mTabs;
    private XListView list_study;
    private String value[]= new String[2];
    private CourseListFragment courseListFragment;          // 第一屏
    private CourseDscAdapter courseDscAdapter;
    private TextView id_test_tv, id_answer_tv;
    ImageLoader imageLoader = ImageLoader.getInstance();
    private LinearLayout id_tab_test, id_tab_answer;
    CourseDetailFragment courseDetailFragment;
    private ArrayList<Course> course = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsdetail);
        initView();
        initClick();


    }

    private void initClick() {
        id_tab_test.setOnClickListener(this);
        id_tab_answer.setOnClickListener(this);
        actionbar_left_menu.setOnClickListener(this);

    }

    private void initView() {
//        initProgressDialog("拼命加载中...");
        title1 = (TextView) findViewById(R.id.title1);
        title1dsc = (TextView) findViewById(R.id.title1dsc);
        price = (TextView) findViewById(R.id.price);
        img = (ImageView) findViewById(R.id.img);
        detail = (ImageView) findViewById(R.id.layout);
        actionbar_left_menu = (ImageView) findViewById(R.id.actionbar_left_menu);
        list_study = (XListView) findViewById(R.id.list_study);
        pricedsc1 = (TextView) findViewById(R.id.pricedsc1);
        pricepeople = (TextView) findViewById(R.id.pricepeople);
        title1 = (TextView) findViewById(R.id.title1);
        id_tab_test = (LinearLayout) findViewById(R.id.id_tab_test);
        id_tab_answer = (LinearLayout) findViewById(R.id.id_tab_answer);
        id_test_tv = (TextView) findViewById(R.id.id_test_tv);
        id_answer_tv = (TextView) findViewById(R.id.id_answer_tv);
        text_status= (TextView) findViewById(R.id.text_status);
        button_counsel=(Button) findViewById(R.id.button_counsel);
        button_add=(Button) findViewById(R.id.button_add);
        mTabs = new LinearLayout[]{id_tab_test, id_tab_answer};
        id_tab_test.setSelected(true);
        courseDetailFragment = new CourseDetailFragment();
        courseListFragment = new CourseListFragment();
        fragments = new Fragment[]{courseListFragment,courseDetailFragment};
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                value[0] = text_status.getText().toString();//价格
                value[1] = title1.getText().toString();//商品描述
                //  Log.i("flag的值", "" + value[0]+"/value[1]="+value[1]);
                bundle.putStringArray("value", value);
                intent.setClass(getApplication(), GoodsPayDemoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=1463443681&version=1")));
            }
        });
        button_counsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Dialog builder = new Dialog(GoodsDetailActivity.this, R.style.dialog);
//                builder.setContentView(R.layout.answer_dialog);
//                TextView confirm_btn = (TextView) builder.findViewById(R.id.dialog_sure);
//                TextView content = (TextView) builder.findViewById(R.id.dialog_content);
//                TextView cancel_btn = (TextView) builder.findViewById(R.id.dialog_cancle);
//
//                content.setText("咨询QQ:1463443681");
//                confirm_btn.setText("确定");
//                cancel_btn.setText("开通VIP");
//                cancel_btn.setVisibility(View.GONE);
//                //cancel_btn.setText("确认");
//                confirm_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        builder.dismiss();
//                    }
//                });
//
//                cancel_btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        builder.dismiss();

//                        Intent intent = new Intent();
//
//                        intent.setClass(mcontext, PayDemoActivity.class);
//
//                        mcontext.startActivity(intent);
//
//                    }
//                });
//                builder.setCanceledOnTouchOutside(true);// 设置点击Dialog外部任意区域关闭Dialog
//
//                builder.show();
                joinQQGroup("GTQd1qoOt49wca50Sh64yE2WPaoC_4rF");
            }
        });
        list_study.setPullRefreshEnable(false);
        list_study.setPullLoadEnable(false);
        list_study.setItemsCanFocus(true);
        setUpLeftMenu(1);

        if(!getIntent().getExtras().isEmpty()) {
         //   Log.i("FlagID",""+getIntent().getExtras().getInt("FlagID"));
            queryData(getIntent().getExtras().getInt("FlagID"));

        }else{
           // Log.i("FlagID","为空");
        }

    }
    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }
    public void onClick(View v) {
//        resetTextView();
        switch (v.getId()) {
            case R.id.id_tab_test:
                index = 0;
                resetTextView(0);
                break;
            case R.id.id_tab_answer:
                index = 1;
                resetTextView(1);
                break;
            case R.id.actionbar_left_menu:
                finish();
                break;
            default:
                break;

        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager()
                    .beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment1_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        // 把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    /**
     * 重置颜色
     */
    private void resetTextView(int i) {
        switch (i) {

            case 0:
               // id_test_tv.setTextColor(getResources().getColor(R.color.color_bottom_text_press));
                id_answer_tv.setTextColor(Color.BLACK);
                break;
            case 1:
                id_test_tv.setTextColor(Color.BLACK);
                //id_answer_tv.setTextColor(getResources().getColor(R.color.color_bottom_text_press));

                break;

            default:
                break;
        }


    }

    private void queryData(int value0) {
        // TODO Auto-generated method stub
        BmobQuery<GoodsDetail> goodsdetail = new BmobQuery<GoodsDetail>();
        //按照时间降序
       // Log.i("flag1",""+String.valueOf(value0));
        goodsdetail.addWhereEqualTo("flag", String.valueOf(value0));

        goodsdetail.findObjects(GoodsDetailActivity.this, new FindListener<GoodsDetail>() {
            @Override
            public void onSuccess(List<GoodsDetail> data) {
                // TODO Auto-generated method stub
                if (data != null && !data.isEmpty()) {
                    setUpTitle(data.get(0).getTitle());
                    title1.setText(data.get(0).getTitledsc1());
                    title1dsc.setText(data.get(0).getTitledsc2());
                    price.setText(data.get(0).getPrice());
                    text_status.setText(data.get(0).getPrice());
                    pricedsc1.setText(data.get(0).getPricedsc());
                    pricepeople.setText(data.get(0).getPeople());
                    imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
                    imageLoader.displayImage(data.get(0).getImgUrl(), img);
                    courseDscAdapter = new CourseDscAdapter(GoodsDetailActivity.this, data.get(0).getGoodsgive());
                    courseDscAdapter.notifyDataSetChanged();
                    list_study.setAdapter(courseDscAdapter);
                    ActivityUtil.setListViewHeightBasedOnChildren(list_study);
                    Bundle bundle2 = new Bundle();
                    course.addAll(data.get(0).getCourse());
                    bundle2.putSerializable("core",course);
                    Bundle bundle3 = new Bundle();
                    String detail[]= new String[5];
                    detail[0]= data.get(0).getImgdetail().getFilename();
                    detail[1]=data.get(0).getImgdetailurl();
                    detail[2]=data.get(0).getVideodetailurl();
                   // Log.i("detail",""+detail[2]);
                    detail[3]=data.get(0).getVideodsc();
                    detail[4]=data.get(0).getVideoauther();
                    bundle3.putStringArray("detail",detail);
                    courseDetailFragment.setArguments(bundle2);
                    courseListFragment.setArguments(bundle3);
                    resetTextView(0);
                    // 添加显示第一个fragment
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment1_container,courseListFragment).
                            add(R.id.fragment1_container, courseDetailFragment).hide(courseDetailFragment).show(courseListFragment).commitAllowingStateLoss();
                    dismissDialog();
                }
            }

            @Override
            public void onError(int errorCode, String errorString) {
                // TODO Auto-generated method stub
                dismissDialog();
            }
        });
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

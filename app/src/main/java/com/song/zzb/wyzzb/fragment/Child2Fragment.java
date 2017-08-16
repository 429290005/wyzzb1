package com.song.zzb.wyzzb.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.maning.library.SwitcherView;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.FragmentAdapter;
import com.song.zzb.wyzzb.ui.HelpActivity;
import com.song.zzb.wyzzb.ui.PersonInforActivity;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.widget.RegisterPage;
import com.song.zzb.wyzzb.widget.RoundImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A simple {@link Fragment} subclass.
 * 2题库
 */
public class Child2Fragment extends Fragment implements
        View.OnClickListener{
    public static final String TAG = Child2Fragment.class.getSimpleName();
    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private List<Map<String, String>> menuData1;
    private ListView  popListView;
    private PopupWindow popMenu;
    private SimpleAdapter menuAdapter1;
    private LinearLayout product;
    private TextView productTv;
    private String kemu;
    private int menuIndex = 0;
    private TextView id_test_tv,id_answer_tv, mTabFriendTv;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private FragmentAdapter mFragmentAdapter;
    /**
     * Fragment
     */
    private Child2_tiku_Fragment tikuFg;
    private Child2_Video_Fragment VideoFg;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;
    public static int STICKY_HEIGHT1;
    public static int STICKY_HEIGHT2;
    public static Fragment newInstance() {
        Child2Fragment fragment = new Child2Fragment();

        return fragment;
    }

    public Child2Fragment() {
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
            case R.id.supplier_list_product:
                productTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(menuAdapter1);
                popMenu.showAsDropDown(product, 0, 2);
                menuIndex = 0;
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
        return inflater.inflate(R.layout.fragment_child2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwitcherView switcherView = (SwitcherView) view.findViewById(R.id.switcherView);
        id_test_tv = (TextView)  view.findViewById(R.id.id_test_tv);
        id_answer_tv = (TextView)  view.findViewById(R.id.id_answer_tv);
        mTabLineIv = (ImageView) view.findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager)  view.findViewById(R.id.id_page_vp);
        product = (LinearLayout)view. findViewById(R.id.supplier_list_product);
        productTv = (TextView)view. findViewById(R.id.supplier_list_product_tv);
        product.setOnClickListener(this);
        ArrayList<String> strs = new ArrayList<>();
        strs.add("哎呦，不错哦");
        strs.add("你知道我是谁吗你知道我是谁吗你知道我是谁吗");
        strs.add("哈哈哈");
        strs.add("1111111111111");
        initPopMenu();
        //设置数据源
        switcherView.setResource(strs);
        //开始滚动
        switcherView.startRolling();

        //监听点击事件
        switcherView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取当前的展示的值
//                Toast.makeText(MainActivity.this, switcherView.getCurrentItem(), Toast.LENGTH_SHORT).show();
//                //获取当前展示的集合的index
//                Toast.makeText(MainActivity.this, switcherView.getCurrentItem(), Toast.LENGTH_SHORT).show();
            }
        });
//        user_name_tv_nologin= (TextView) view.findViewById(R.id.user_name_tv_nologin);
//
//
//
//
//        user_have_login.setOnClickListener(this);

    }
    private void initPopMenu() {
        menuData1 = new ArrayList<Map<String, String>>();
        String[] menuStr1 = new String[] { "计算机", "英语", "语文", "数学" };
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<String, String>();
            map1.put("name", menuStr1[i]);
            menuData1.add(map1);
        }
        View contentView = View.inflate(getContext(), R.layout.popwin_supplier_list,
                null);
        popMenu = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        popMenu.setOutsideTouchable(true);
        popMenu.setBackgroundDrawable(new BitmapDrawable());
        popMenu.setFocusable(true);
        popMenu.setAnimationStyle(R.style.popwin_anim_style);
        popMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                productTv.setTextColor(Color.parseColor("#ffffff"));

            }
        });

        popListView = (ListView) contentView
                .findViewById(R.id.popwin_supplier_list_lv);
        contentView.findViewById(R.id.popwin_supplier_list_bottom)
                .setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        popMenu.dismiss();
                    }
                });
        menuAdapter1 = new SimpleAdapter(getContext(), menuData1,
                R.layout.item_listview_popwin, new String[] { "name" },
                new int[] { R.id.listview_popwind_tv });

        popListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
                                    long arg3) {
                popMenu.dismiss();
                if (menuIndex == 0) {
                    kemu= menuData1.get(pos).get("name");

                    productTv.setText(kemu);
//                   Toast.makeText(MainActivity.this, currentProduct, Toast.LENGTH_SHORT).show();
                }
//                queryData(productTv.getText().toString(),sortTv.getText().toString(),activityTv.getText().toString());

            }
        });
    }
    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {

        DisplayMetrics dpMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);

        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                .getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }
    private void init() {

        tikuFg = new Child2_tiku_Fragment();
        VideoFg = new Child2_Video_Fragment();

        mFragmentList.add(tikuFg);
        mFragmentList.add(VideoFg);


        mFragmentAdapter = new FragmentAdapter(
                this.getChildFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv
                        .getLayoutParams();

                Log.e("offset:", offset + "");
                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */

                if (currentIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));

                } else if (currentIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                } else if (currentIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset)
                            * (screenWidth * 1.0 / 3) + currentIndex
                            * (screenWidth / 3));
                }
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        id_test_tv.setTextColor(Color.BLUE);

                        break;
                    case 1:
                        id_answer_tv.setTextColor(Color.BLUE);
                        break;

                }
                currentIndex = position;
            }
        });

    }
    /**
     * 重置颜色
     */
    private void resetTextView() {
        id_test_tv.setTextColor(Color.BLACK);
        id_answer_tv.setTextColor(Color.BLACK);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        initTabLineWidth();
        if (savedInstanceState == null) {

        } else {


        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }



    public void onDestroy(){


        super.onDestroy();
    }
    public interface StickyScrollCallBack {
        public void onScrollChanged(int scrollY);
        public int getCurrentViewpagerItem();
    }

    public interface ViewPagerStateListener {
        public void onPageScrollStateChanged(int state);
    }
}

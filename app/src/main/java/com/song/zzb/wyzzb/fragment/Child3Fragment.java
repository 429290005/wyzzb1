package com.song.zzb.wyzzb.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.Child2FragmentAdapter;
import com.song.zzb.wyzzb.adapter.FragmentAdapter;
import com.song.zzb.wyzzb.widget.TabChild2Layout;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Child3Fragment extends Fragment {

    public static final String TAG = Child3Fragment.class.getSimpleName();

    private FragmentNavigator mNavigator;

    private TabChild2Layout tabLayout;
    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
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
    public Child3Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new FragmentNavigator(getChildFragmentManager(), new Child2FragmentAdapter(), R.id.childContainer);
        mNavigator.setDefaultPosition(0);
        mNavigator.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child3, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_test_tv = (TextView)  view.findViewById(R.id.id_test_tv);
        id_answer_tv = (TextView)  view.findViewById(R.id.id_answer_tv);
        mTabLineIv = (ImageView) view.findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager)  view.findViewById(R.id.id_page_vp);
        init();
        initTabLineWidth();
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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }




    public static Fragment newInstance( ) {
        Fragment fragment = new Child3Fragment();
        return fragment;
    }
}

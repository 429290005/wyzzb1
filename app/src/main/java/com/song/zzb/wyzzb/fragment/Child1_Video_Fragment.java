package com.song.zzb.wyzzb.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.Child1_Shop_Adapter;
import com.song.zzb.wyzzb.adapter.Child1_Video_Adapter;
import com.song.zzb.wyzzb.bean.CourseList;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.xlistview.XListView;

import java.lang.ref.WeakReference;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Child1_Video_Fragment extends Fragment {
    private Child1_Video_Adapter studyMainAdapter;
    public static final String TAG = Child1_Video_Fragment.class.getSimpleName();

    public static final String EXTRA_TEXT = "extra_text";

    private static final int MOCK_LOAD_DATA_DELAYED_TIME = 2000;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private WeakRunnable mRunnable = new WeakRunnable(this);
    private XListView listView;

    private LinearLayout l_emptylayout;

    public Child1_Video_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.child1_video_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (XListView)view.findViewById(R.id.list_study);

        l_emptylayout=(LinearLayout) view.findViewById(R.id.l_emptylayout);
        l_emptylayout=(LinearLayout)view.findViewById(R.id.l_emptylayout);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        listView.setItemsCanFocus(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {

            loadData();
        } else {

            bindData();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
         ;
    }

    @Override
    public void onDestroyView() {
        sHandler.removeCallbacks(mRunnable);

        super.onDestroyView();
    }



    private void bindData() {
        boolean isLogin = SharedPrefUtils.isLogin(getActivity());

    }

    /**
     * mock load data
     */
    private void loadData() {

        queryData();
    }

    private static class WeakRunnable implements Runnable {

        WeakReference<Child1_Video_Fragment> mMainFragmentReference;

        public WeakRunnable(Child1_Video_Fragment mainFragment) {
            this.mMainFragmentReference = new WeakReference<Child1_Video_Fragment>(mainFragment);
        }

        @Override
        public void run() {
            Child1_Video_Fragment mainFragment = mMainFragmentReference.get();
            if (mainFragment != null && !mainFragment.isDetached()) {

                mainFragment.bindData();
            }
        }
    }

    private void queryData() {
        // TODO Auto-generated method stub
        BmobQuery<CourseList> newsQuery = new BmobQuery<CourseList>();
        //按照时间降序
        newsQuery.addWhereEqualTo("display", 3);
        newsQuery.order("orderby");
//         //判断是否有缓存，该方法必须放在查询条件（如果有的话）都设置完之后再来调用才有效，就像这里一样。
//        boolean isCache = newsQuery.hasCachedResult(this.getContext(),CourseList.class);
//        if(isCache){
//            if(ActivityUtil.hasNetwork(this.getContext())){
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则
//            }else{
//            //--此为举个例子，并不一定按这种方式来设置缓存策略
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
//            }
//
//        }else{
//            newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
//        }
        newsQuery.findObjects(getActivity(), new FindListener<CourseList>() {

            @Override
            public void onSuccess(List<CourseList> data) {
                // TODO Auto-generated method stub
                if (data != null) {
                    l_emptylayout.setVisibility(View.GONE);
                    studyMainAdapter = new Child1_Video_Adapter(getActivity(), data);

                    studyMainAdapter.notifyDataSetChanged();
                    listView.setAdapter(studyMainAdapter);
                    ActivityUtil.setListViewHeightBasedOnChildren(listView);
                }
            }

            @Override
            public void onError(int errorCode, String errorString) {
                // TODO Auto-generated method stub

            }
        });
    }
}

package com.song.zzb.wyzzb.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.CourseListAdapter;
import com.song.zzb.wyzzb.bean.Course;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.xlistview.XListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2016/2/12.
 */
public class CourseDetailFragment extends BaseFragment {

    private CourseListAdapter courseListAdapter;
    private XListView listView;
    private  List<Course> videocores = new ArrayList<>();
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Bundle bundle=getArguments();
        if(bundle!=null){
            videocores.addAll((List)bundle.getSerializable("core"));
            Log.i("name", "" + bundle.getSerializable("core"));
            Log.i("name", "" + videocores);
        }

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.fragment_courselist, container, false);
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

//        queryData();

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
        listView = (XListView)findViewById(R.id.list_study);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        listView.setItemsCanFocus(true);
        courseListAdapter = new CourseListAdapter(this.getContext(),videocores);
        courseListAdapter.notifyDataSetChanged();
        listView.setAdapter(courseListAdapter);
        ActivityUtil.setListViewHeightBasedOnChildren(listView);

    }
//    private void queryData() {
//        // TODO Auto-generated method stub
//        BmobQuery<videocore> newsQuery = new BmobQuery<videocore>();
//        //按照时间降序
////        newsQuery.order("createdAt");
////判断是否有缓存，该方法必须放在查询条件（如果有的话）都设置完之后再来调用才有效，就像这里一样。
//        boolean isCache = newsQuery.hasCachedResult(this.getContext(),videocore.class);
//        if(isCache){
//            if(ActivityUtil.hasNetwork(this.getContext())){
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则
//            }else{
//                //--此为举个例子，并不一定按这种方式来设置缓存策略
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
//            }
//
//        }else{
//            newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
//        }
//        newsQuery.findObjects(getActivity(), new FindListener<videocore>() {
//
//            @Override
//            public void onSuccess(List<videocore> data) {
//                // TODO Auto-generated method stub
//                if (data != null) {
////                    VideoCourseActivity videoCourseActivity =new VideoCourseActivity();
//////                    courseList.addAll(data);
////                    ((VideoCourseActivity)getActivity()).getVideocores().toString();
////                  Log.i("videocore", videoCourseActivity.getVideocores().toString());
////                    videoTestAdapter = new VideoTestAdapter(getActivity(),videoCourseActivity.getVideocores());
////                    videoTestAdapter.notifyDataSetChanged();
////                    listView.setAdapter(videoTestAdapter);
////                    ActivityUtil.setListViewHeightBasedOnChildren(listView);
//                }
//            }
//
//            @Override
//            public void onError(int errorCode, String errorString) {
//                // TODO Auto-generated method stub
//
//            }
//        });
//    }
}

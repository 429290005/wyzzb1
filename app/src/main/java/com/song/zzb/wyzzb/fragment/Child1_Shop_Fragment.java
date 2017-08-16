package com.song.zzb.wyzzb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.Child1_Shop_Adapter;
import com.song.zzb.wyzzb.bean.Advertisement;
import com.song.zzb.wyzzb.bean.CourseList;
import com.song.zzb.wyzzb.ui.BanMaWebActivity;
import com.song.zzb.wyzzb.ui.GoodsDetailActivity;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.xlistview.XListView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
/**
 * Created by song on 2016/2/11.
 */
public class Child1_Shop_Fragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
  private Child1_Shop_Adapter studyMainAdapter;
    private List<CourseList> courseList=new ArrayList<CourseList>();
    private XListView listView;

    private LinearLayout  l_emptylayout;
    private HashMap<String,String> url_maps = new HashMap<String, String>();
    // 轮播banner的数据
    private List<Advertisement> adList = new ArrayList<Advertisement>();
    private SliderLayout mDemoSlider;

    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View v = inflater.inflate(R.layout.child1_shop_fragment, container, false);
        listView = (XListView)v.findViewById(R.id.list_study);
        mDemoSlider = (SliderLayout)v.findViewById(R.id.slider);
        l_emptylayout=(LinearLayout)v. findViewById(R.id.l_emptylayout);
        l_emptylayout=(LinearLayout)v. findViewById(R.id.l_emptylayout);
        return v;
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


        initClick();
    }
    protected  void initClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               CourseList courseList=(CourseList) parent.getItemAtPosition(position);
                if(courseList.getVideourl().equals("1")){
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt("FlagID",courseList.getFlagID());//课程标识
                    intent.setClass(getActivity(), GoodsDetailActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }



            }
        });

    }
    protected void initViews(){


        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(3000);
        mDemoSlider.addOnPageChangeListener(this);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        listView.setItemsCanFocus(true);


        l_emptylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    queryData(); // 如果没有缓存的话，则
                    getBannerAd();
                    l_emptylayout.setVisibility(View.GONE);

            }
        });
        if(ActivityUtil.hasNetwork(getContext())){
            queryData(); // 如果没有缓存的话，则
            getBannerAd();
            l_emptylayout.setVisibility(View.GONE);
        }else{
            l_emptylayout.setVisibility(View.VISIBLE);
        }

    }
    //得到广告数据
    public void getBannerAd() {
        // TODO Auto-generated method stub
        BmobQuery<Advertisement> newsQuery = new BmobQuery<Advertisement>();
        //按照时间降序
//        newsQuery.order("createdAt");
        newsQuery.addWhereEqualTo("orderby", 1);
        //判断是否有缓存，该方法必须放在查询条件（如果有的话）都设置完之后再来调用才有效，就像这里一样。
//        boolean isCache = newsQuery.hasCachedResult(getContext(),Advertisement.class);
//        if(isCache){
//            if(ActivityUtil.hasNetwork(getContext())){
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则
//            }else{
//                //--此为举个例子，并不一定按这种方式来设置缓存策略
//                newsQuery.setCachePolicy(BmobQuery.CachePolicy.CACHE_ELSE_NETWORK);    // 如果有缓存的话，则设置策略为CACHE_ELSE_NETWORK
//            }
//
//        }else{
//            newsQuery.setCachePolicy(BmobQuery.CachePolicy.NETWORK_ELSE_CACHE);    // 如果没有缓存的话，则设置策略为NETWORK_ELSE_CACHE
//        }
        newsQuery.findObjects(getContext(), new FindListener<Advertisement>() {

            @Override
            public void onSuccess(List<Advertisement> data) {
                // TODO Auto-generated method stub
                if (data != null) {
                    l_emptylayout.setVisibility(View.GONE);
                    for (int i = 0; i < data.size(); i++) {
                        TextSliderView textSliderView = new TextSliderView(getContext());
                        Advertisement advertisement = new Advertisement();
                        advertisement.setFeedId(data.get(i).getFeedId());
                        advertisement.setDate(data.get(i).getDate());
                        advertisement.setTitle(data.get(i).getTitle());
                        advertisement.setTopicFrom(data.get(i).getTopicFrom());
                        advertisement.setTopic(data.get(i).getTopic());
                        advertisement.setImgUrl(data.get(i).getImgUrl());
                        advertisement.setIsAd(data.get(i).getIsAd());
                        adList.add(advertisement);
                        // Log.i("url_maps", i+"???"+data.get(i).getIsAd());
                        url_maps.put(data.get(i).getTitle(), data.get(i).getImgUrl());
                        // initialize a SliderLayout
                        textSliderView
                                .description(data.get(i).getTitle())
                                .image(data.get(i).getImgUrl())
                                .setScaleType(BaseSliderView.ScaleType.Fit);
                        String[] value = new String[2];
//                        Bundle bundle = getIntent().getExtras();
//                        value = bundle.getIntArray("value");
//                        Intent intent = new Intent();
                        value[0] = data.get(i).getFeedId();
                        value[1] = String.valueOf(data.get(i).getIsAd());

//                        bundletitle.putString("title", tKuLtist1.getKuTitle());
//                        Log.i("flag", "" + value);
//                        intent.setClass(getApplicationContext(),ComRealExameActivity.class);
//                        intent.putExtras(bundle);
//                        intent.putExtras(bundletitle);
//                        startActivity(intent);
//                        Bundle bundle =new Bundle();
//                        bundle.putStringArray();
//                        bundle.putString("feedId",data.get(i).getFeedId());
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle().putStringArray("value", value);
                        textSliderView.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
//                                ToastUtil.showToast(getContext(),slider.getUrl()+"、、、"+slider.getBundle().get("value"));
                              String[] value = new String[2];
                                value =slider.getBundle().getStringArray("value");
                                if(value[1].equals("2")){//为浏览器
                                    Intent intent = new Intent();
                                    intent.putExtra("url", value[0]);
                                    intent.setClass(getActivity(),BanMaWebActivity.class);
                                    startActivity(intent);
                                }else if(value[1].equals("1")){//课程详情页
                                    Intent intent = new Intent();
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("FlagID",Integer.parseInt(value[0]));//课程标识
                                    intent.setClass(getActivity(), GoodsDetailActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }else if(value[1].equals("3")){//feed
                                    //  Log.i("value11",""+value[0]);
//                                    final String feedid=value[0];
//                                    mCommSDK.fetchFeedWithId(feedid, new Listeners.FetchListener<FeedItemResponse>() {
//                                        @Override
//                                        public void onStart() {
//                                            Log.i("feedItem",""+feedItem);
//                                        }
//                                        @Override
//                                        public void onComplete(FeedItemResponse feedItemResponse) {
//                                            feedItem =feedItemResponse.result;
//                                            Intent intent = new Intent(getActivity(), FeedDetailActivity.class);
//                                            intent.putExtra(Constants.TAG_FEED, feedItem);
//                                            Log.i("feedItem",""+feedid);
//                                            startActivity(intent);
//                                            Log.i("feedItem",""+feedItem);
//                                        }
//                                    });
//                                    		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//
//
//			}
//		}, 500);


//                                    Intent intent = new Intent();
//                                    ComponentName componentName = new ComponentName(getActivity(), TopicDetailActivity.class);
//                                    intent.setComponent(componentName);
//                                    intent.putExtra(Constants.TAG_TOPIC, mTopic);
//                                    startActivity(intent);
                                }

                            }
                        });
                        mDemoSlider.addSlider(textSliderView);


                    }

                }else{
                    //l_emptylayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int errorCode, String errorString) {
                // TODO Auto-generated method stub

            }
        });
    }
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
//       Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
//        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
    private void queryData() {
        // TODO Auto-generated method stub
        BmobQuery<CourseList> newsQuery = new BmobQuery<CourseList>();
        //按照时间降序
        newsQuery.addWhereEqualTo("display", 1);
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
                    courseList.addAll(data);
                    Log.i("News", courseList.toString());
                    studyMainAdapter = new Child1_Shop_Adapter(getActivity(), courseList);
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

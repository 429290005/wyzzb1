package com.song.zzb.wyzzb.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.Child1_Download_Adapter;

import com.song.zzb.wyzzb.bean.CourseList;
import com.song.zzb.wyzzb.bean.downloadziliao;
import com.song.zzb.wyzzb.ui.DocDetailActivity;
import com.song.zzb.wyzzb.ui.GoodsDetailActivity;
import com.song.zzb.wyzzb.util.ActivityUtil;
import com.song.zzb.wyzzb.util.SharedPrefUtils;
import com.song.zzb.wyzzb.util.ToastUtil;
import com.song.zzb.wyzzb.util.xlistview.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class Child1_Download_Fragment extends Fragment implements View.OnClickListener {
    private ListView  popListView;
    private ProgressBar progressBar;
    private List<Map<String, String>> menuData1, menuData2, menuData3;
    private PopupWindow popMenu;
    private SimpleAdapter menuAdapter1, menuAdapter2, menuAdapter3;
    private LinearLayout product, sort, activity;
    private TextView productTv, sortTv, activityTv;
    private String hot, jieduan, kemu;
    private int menuIndex = 0;
    private Intent intent;
    public static final String TAG = Child1_Download_Fragment.class.getSimpleName();
    private XListView listView;
    private Child1_Download_Adapter studyMainAdapter;
    public static Fragment newInstance(String text) {
        Child1_Download_Fragment fragment = new Child1_Download_Fragment();

        return fragment;
    }

    public Child1_Download_Fragment() {
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
            case R.id.supplier_list_sort:
                sortTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(menuAdapter2);
                popMenu.showAsDropDown(product, 0, 2);
                menuIndex = 1;
                break;
            case R.id.supplier_list_activity:
                activityTv.setTextColor(Color.parseColor("#39ac69"));
                popListView.setAdapter(menuAdapter3);
                popMenu.showAsDropDown(product, 0, 2);
                menuIndex = 2;
                break;

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.child1_download_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (XListView) view.findViewById(R.id.supplier_list_lv);
        product = (LinearLayout)view. findViewById(R.id.supplier_list_product);
        sort = (LinearLayout) view.findViewById(R.id.supplier_list_sort);
        activity = (LinearLayout) view.findViewById(R.id.supplier_list_activity);
        productTv = (TextView)view. findViewById(R.id.supplier_list_product_tv);
        sortTv = (TextView)view. findViewById(R.id.supplier_list_sort_tv);
        activityTv = (TextView)view. findViewById(R.id.supplier_list_activity_tv);
        listView.setPullRefreshEnable(false);
        listView.setPullLoadEnable(false);
        listView.setItemsCanFocus(true);
        product.setOnClickListener(this);
        sort.setOnClickListener(this);
        activityTv.setOnClickListener(this);
        activity.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            initPopMenu();
            queryData(productTv.getText().toString(),sortTv.getText().toString(),activityTv.getText().toString());

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




    private void initMenuData() {
        menuData1 = new ArrayList<Map<String, String>>();
        String[] menuStr1 = new String[] { "计算机", "英语", "语文", "数学" };
        Map<String, String> map1;
        for (int i = 0, len = menuStr1.length; i < len; ++i) {
            map1 = new HashMap<String, String>();
            map1.put("name", menuStr1[i]);
            menuData1.add(map1);
        }

        menuData2 = new ArrayList<Map<String, String>>();
        String[] menuStr2 = new String[] { "基础班","强化班" ,"冲刺班","押题班"};
        Map<String, String> map2;
        for (int i = 0, len = menuStr2.length; i < len; ++i) {
            map2 = new HashMap<String, String>();
            map2.put("name", menuStr2[i]);
            menuData2.add(map2);
        }

        menuData3 = new ArrayList<Map<String, String>>();
        String[] menuStr3 = new String[] {"推荐","最新","最热"};
        Map<String, String> map3;
        for (int i = 0, len = menuStr3.length; i < len; ++i) {
            map3 = new HashMap<String, String>();
            map3.put("name", menuStr3[i]);
            menuData3.add(map3);
        }
    }

    private void initPopMenu() {
        initMenuData();
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
                productTv.setTextColor(Color.parseColor("#5a5959"));
                sortTv.setTextColor(Color.parseColor("#5a5959"));
                activityTv.setTextColor(Color.parseColor("#5a5959"));
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
        menuAdapter2 = new SimpleAdapter(getContext(), menuData2,
                R.layout.item_listview_popwin, new String[] { "name" },
                new int[] { R.id.listview_popwind_tv });
        menuAdapter3 = new SimpleAdapter(getContext(), menuData3,
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
                } else if (menuIndex == 1) {
                    jieduan = menuData2.get(pos).get("name");

                    sortTv.setText(jieduan);
//                    Toast.makeText(MainActivity.this, currentSort, Toast.LENGTH_SHORT).show();
                } else {
                    hot = menuData3.get(pos).get("name");
                    activityTv.setText(hot);
//                    Toast.makeText(MainActivity.this, currentActivity, Toast.LENGTH_SHORT).show();
                }
                queryData(productTv.getText().toString(),sortTv.getText().toString(),activityTv.getText().toString());

            }
        });
    }
    /**
     * mock load data
     */

    private void queryData(String kemu1,String jieduan1,String redu) {

        // TODO Auto-generated method stub
        BmobQuery<downloadziliao> newsQuery = new BmobQuery<downloadziliao>();
        //按照时间降序
         newsQuery.addWhereContains("kemu", kemu1);
         newsQuery.addWhereContains("jieduan",jieduan1);
         newsQuery.addWhereContains("hot",redu);
        if(redu.equals("推荐"))newsQuery.addWhereContains("hot","推荐");
        if(redu.equals("最热")) newsQuery.order("count");
        if(redu.equals("最新")) newsQuery.order("createdAt");
        ToastUtil.showToast(getContext(),"kemu="+kemu1+"jieduan="+jieduan1+"hot"+redu);
//        newsQuery.order("orderby");
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
        newsQuery.findObjects(getActivity(), new FindListener<downloadziliao>() {

            @Override
            public void onSuccess(List<downloadziliao> data) {
                // TODO Auto-generated method stub
                if (data != null) {

                    studyMainAdapter = new Child1_Download_Adapter(getActivity(), data);
                    studyMainAdapter.notifyDataSetChanged();
                    listView.setAdapter(studyMainAdapter);
                    ActivityUtil.setListViewHeightBasedOnChildren(listView);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            downloadziliao courseList=(downloadziliao) parent.getItemAtPosition(position);
                             String value[] =new String[12];
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                value[0]=courseList.getTitle();
                                value[1]=String.valueOf(courseList.getCount());
                                value[2]=courseList.getPrice();
                                value[3]=courseList.getYuanprice();
                                //value[4]=courseList.getAuthor1();
                                value[5]=courseList.getSize();
                                value[6]=courseList.getLinetime();
                                value[7]=courseList.getFormat();
                                 value[8]=courseList.getAbstract1();
                                value[9]=courseList.getGoodstype();//等于2时会员也需要购买
                                value[10]=String.valueOf(courseList.getTotalPage());//总页数
                                value[11]=courseList.getDocId();//文档ID
                                  bundle.putStringArray("FlagID",value);
                                intent.setClass(getActivity(), DocDetailActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            downloadziliao ziliao  = new downloadziliao();
                            ziliao.increment("count"); // 人数递增1
                            ziliao.update(getContext(), courseList.getObjectId(), new UpdateListener() {
                                @Override
                                public void onSuccess() {

                                   Log.i("perNumber", "更新成功：");
                                }

                                @Override
                                public void onFailure(int i, String s) {
                                     Log.i("perNumber", "更新失败：" + s);
                                }
                            });
                        }
                    });

                }
            }

            @Override
            public void onError(int errorCode, String errorString) {
                // TODO Auto-generated method stub

            }
        });
    }

}

package com.song.zzb.wyzzb.util;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;

import java.util.List;

import static anet.channel.util.Utils.context;


/**
 * Created by song on 2016/2/12.
 */
public class ActivityUtil {
    /**
     * 判断网络连接是否可用
     * @param context
     * @return
     */
    public static boolean hasNetwork(Context context) {
        // 获取手机所有连接管理对象（包括wifi，net等连接的管理）
        ConnectivityManager connectivity=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivity != null) {
            // 获取网络连接管理的对象
            NetworkInfo info=connectivity.getActiveNetworkInfo();
            if(info != null && info.isConnected()) {
                // 判断当前网络是否已经连接
                if(info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;

    }
    /**
     * 判断 用户是否安装QQ客户端
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;

                if (pn.equalsIgnoreCase("com.tencent.qqlite") || pn.equalsIgnoreCase("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }
    //    得到listview高度
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
    /**
     * 可扩展listview展开时调用
     *
     * @param listView
     * @param groupPosition
     */
    public static void setExpandedListViewHeightBasedOnChildren(
            ExpandableListView listView, int groupPosition) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            return;
        }
        View listItem = listAdapter.getChildView(groupPosition, 0, true, null,
                listView);
        listItem.measure(0, 0);
        int appendHeight = 0;
        for (int i = 0; i < listAdapter.getChildrenCount(groupPosition); i++) {
            appendHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        Log.d(TAG, "Expand params.height" + params.height);
        params.height += appendHeight;
        listView.setLayoutParams(params);
    }
    /**
     * 可扩展listview收起时调用
     *
     * @param listView
     * @param groupPosition
     */
    public static void setCollapseListViewHeightBasedOnChildren(
            ExpandableListView listView, int groupPosition) {
        ExpandableListAdapter listAdapter = listView.getExpandableListAdapter();
        if (listAdapter == null) {
            return;
        }
        View listItem = listAdapter.getChildView(groupPosition, 0, true, null,
                listView);
        listItem.measure(0, 0);
        int appendHeight = 0;
        for (int i = 0; i < listAdapter.getChildrenCount(groupPosition); i++) {
            appendHeight += listItem.getMeasuredHeight();
        }
        /*Log.d(TAG,
                "Collapse childCount="
                        + listAdapter.getChildrenCount(groupPosition));*/
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height -= appendHeight;
        listView.setLayoutParams(params);
    }
    //    得到listview高度的前三个
    public static void setHalfListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i <4; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if(listAdapter.getCount()>=3){
        params.height = totalHeight
                + (listView.getDividerHeight() * 3);
        }else{
            params.height = totalHeight
                    + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        }
        listView.setLayoutParams(params);
    }

    // 弹出对话框通知用户答题时间到
//    public static void showTimeOutDialog(final String title, final Context mcontext,final String QQ) {
//        final Dialog builder = new Dialog(mcontext, R.style.dialog);
//        builder.setContentView(R.layout.answer_dialog);
//        TextView confirm_btn = (TextView) builder.findViewById(R.id.dialog_sure);
//        TextView content = (TextView) builder.findViewById(R.id.dialog_content);
//        TextView cancel_btn = (TextView) builder.findViewById(R.id.dialog_cancle);
//
//        content.setText(title);
//        confirm_btn.setText("点击进群");
//        cancel_btn.setText("开通会员");
//
//        //cancel_btn.setText("确认");
//        confirm_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + "Xacro7V3QQvIkL1AO27kcQwikqe7ot3e"));
//                // 此Flag可根据具体产品需要自定义，如设置，则在加群界面按返回，返回手Q主界面，不设置，按返回会返回到呼起产品界面    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//
//                    mcontext.startActivity(intent);
////                    return true;
////                } catch (Exception e) {
////                    // 未安装手Q或安装的版本不支持
////                    return false;
////                }
////                joinQQGroup(,mcontext);
//////                ClipboardManager cmb = (ClipboardManager)mcontext.getSystemService(Context.CLIPBOARD_SERVICE);
////                cmb.setText(QQ.trim());
//                builder.dismiss();
//
//            }
//        });
//
//        cancel_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                builder.dismiss();
//
////                Intent intent = new Intent();
////
////                intent.setClass(mcontext, PayDemoActivity.class);
////
////                mcontext.startActivity(intent);
//
//            }
//        });
//        builder.setCanceledOnTouchOutside(false);// 设置点击Dialog外部任意区域关闭Dialog
//
//        builder.show();
//
//    }
    /****************
     *
     * 发起添加群流程。群号：专转本学习群(303560856) 的 key 为： GTQd1qoOt49wca50Sh64yE2WPaoC_4rF
     * 调用 joinQQGroup(GTQd1qoOt49wca50Sh64yE2WPaoC_4rF) 即可发起手Q客户端申请加群 专转本学习群(303560856)
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回fals表示呼起失败
     ******************/


}

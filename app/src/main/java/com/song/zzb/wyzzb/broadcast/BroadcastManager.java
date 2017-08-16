package com.song.zzb.wyzzb.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.song.zzb.wyzzb.Action;
import com.song.zzb.wyzzb.bean.User;
import com.song.zzb.wyzzb.util.Constant;

import static com.song.zzb.wyzzb.Action.REGISTER;


/**
 * Created by aspsine on 16/9/3.
 */

public class BroadcastManager {
    /**
     * 广播类型
     */
    public static enum BROADCAST_TYPE {
        TYPE_USER_UPDATE, // 更新用户
        TYPE_USER_REGISTER, // 注册用户
        TYPE_USER_CANCEL_FOLLOW, // 取消关注用户
        TYPE_TOPIC_FOLLOW, // 关注某个话题
        TYPE_TOPIC_CANCEL_FOLLOW, // 取消关注某个话题
        TYPE_COUNT_FEED, // feed消息条数
        TYPE_COUNT_USER, // user消息条数
        TYPE_COUNT_FANS, // 粉丝消息条数
        TYPE_FEED_POST, // 发送feed
        TYPE_FEED_DELETE, // 删除feed
        TYPE_FEED_UPDATE,// update feed
        TYPE_FEED_FAVOURITE,// feed favourite
        TYPE_USER_LOGOUT
    }
    /**
     * 注册一个广播</br>
     *
     * @param context
     * @param
     * @param receiver
     */
    public static void register(Context context, DefalutReceiver receiver, String actions){
        IntentFilter filter = new IntentFilter();
            filter.addAction(actions);
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
    }
    /**
     * 注销该context下的所有广播。</br>
     *
     * @param context
     */
    public static void unregister(Context context, DefalutReceiver receiver){
        if (context != null && receiver != null) {
            try {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
            } catch (IllegalArgumentException e) {
            }
        }
    }

    /**
     * 注册一个用户级别的广播当注册用户时，修改相应界面的数据</br>
     *
     * @param context
     * @param receiver
     */
    public static void registerUserBroadcast(Context context, DefalutReceiver receiver) {
        register(context, receiver, Action.REGISTER);
    }
    private static Intent buildIntent(BROADCAST_TYPE type, String action) {
        Intent intent = new Intent();
        intent.putExtra(Constant.TAG_TYPE, type);
        intent.setAction(action);
        return intent;
    }
    /**
     * 发送一个关注用户的广播</br>
     *
     * @param context
     * @param user
     */
    public static void sendUserBroadcast(Context context, User user) {
        Intent intent = buildIntent(BROADCAST_TYPE.TYPE_USER_UPDATE, Action.REGISTER);
        intent.putExtra(Constant.TAG_USER, user);
        Log.i("getMobilePhoneNumber1",user.getMobilePhoneNumber().toString());
        sendBroadcast(context, intent);

    }
    private static void sendBroadcast(Context context, Intent intent) {
        if (context != null) {
            // context.sendBroadcast(intent);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    /**
     * 默认的广播接收器实现类。具体对哪类（user、feed、topic、count）感兴趣，覆盖相应的方法并处理并处理逻辑
     */
    public static class DefalutReceiver extends BroadcastReceiver {

        public void onReceiveUser(Intent intent) {// 收到用户广播时的回调
        }

        public void onReceiveTopic(Intent intent) {// 收到话题广播时的回调
        }

        public void onReceiveFeed(Intent intent) {// 收到feed广播时的回调
        }

        public void onReceiveCount(Intent intent) {// 收到更新消息数广播时的回调
        }

        public void onReceiveUpdateFeed(Intent intent) {
        }

        protected User getUser(Intent intent) {// 从intent中获取数据
            Log.i("getMobilePhoneNumber",intent.getExtras().getParcelable(Constant.TAG_USER).toString());
            return intent.getExtras().getParcelable(Constant.TAG_USER);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Action.REGISTER.equals(action)) {
                onReceiveUser(intent);
            }
//            else if (ACTION_TOPIC.equals(action)) {
//                onReceiveTopic(intent);
//            } else if (ACTION_COUNT.equals(action)) {
//                onReceiveCount(intent);
//            } else if (ACTION_FEED.equals(action)) {
//                onReceiveFeed(intent);
//            } else if (ACTION_UPDATE.equals(action)) {
//                onReceiveUpdateFeed(intent);
//            }
        }
    }
}

package com.song.zzb.wyzzb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.CourseList;
import com.song.zzb.wyzzb.ui.CustomView.JCVideoPlayerStandard;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


/**
 * Created by song on 2016/2/11.
 * 第一首页商城界面的列表
 */
public class Child1_Video_Adapter extends BaseAdapter {
    private Context mContext;
    private List<CourseList> news = new ArrayList<CourseList>();
    ImageLoader imageLoader = ImageLoader.getInstance();
    public Child1_Video_Adapter(Context mContext, List<CourseList> news) {
        super();
        this.mContext = mContext;
        this.news = news;
    }



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return news.size();
    }

    @Override
    public CourseList getItem(int position) {
        if (news != null && news.size() != 0) {
            return news.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child1_video, null);
            viewHolder.jcVideoPlayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.videoplayer);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CourseList news = this.news.get(position);

        boolean setUp = viewHolder.jcVideoPlayer.setUp(
                news.getVideourl(), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                news.getTitle());
        if (setUp) {
            if(news.getPicFile()!=null){
            Log.i("url1",news.getPicFile().getFileUrl(mContext));
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
            imageLoader.displayImage( news.getPicFile().getFileUrl(mContext),
                    viewHolder.jcVideoPlayer.thumbImageView);
            }
        }

//        viewHolder.jcVideoPlayer.setUp(
//                news.getVideourl(),0,
//                news.getPicFile().getFileUrl(mContext),
//                news.getTitle());

        return convertView;
    }

    private class ViewHolder{

        ImageView image;
        LinearLayout dsc;
        JCVideoPlayerStandard jcVideoPlayer;

    }
}




package com.song.zzb.wyzzb.adapter;

import android.content.Context;
import android.graphics.Paint;
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
import com.song.zzb.wyzzb.bean.downloadziliao;
import com.song.zzb.wyzzb.ui.CustomView.JCVideoPlayerStandard;
import com.song.zzb.wyzzb.ui.view.CustomTextView;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


/**
 * Created by song on 2016/2/11.
 * 下载
 */
public class Child1_Download_Adapter extends BaseAdapter {
    private Context mContext;
    private List<downloadziliao> news = new ArrayList<downloadziliao>();

    public Child1_Download_Adapter(Context mContext, List<downloadziliao> news) {
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
    public downloadziliao getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_doc_item, null);
            viewHolder.doc_item_title = (TextView) convertView.findViewById(R.id.doc_item_title);
            viewHolder.doc_item_downloadcount = (TextView) convertView.findViewById(R.id.doc_item_downloadcount);
            viewHolder.doc_item_price = (TextView) convertView.findViewById(R.id.doc_item_price);
            viewHolder.doc_item_purchase_price = (TextView) convertView.findViewById(R.id.doc_item_purchase_price);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        downloadziliao news = this.news.get(position);
        Log.i("title",""+news.getTitle());
        viewHolder.doc_item_title.setText(news.getTitle());
        if(news.getCount()!=null){
        viewHolder.doc_item_downloadcount.setText(String.valueOf(news.getCount())+"次下载");
        }

        viewHolder.doc_item_price.setText(news.getPrice());
        if(news.getYuanprice()==null){
            viewHolder.doc_item_purchase_price.setVisibility(View.GONE);
        }else{
        viewHolder.doc_item_purchase_price.setText(news.getYuanprice());
        viewHolder.doc_item_purchase_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);}



        return convertView;
    }

    private class ViewHolder{

        ImageView image;
        LinearLayout dsc;
        TextView doc_item_title;
        TextView   doc_item_downloadcount;//下载次数
        TextView  doc_item_price;//现价
        TextView  doc_item_purchase_price;//原价

    }
}




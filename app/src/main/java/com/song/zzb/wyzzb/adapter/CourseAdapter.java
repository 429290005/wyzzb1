package com.song.zzb.wyzzb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.bean.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2016/9/6.
 */
public class CourseAdapter extends BaseAdapter {
    private Context mContext;
    private List<Course> news = new ArrayList<Course>();

    public CourseAdapter(Context mContext, List<Course> news) {
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
    public Course getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.coursedsc_item, null);
            viewHolder.title = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Course news = this.news.get(position);


        if(news.getDsc()!=null){
            viewHolder.title.setText(news.getDsc());}


        return convertView;
    }

    private class ViewHolder{
        TextView title;

    }
}


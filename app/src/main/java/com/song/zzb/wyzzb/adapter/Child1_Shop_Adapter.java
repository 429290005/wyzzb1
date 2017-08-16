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

import java.util.ArrayList;
import java.util.List;



/**
 * Created by song on 2016/2/11.
 * 第一首页商城界面的列表
 */
public class Child1_Shop_Adapter extends BaseAdapter {
    private Context mContext;
    private List<CourseList> news = new ArrayList<CourseList>();
    ImageLoader imageLoader = ImageLoader.getInstance();
    public Child1_Shop_Adapter(Context mContext, List<CourseList> news) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.child1_item_shop_course_list, null);
            viewHolder.course_title= (TextView)convertView.findViewById(R.id.course_title);
            viewHolder.dsc=(LinearLayout) convertView.findViewById(R.id.dsc);
//            viewHolder.play_btn=(Button) convertView.findViewById(R.id.play_btn);
//            viewHolder.tv= (TextView)convertView.findViewById(R.id.tv);
            viewHolder.tv_people=(TextView)convertView.findViewById(R.id.tv_people);
            viewHolder.tv_seniorhelp = (TextView)convertView.findViewById(R.id.tv_seniorhelp);
//            viewHolder.video_fy=(FrameLayout ) convertView.findViewById(R.id.video_fy);
          // viewHolder.tv_seniorhelpdsc = (TextView)convertView.findViewById(R.id.tv_seniorhelpdsc);
            viewHolder.image = (ImageView)convertView.findViewById(R.id.image);
            viewHolder.price= (TextView)convertView.findViewById(R.id.price);

//            viewHolder.mSuperVideoPlayer = (SuperVideoPlayer)convertView.findViewById(R.id.video_player_item_1);
//            viewHolder.mPlayBtnView = (Button)convertView.findViewById(R.id.play_btn);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String picUrl = null;
        CourseList news = this.news.get(position);
        Log.i("user", "USER avatar IS NULL"+news);
        if(news.getVideourl().equals("1")){
        if(news.getTitle()!=null){
            viewHolder.course_title.setText(news.getTitle());}
        if(news.getTitledsc1()!=null){
            viewHolder.tv_people.setText(news.getTitledsc1());}
        if(news.getTitledsc()!=null){
            viewHolder.tv_seniorhelp.setText(news.getTitledsc());}
        if(news.getPrice()!=null){
            viewHolder.price.setText(news.getPrice());}
        if(news.getPicFile()==null){
            Log.i("user", "USER avatar IS NULL");
        }
        String avatarUrl = null;
        if(news.getPicFile()!=null){
            avatarUrl = news.getPicFile().getFileUrl(mContext);
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));
            imageLoader.displayImage(avatarUrl,viewHolder.image);
           // ImageLoader.getInstance().displayImage(avatarUrl,viewHolder.image);
        }
        }else{


//            viewHolder.play_btn.setText(news.getTitle());
//            viewHolder.tv.setText(news.getTitledsc());
        }
       // viewHolder.tv_seniorhelpdsc.setText(news.getTitledsc());
//        viewHolder.image.setText(news.getCreatedAt());
        return convertView;
    }

    private class ViewHolder{
        TextView tv_seniorhelp,course_title,tv_people,price;
        TextView tv_seniorhelpdsc;
        ImageView image;
        LinearLayout dsc;


    }
}


package com.song.zzb.wyzzb.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.song.zzb.wyzzb.R;


import java.util.ArrayList;
import java.util.List;

public class NumberedAdapter extends RecyclerView.Adapter<TextViewHolder> {
  private List<String> labels;
  private Context context;
  private int[] value=new int[2];//得到具体课程

  public NumberedAdapter(int count,Context context) {
    labels = new ArrayList<String>(count);
    this.context=context;
    for (int i = 1; i<= count; ++i) {
      labels.add(String.valueOf(i));
    }
  }

  @Override
  public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.answercard_item, parent, false);
    return new TextViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final TextViewHolder holder, final int position) {
    final String label = labels.get(position);
    holder.textView.setText(label);
    holder.textView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Toast.makeText(
                holder.textView.getContext(), label, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        Bundle bundletitle = new Bundle();
        intent.putExtra("option", 2);
        intent.putExtra("startfrom", position);
        value[0] =  1;//哪个章节
        value[1] =  1;//哪个小节
        Log.i("value4", "" + value);
        Log.i("flag1", "" + value[0] + "/" + value[1]);
        bundletitle.putString("title", "返回");
        bundle.putIntArray("value", value);
//
//        intent.putExtras(bundletitle);
//        intent.setClass(context, ComChapterExameActivity.class);
//        intent.putExtras(bundle);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        context.startActivity(intent);



      }
    });
  }

  @Override
  public int getItemCount() {
    return labels.size();
  }
}
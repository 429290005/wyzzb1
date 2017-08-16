package com.song.zzb.wyzzb.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.song.zzb.wyzzb.R;


/**
 * Created by aspsine on 16/4/1.
 */
public class TabChild2Layout extends LinearLayout {

    private OnTabItemClickListener mOnTabItemClickListener;

    public interface OnTabItemClickListener {
        void onTabItemClick(int position, View view);
    }

    public TabChild2Layout(Context context) {
        this(context, null);
    }

    public TabChild2Layout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabChild2Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        inflate(context, R.layout.layout_tab_child2, this);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            final int finalI = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnTabItemClickListener.onTabItemClick(finalI, v);
                }
            });
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener listener) {
        this.mOnTabItemClickListener = listener;
    }

    public void select(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (i == position) {
                selectChild(child, true);
            } else {
                selectChild(child, false);
            }
        }
    }

    private void selectChild(View child, boolean select) {
        if (child instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) child;
            group.setSelected(select);
            for (int i = 0; i < group.getChildCount(); i++) {
                selectChild(group.getChildAt(i), select);
            }
        } else {
            child.setSelected(select);
        }
    }

}
package com.song.zzb.wyzzb.ui.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;

import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.song.zzb.wyzzb.R;

/**
 * 自定义TextView
 * 博客地址：http://blog.csdn.net/u010156024/article/details/45770417
 * @author
 */

public class CustomTextView extends AppCompatTextView {

  private int duration;
  private Timer timer;
  private String text;
  private String textc;
  private int i = 0;
  public CustomTextView(Context context) {
    super(context);
    init(context,null,0);
  }

  public CustomTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs, 0);
  }

  public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    init(context, attrs, defStyle);
  }
  private void init(Context context,AttributeSet attrs, int defStyle){
    TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
            R.styleable.customTextView, defStyle, 0);
    int j = typedArray.getIndexCount();
    for (int i = 0; i < j; i++) {
      int k = typedArray.getIndex(i);
      switch (k) {
        case R.styleable.customTextView_duration:
          duration = typedArray.getInt(k, 0);
          break;
        default:
          break;
      }
    }
    typedArray.recycle();
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    text = getText().toString();
    timer = new Timer();
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        textc = text.substring(i, text.length());
        i++;
        if (i>=text.length()) {
          i=0;
        }
        postInvalidate();
      }
    };
    timer.schedule(task, 100, duration);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (textc != null) {
      canvas.drawText(textc, getBaseline(), getBaseline(), getPaint());
    }
  }

  @Override
  public boolean isInEditMode() {
    return false;
  }

  //脱离Window，定时器取消
  @Override
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    timer.cancel();
  }

}

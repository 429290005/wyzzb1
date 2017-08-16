package com.song.zzb.wyzzb.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;

import com.song.zzb.wyzzb.fragment.Child1Fragment;
import com.song.zzb.wyzzb.fragment.Child2Fragment;
import com.song.zzb.wyzzb.fragment.Child3Fragment;
import com.song.zzb.wyzzb.fragment.Child5Fragment;
import com.song.zzb.wyzzb.fragment.MainFragment;


/**
 * Created by aspsine on 16/3/31.
 * 主页面fragment
 */
public class MainFragmentAdapter implements FragmentNavigatorAdapter {

    private static final String TABS[] = {"百科", "题库","微课", "直播", "我的"};

    @Override
    public Fragment onCreateFragment(int position) {
      if (position == 0){
        return Child1Fragment.newInstance(position);
        }
        else if (position == 1){
            return Child2Fragment.newInstance();
        } else if (position ==2){
          return Child3Fragment.newInstance();
      }
       else if (position == 4){
          return Child5Fragment.newInstance();
      }
//        else if (position == 2){
//            return Child2Fragment.newInstance(position);
//        }
        return MainFragment.newInstance(TABS[position]);
    }

    @Override
    public String getTag(int position) {
        if (position == 1){
            return Child1Fragment.TAG;
        }else if (position == 2){
            return Child2Fragment.TAG;
        }else if (position == 3){
            return Child2Fragment.TAG;
        }else if (position == 4){
            return Child2Fragment.TAG;
        }
        return Child1Fragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}

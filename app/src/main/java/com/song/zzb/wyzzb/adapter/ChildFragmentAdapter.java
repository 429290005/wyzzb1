package com.song.zzb.wyzzb.adapter;

import android.support.v4.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.song.zzb.wyzzb.fragment.MainFragment;


/**
 * Created by aspsine on 16/4/3.
 */
public class ChildFragmentAdapter implements FragmentNavigatorAdapter {

    public static final String[] TABS = {"百科", "资料下载", "学堂", "微课"};

    @Override
    public Fragment onCreateFragment(int position) {
        return MainFragment.newInstance(TABS[position]);
    }

    @Override
    public String getTag(int position) {
        return MainFragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}

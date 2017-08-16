package com.song.zzb.wyzzb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.song.zzb.wyzzb.R;
import com.song.zzb.wyzzb.adapter.Child1FragmentAdapter;
import com.song.zzb.wyzzb.widget.TabChild1Layout;


/**
 * A simple {@link Fragment} subclass.
 * 第一个主页面fragment
 */
public class Child1Fragment extends Fragment {

    public static final String TAG = Child1Fragment.class.getSimpleName();

    private FragmentNavigator mNavigator;

    private TabChild1Layout tabLayout;

    public Child1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new FragmentNavigator(getChildFragmentManager(), new Child1FragmentAdapter(), R.id.childContainer);
        mNavigator.setDefaultPosition(0);
        mNavigator.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_child1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabChild1Layout) view.findViewById(R.id.tabLayout);
        tabLayout.setOnTabItemClickListener(new TabChild1Layout.OnTabItemClickListener() {
            @Override
            public void onTabItemClick(int position, View view) {
                setCurrentTab(position);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCurrentTab(mNavigator.getCurrentPosition());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    public void setCurrentTab(int position) {
        mNavigator.showFragment(position);
        tabLayout.select(position);
    }


    public static Fragment newInstance(int position) {
        Fragment fragment = new Child1Fragment();
        return fragment;
    }
}

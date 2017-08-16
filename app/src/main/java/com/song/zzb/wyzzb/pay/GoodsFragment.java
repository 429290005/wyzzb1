package com.song.zzb.wyzzb.pay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.song.zzb.wyzzb.R;

/**
 * Created by song on 2016/9/7.
 */
public class GoodsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_pay_confirm, container, false);
    }
}


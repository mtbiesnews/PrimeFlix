package com.rcloud.business.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.item.ItemAbout;
import com.example.util.JsonUtils;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.google.ads.consent.ConsentForm;
import com.rcloud.netflix.MyApplication;
import com.rcloud.netflix.R;

import java.util.ArrayList;


public class home_mtbies extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_mtbies, container, false);
    }
}
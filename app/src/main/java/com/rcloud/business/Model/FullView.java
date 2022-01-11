package com.rcloud.business.Model;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.rcloud.business.Adapter.ImageAdapter;
import com.rcloud.netflix.R;

public class FullView extends Fragment {

    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_full_view, container, false);


        ImageView imageView = view.findViewById(R.id.img_full);
        int img_id = getResources().getInteger(Integer.valueOf("img_id"));

        imageView.setImageResource(img_id);
        return view;
    }



}

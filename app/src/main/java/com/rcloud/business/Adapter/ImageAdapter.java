package com.rcloud.business.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.rcloud.business.fragment.BusineesProfileFrag.PhotosFragment;
import com.rcloud.netflix.R;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private List<Integer> thumbId;
    private Context context;
    LayoutInflater inflater;

    public ImageAdapter(List<Integer> thumbId, Context context) {
        this.thumbId = thumbId;
        this.context = context;
        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return thumbId.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return thumbId.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if (imageView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        imageView.setImageResource(thumbId.get(position));
        return imageView;
    }
}

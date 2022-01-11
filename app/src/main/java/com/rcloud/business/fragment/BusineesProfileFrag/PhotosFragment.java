package com.rcloud.business.fragment.BusineesProfileFrag;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Class;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rcloud.business.Adapter.ImageAdapter;
import com.rcloud.business.Model.FullView;
import com.rcloud.netflix.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PhotosFragment extends Fragment {
    private GridView gridView;
    ArrayList<Integer> img = new ArrayList(Arrays.asList(
            R.drawable._desktop_wallpapers_free_online_fantasy_wallpapers___full_hd_desktop,
            R.drawable.rose_flower_keys_piano_68785_1920x1080,
            R.drawable.dubai_uae_buildings_skyscrapers_night_96720_1920x1080,
            R.drawable.water_lilies_water_leaves_pond_bridge_trees_beauty_green_nature_30352_1920x1080,
            R.drawable.peacock_bird_tail_grass_59857_1920x1080,
            R.drawable.free_wallpaper_for_laptop_full_hd_download_high_definiton_wallpapers_desktop_images_amazing_colourful_free_cool_artwork_1920x1080));


    public static PhotosFragment newInstance() {
        return new PhotosFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos, container, false);
        gridView = (GridView) view.findViewById(R.id.gallery);
        gridView.setAdapter(new ImageAdapter(img, getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int item_pos = img.get(position);
                ShowDialogBox(item_pos);
            }
        });
        return view;
    }
    public void ShowDialogBox(int item_pos) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        //Getting custom dialog views
        TextView Image_name = dialog.findViewById(R.id.txt_Image_name);
        ImageView Image = dialog.findViewById(R.id.img);
        Button btn_Full = dialog.findViewById(R.id.btn_full);
        Button btn_Close = dialog.findViewById(R.id.btn_close);

        String title = getResources().getResourceName(item_pos);

        //extracting name
        int index = title.indexOf("/");
        String name = title.substring(index+1,title.length());
        Image_name.setText(name);

        Image.setImageResource(item_pos);

        btn_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_Full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i = new Intent(PhotosFragment.this, FullView.class);
             //   i.putExtra("imd_id", item_pos);
             //   startActivity(i);
            }
        });

        dialog.show();

    }




}

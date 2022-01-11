package com.rcloud.business;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.rcloud.business.Adapter.ViewPagerStateAdapter;
import com.rcloud.netflix.R;
import com.squareup.picasso.Picasso;

public class BusinessActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ImageView cover, back;
    DrawerLayout drawer_layout;
    ImageView profile;
    FloatingActionButton floatingActionButton, changeProfile;
    private static final int PICK_IMAGE = 1;
    public static String xFer = "IN";
    Context context;

    private int drawable = R.drawable.newlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        viewPager = findViewById(R.id.viewPager);
        cover = findViewById(R.id.coverimage);
        floatingActionButton = findViewById(R.id.fab);
        profile = findViewById(R.id.profile_image);
        changeProfile = findViewById(R.id.changeprofile);
        back = findViewById(R.id.back);
        profile.setImageResource(R.drawable.img_fram);

        ViewPagerStateAdapter adapter = new ViewPagerStateAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra("scale", "true");
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(Intent.createChooser(intent,"Select Picture"), PICK_IMAGE);
            }
        });

       /* changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(context)
                        .load(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        .transform(new CustomMaskTransformation(context, mask))
                        .placeholder(R.drawable.img_fram)
                        .into(profile);*/
              /*  Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 2);*/
    //        }
    //    });


    back.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                 Intent intent = new Intent(BusinessActivity.this, Mtbies.class);
                 startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            cover.setImageURI(uri);
        }
        else {
            Uri uri = data.getData();
            profile.setImageURI(uri);
        }
    }
}
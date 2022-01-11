package com.rcloud.business;

import com.google.android.gms.cast.framework.media.ImagePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.rcloud.business.Adapter.ViewPagerBusinessAdapter;
import com.rcloud.business.Adapter.ViewPagerStateAdapter;
import com.rcloud.netflix.R;

import de.hdodenhof.circleimageview.CircleImageView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BusineesProfileActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ImageView cover, back;
    CircleImageView profile;
    FloatingActionButton floatingActionButton, changeProfile;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businees_profile);
        ViewPagerBusinessAdapter adapter = new ViewPagerBusinessAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        cover = findViewById(R.id.coverimage);
        floatingActionButton = findViewById(R.id.fab);
        profile = findViewById(R.id.profile_image);
        changeProfile = findViewById(R.id.changeprofile);
        back = findViewById(R.id.back);

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

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", "true");
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 2);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BusineesProfileActivity.this, Mtbies.class);
                startActivity(intent);
            }
        });

    }

  /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Appointment", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Business Ads", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Invite", Toast.LENGTH_SHORT).show();
                default:
                    return super.onOptionsItemSelected(item);
        }
    }*/
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
package com.map_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rcloud.netflix.R;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class setLocationInfo_Activity extends AppCompatActivity {
    private RecyclerView locationRecycler;
    private ArrayList<Location_model> list;
    private Location_Adapter adapter;
    private String Area, Address, Latitude, Longitude, PinCode, phoneNumber, purpose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location_info);

        Area = getIntent().getStringExtra("area");
        Address = getIntent().getStringExtra("address");
        PinCode = getIntent().getStringExtra("pinCode");
        Latitude = getIntent().getStringExtra("latitude");
        Longitude = getIntent().getStringExtra("longitude");
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        purpose = getIntent().getStringExtra("purpose");

        locationRecycler = findViewById(R.id.locationRecycler);

        locationRecycler.setLayoutManager(new LinearLayoutManager(this));
        locationRecycler.setHasFixedSize(true);
        list = new ArrayList<>();

        Location_model location_model = new Location_model(Area, Address, PinCode, Latitude, Longitude, phoneNumber, purpose);
        list.add(location_model);
        adapter = new Location_Adapter(this, list);
        adapter.notifyDataSetChanged();
        locationRecycler.setAdapter(adapter);


    }
}
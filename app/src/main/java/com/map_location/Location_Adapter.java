package com.map_location;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.rcloud.netflix.R;

import java.util.List;

public class Location_Adapter extends RecyclerView.Adapter<Location_Adapter.LocationViewAdapter> {
    private Context context;
    private List<Location_model>list;

    public Location_Adapter(Context context, List<Location_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LocationViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.location_item_layout,parent,false);
        return new LocationViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewAdapter holder, int position) {
        Location_model currentItem = list.get(position);

        holder.area.setText(currentItem.getArea());
        holder.address.setText(currentItem.getAddress());
        holder.latitude.setText(currentItem.getLatitude());
        holder.longitude.setText(currentItem.getLongitude());
        holder.phoneNumber.setText(currentItem.getPhoneNumber());
        holder.purpose.setText(currentItem.getPurpose());

        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map_Fragment fragment4 = new Map_Fragment();

                Bundle bundle = new Bundle();
                bundle.putString("cus_latitude", currentItem.getLatitude());
                bundle.putString("cus_longitude", currentItem.getLongitude());
                fragment4.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.mapFrame, fragment4).commit();



            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationViewAdapter extends RecyclerView.ViewHolder {
        private TextView area,address,latitude,longitude,phoneNumber,purpose;
        private Button accept;

        public LocationViewAdapter(@NonNull View itemView) {
            super(itemView);
            area=itemView.findViewById(R.id.area);
            address=itemView.findViewById(R.id.address);
            latitude=itemView.findViewById(R.id.latitude);
            longitude=itemView.findViewById(R.id.longitude);
            phoneNumber=itemView.findViewById(R.id.phoneNumber);
            purpose=itemView.findViewById(R.id.purpose);
            accept=itemView.findViewById(R.id.accept);

        }
    }
}

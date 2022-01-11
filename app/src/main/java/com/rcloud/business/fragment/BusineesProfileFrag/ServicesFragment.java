package com.rcloud.business.fragment.BusineesProfileFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.rcloud.netflix.R;


public class ServicesFragment extends Fragment {

Spinner spinner_sellertype,spinner_unittype;
    String[] seller = {"Select Seller Type", "Brand", "Manufacture", "WholeSeller", "Retailer", "Exporter"};
    String[] unit = {"Select Unit", "Piece","Pair","PAck","Corton","carat","Bag","Cubic","feet","ton","meter"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_services, container, false);

         spinner_sellertype = view.findViewById(R.id.spinner_sellertype);
         spinner_unittype = view.findViewById(R.id.spinner_unittype);
        ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, seller);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sellertype.setAdapter(aa1);
        spinner_sellertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, unit);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_unittype.setAdapter(aa2);
        spinner_unittype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
}
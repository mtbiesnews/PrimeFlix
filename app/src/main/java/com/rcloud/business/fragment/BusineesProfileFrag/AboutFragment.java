package com.rcloud.business.fragment.BusineesProfileFrag;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.rcloud.netflix.R;

public class AboutFragment extends Fragment {

    String[] country = {"Select Country", "India"};
    String[] state = {"Select State", "UP","MP"};
    String[] city = {"Select City", "Lucknow","Sitapur"};
    String[] hourtype = {"Select Type", "Aviable time","Always open","Appointed only"};
    String[] servicetype = {"Select Service Type", "On Call","Home","Online"};
    RelativeLayout rl_self;
    String pro_edu = "1";
    LinearLayout ll_address;
    Spinner spinner_country,spinner_state,spinner_city,spinner_hourtype,spinner_servicetype;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about2, container, false);

         rl_self = view.findViewById(R.id.rl_self);
         ll_address = view.findViewById(R.id.ll_address);
         spinner_country = view.findViewById(R.id.spinner_country);
         spinner_state = view.findViewById(R.id.spinner_state);
         spinner_city = view.findViewById(R.id.spinner_city);
         spinner_hourtype = view.findViewById(R.id.spinner_hourtype);
         spinner_servicetype = view.findViewById(R.id.spinner_servicetype);


         rl_self.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if (pro_edu == "0") {
                     pro_edu = "1";
                     ll_address.setVisibility(View.GONE);
                 } else {
                     pro_edu = "0";
                     ll_address.setVisibility(View.VISIBLE);
                 }
             }
         });


        ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, country);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_country.setAdapter(aa1);
        spinner_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, state);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setAdapter(aa2);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, city);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(aa3);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, hourtype);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hourtype.setAdapter(aa4);
        spinner_hourtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa5 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, servicetype);
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_servicetype.setAdapter(aa5);
        spinner_servicetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return  view;
    }
}
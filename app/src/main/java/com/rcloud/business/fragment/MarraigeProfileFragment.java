package com.rcloud.business.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.rcloud.netflix.R;


public class MarraigeProfileFragment extends Fragment {
    String[] relation = {"Select Relation", "Single", "In a relationship ", "Engaged ", "Married ", "Divorced  ", "Widowed "};
    Spinner spinner_res;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_marraige_profile, container, false);

        spinner_res = view.findViewById(R.id.spinner_res);

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, relation);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_res.setAdapter(aa);
        spinner_res.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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



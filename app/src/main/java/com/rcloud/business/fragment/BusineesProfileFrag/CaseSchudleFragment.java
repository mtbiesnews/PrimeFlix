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


public class CaseSchudleFragment extends Fragment {
    String[] mode = {"Select Case mode", "OnCall", "OnSite", "Online", "Home"};
    String[] status = {"Select Case Status", "Running", "Result"};
    Spinner spinner_casemode,spinner_casestatus;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_case_schudle, container, false);

         spinner_casemode = view.findViewById(R.id.spinner_casemode);
                spinner_casestatus = view.findViewById(R.id.spinner_casestatus);


                ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, mode);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_casemode.setAdapter(aa1);
        spinner_casemode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, status);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_casestatus.setAdapter(aa2);
        spinner_casestatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
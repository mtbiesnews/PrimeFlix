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

public class ServiceOnTimeFragment extends Fragment {
Spinner spinner_servicemode,spinner_paymode,spinner_plantype,spinner_servicesttus;
    String[] mode = {"Select Case mode", "OnCall", "OnSite", "Online", "Home"};
    String[] status = {"Select Case Status", "Running", "Result"};
    String[] plan = {"Select Plan", "Prepaid", "Postpaid"};
    String[] paymode = {"Select Payment mode", "COD", "Online", "NEFT"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_service_on_time, container, false);
                 spinner_servicemode = view.findViewById(R.id.spinner_servicemode);
                 spinner_paymode = view.findViewById(R.id.spinner_paymode);
                 spinner_plantype = view.findViewById(R.id.spinner_plantype);
                 spinner_servicesttus = view.findViewById(R.id.spinner_servicesttus);


        ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, mode);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_servicemode.setAdapter(aa1);
        spinner_servicemode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, paymode);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_paymode.setAdapter(aa2);
        spinner_paymode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, plan);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_plantype.setAdapter(aa3);
        spinner_plantype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa4 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, status);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_servicesttus.setAdapter(aa4);
        spinner_servicesttus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
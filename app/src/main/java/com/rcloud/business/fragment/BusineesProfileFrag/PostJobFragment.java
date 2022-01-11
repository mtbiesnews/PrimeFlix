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


public class PostJobFragment extends Fragment {
    String[] salary = {"Select Salary type", "Per Hours", "Per Day", "Per Week", "Per Month", "Per Year"};
    String[] job = {"Select Job type", "Full-time","Part-time", "Internship","Volunteer", "Contract","Freelance"};
    Spinner spinner_salarytype,spinner_jobtype;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_post_job, container, false);
        spinner_salarytype = view.findViewById(R.id.spinner_salarytype);
        spinner_jobtype = view.findViewById(R.id.spinner_jobtype);



        ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, salary);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_salarytype.setAdapter(aa1);
        spinner_salarytype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter aa2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, job);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_jobtype.setAdapter(aa2);
        spinner_jobtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
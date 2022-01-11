package com.rcloud.business.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.rcloud.netflix.R;


public class ModelProfileFragment extends Fragment {
    String[] color = {"Select Eye Color","Black","Brown","Blue"};
    String[] hcolor = {"Select hair Color","Black","Brown","Blue","Red"};
    String[] interest = {"Select Interest","Acting","Print Shoot","Ramp Shows","Designer shoots",
            "Western Wears","Swim Suits","Calender Shoots","Music Albums",
            "Movie","WebSeries","Bold WebSeries","TV Serial",
            "Ads","Bikini Shoots","Lingerie Shoots","Full Body Paint Shoots",
            "Semi Nude Shoots","Nude Shoots"};

    String[] comfort = {"Select Comfort Zone","Kiss","Smooch","Love making","Indian Wear",
            "Western","Shorts","Bikini","Bold Scenes",
            "Bed Scenes","Topless","Nude","Compro or Adjustment", "Able to work indoor & outdoor"};
    Spinner spinner_eye,spinner_hair,spinner_interest,spinner_comfort;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_model_profile, container, false);
        spinner_eye = view.findViewById(R.id.spinner_eye);
        spinner_hair = view.findViewById(R.id.spinner_hair);
        spinner_interest = view.findViewById(R.id.spinner_interest);
        spinner_comfort = view.findViewById(R.id.spinner_comfort);
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,color);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_eye.setAdapter(aa);
        spinner_eye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,hcolor);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hair.setAdapter(aa1);
        spinner_hair.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa2 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,interest);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_interest.setAdapter(aa2);
        spinner_interest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(),relation[position] , Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa3 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,comfort);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_comfort.setAdapter(aa3);
        spinner_comfort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
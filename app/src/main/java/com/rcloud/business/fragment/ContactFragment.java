package com.rcloud.business.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rcloud.netflix.R;


public class ContactFragment extends Fragment {

    String[] web_type = { "Select web Type", "personal", "Company", "Job profile ", "Portfolio", "others"};
    String[] phone_type = { "Select Type", "personal", "Work", "Mobile", "Fax"};
  ImageView edit_contact;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact, container, false);
        edit_contact = view.findViewById(R.id.edit_contact);
        edit_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity(), android.R.style.Theme_Translucent_NoTitleBar);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.contact_info);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.CENTER;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
                window.setAttributes(wlp);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

                TextView tvAlert = dialog.findViewById(R.id.tvAlert);
                TextView save = dialog.findViewById(R.id.save);
                ImageView img = dialog.findViewById(R.id.back);
                Spinner spinner_webtype = dialog.findViewById(R.id.spinner_webtype);
                Spinner spinner_phonetype = dialog.findViewById(R.id.spinner_phonetype);

                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                ArrayAdapter aa5 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, web_type);
                aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_webtype.setAdapter(aa5);
                spinner_webtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(),emptype[position] , Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                ArrayAdapter aa6 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, phone_type);
                aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner_phonetype.setAdapter(aa6);
                spinner_phonetype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(),emptype[position] , Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            }
        });

        return view;
    }
}
package com.rcloud.business.fragment.BusineesProfileFrag;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Tools.Loading;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.util.Constant;
import com.rcloud.netflix.ActivitySignUp;
import com.rcloud.netflix.R;

import org.json.JSONException;
import org.json.JSONObject;


public class ProfileFragment extends Fragment {
    String[] category = {"Select category", "IT", "Manufacture", "WholeSeller", "Seleing", "Import"};
    Spinner spinner_categogry;
    EditText comapny;
    TextView save;
    LinearLayout lnYes,lnNo;
    RatingBar rb_RatingBar;
    int rating = 0;
    String categoryVal = "";
    String companyName = "";

    String flag="0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile2, container, false);
        spinner_categogry = view.findViewById(R.id.spinner_categogry);
        comapny = view.findViewById(R.id.comapny);
        rb_RatingBar = view.findViewById(R.id.rb_RatingBar);
        save = view.findViewById(R.id.save);
        lnYes = view.findViewById(R.id.lnYes);
        lnNo = view.findViewById(R.id.lnNo);

        lnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag="1";

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    lnYes.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect_fill) );
                    lnNo.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect) );
                } else {
                    lnYes.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect_fill));
                    lnNo.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect));
                }
            }
        });
        lnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag="0";

                final int sdk = android.os.Build.VERSION.SDK_INT;
                if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    lnYes.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect) );
                    lnNo.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect_fill) );
                } else {
                    lnYes.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect));
                    lnNo.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.back_rect_fill));
                }
            }
        });

        rb_RatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                float a = rating;
                rating = (int) a;
                String ratingValue = String.valueOf(rating);

            }
        });
        companyName = comapny.getText().toString();


        ArrayAdapter aa1 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, category);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categogry.setAdapter(aa1);
        spinner_categogry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), category[position], Toast.LENGTH_LONG).show();
                if (category[position].equals("Select category")) {
                    categoryVal = "";
                } else {
                    categoryVal = category[position];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bpApi();
            }
        });
        return view;
    }

    private void bpApi() {
        Loading.loadingDialog(getActivity());

        JSONObject obj = new JSONObject();
        try {
            obj.put("companyname", companyName);
            obj.put("category", categoryVal);
            obj.put("brandvote", flag);
            obj.put("rating", rating + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, Constant.businessCareer, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Loading.loadingDismiss(getActivity());

                            JSONObject jsonObject = response.getJSONObject("messages");
                            String msg = jsonObject.getString("success");
                            Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                            // System.out.println(response + "response_result");

                        } catch (Exception e) {
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsObjRequest.setRetryPolicy(mRetryPolicy);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(jsObjRequest);
    }

}
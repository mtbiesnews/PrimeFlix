package com.rcloud.business;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.rcloud.netflix.R;

import java.util.ArrayList;

public class Vote extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.vote, container, false);
    }

//    ImageView git, google, you;
 //   ImageView amazon, link, ms;
 //   Button submit;
 //   Drawable highlight;

  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote);

        git = findViewById(R.id.git);
        link = findViewById(R.id.link);
        google = findViewById(R.id.google);
        ms = findViewById(R.id.ms);
        you = findViewById(R.id.you);
        amazon = findViewById(R.id.amazon);
        submit = findViewById(R.id.submit);
*/
     /*   amazon.setOnClickListener(new View.OnClickListener() {
         //   @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View v) {
                highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                amazon.setBackground(highlight);
            }
        });*/
      /*  submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amazon.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(),R.drawable.highlight, null);
                    amazon.setBackground(highlight);
                } else if (google.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                    google.setBackground(highlight);
                } else if (you.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                    you.setBackground(highlight);
                } else if (link.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                    link.setBackground(highlight);
                } else if (ms.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                    ms.setBackground(highlight);
                } else if (git.isClickable()) {
                    highlight = ResourcesCompat.getDrawable(getResources(), R.drawable.highlight, null);
                    git.setBackground(highlight);
                } else {
                    Toast.makeText(Vote.this,"Please Select Anyone",Toast.LENGTH_LONG).show();
                }

            }
        });*/
  //  }

}

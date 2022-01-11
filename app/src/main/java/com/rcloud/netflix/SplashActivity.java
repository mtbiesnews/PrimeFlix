package com.rcloud.netflix;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.rcloud.business.views.activites.WelcomeBanner;


public class SplashActivity extends AppCompatActivity {

    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 2000;
    MyApplication app;
    String strMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        app = MyApplication.getInstance();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (!mIsBackButtonPressed) {

//                    if (App.getIsLogin()) {
                        Intent intent = new Intent(getApplicationContext(), WelcomeBanner.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
//                    } else {
//                        Intent intent = new Intent(getApplicationContext(), ActivitySignIn.class);
//                        startActivity(intent);
//                        finish();
//                    }

                }
            }

        }, SPLASH_DURATION);
    }

    @Override
    public void onBackPressed() {
        // set the flag to true so the next activity won't start up
        mIsBackButtonPressed = true;
        super.onBackPressed();

    }
}

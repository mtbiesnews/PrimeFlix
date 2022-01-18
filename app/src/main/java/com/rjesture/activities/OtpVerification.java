package com.rjesture.activities;

import static com.Tools.AppUtils.getColoredSpanned;
import static com.Tools.IntentConstants.pagePath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;

import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityOtpVerificationBinding;
import com.rjesture.presenters.OtpVerify;

public class OtpVerification extends BaseActivity<ActivityOtpVerificationBinding> {

    Context mContext;
    String lastPage="";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_otp_verification;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
        CountreaderOTp();
    }

    private void setIds() {
        mContext = OtpVerification.this;
        lastPage= getIntent().getStringExtra(pagePath);

        dataBinding.setOtpVerify(new OtpVerify() {
            @Override
            public void reSendOtp() {
                CountreaderOTp();
            }

            @Override
            public void verifyOtp() {
                if(lastPage.equalsIgnoreCase("loginVerify")){
                    startActivity(new Intent(mContext,Login.class));
                    finish();
                }else{
                    startActivity(new Intent(mContext,GeneratePassword.class));
                    finish();
                }
            }
        });
    }
    private void CountreaderOTp() {
        long millisInFuture = 30000;
        long countDownInterval = 1000;
        new CountDownTimer(millisInFuture, countDownInterval) {
            public void onTick(long millisUntilFinished) {
                dataBinding.tvOtpTimer.setVisibility(View.VISIBLE);
                dataBinding.tvOtpTimer.setText("You will get OTP by SMS in " + millisUntilFinished / 1000 + " " + "Seconds");
                dataBinding.tvResendCode.setVisibility(View.GONE);
            }

            public void onFinish() {
                dataBinding.tvOtpTimer.setVisibility(View.GONE);
                String account = getColoredSpanned(getResources().getString(R.string.didntRecieveTheCode), String.valueOf(getResources().getColor(R.color.grey)));
                String signup = getColoredSpanned("<b>" + getResources().getString(R.string.resend) + "</b>", String.valueOf(getResources().getColor(R.color.colorPrimary)));
                dataBinding.tvResendCode.setText(Html.fromHtml(account + " " + signup));
                dataBinding.tvResendCode.setVisibility(View.VISIBLE);

            }
        }.start();
    }

}
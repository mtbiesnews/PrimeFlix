package com.rjesture.activities;

import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.showEditTextError;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityForgotPasswordBinding;
import com.rjesture.presenters.ForgetPassword;

public class ForgotPassword extends BaseActivity<ActivityForgotPasswordBinding> {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_forgot_password;
    }

    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
    }

    private void setIds() {
        mContext = ForgotPassword.this;
    dataBinding.setForgotPresenter(new ForgetPassword() {
        @Override
        public void sendOtp() {
         if(isValidated()){
             startActivity(new Intent(mContext,OtpVerification.class));
         }
        }
    });
    }
    private boolean isValidated() {
        if (getEtText(dataBinding.etUserName).isEmpty()) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.loginIdCannotBeBlank));
        }else {
            return true;
        }
        return false;
    }
}
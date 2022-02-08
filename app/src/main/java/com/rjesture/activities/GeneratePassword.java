package com.rjesture.activities;

import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.showEditTextError;
import static com.Tools.AppUtils.showToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityGeneratePasswordBinding;
import com.rjesture.presenters.GenNewPassword;

public class GeneratePassword extends BaseActivity<ActivityGeneratePasswordBinding> {

    Context mContext;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_generate_password;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
    }

    private void setIds() {
        mContext = GeneratePassword.this;
        dataBinding.setGenPass(new GenNewPassword() {
            @Override
            public void confirmPassword() {
                if(validated()){
                    showToast(mContext,"Password changed successfully");
                    startActivity(new Intent(mContext,Login.class));
                    finish();
                }
            }
        });

    }

    private boolean validated() {
        if (getEtText(dataBinding.etNewPassword).isEmpty()) {
            showEditTextError(dataBinding.etNewPassword, mContext, getResources().getString(R.string.passwordCannotBeBlank));
        } else if (getEtText(dataBinding.etNewPassword).length()<8) {
            showEditTextError(dataBinding.etNewPassword, mContext, getResources().getString(R.string.passwordLengthError));
        }else if (!getEtText(dataBinding.etNewPassword).equals(getEtText(dataBinding.etConfirmPassword))) {
            showEditTextError(dataBinding.etConfirmPassword, mContext, getResources().getString(R.string.passwordMismatch));
        }else{
            return true;
        }
            return false;
    }
}








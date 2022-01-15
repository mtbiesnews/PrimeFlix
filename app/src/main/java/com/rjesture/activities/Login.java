package com.rjesture.activities;

import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.showEditTextError;
import static com.Tools.AppUtils.showToast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityLoginBinding;
import com.rjesture.presenters.SignInUser;

public class Login extends BaseActivity<ActivityLoginBinding> {

    Context mContext;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setIds();
    }

    private void setIds() {
        mContext = Login.this;
        dataBinding.setLoginPresenter(new SignInUser() {
            @Override
            public void login() {
                if(isValidated()){
                    showToast(mContext,"Login Successful");
                }
            }

            @Override
            public void moveToForgetPasswordPage() {
                startActivity(new Intent(mContext,ForgotPassword.class));

            }

            @Override
            public void moveToDashBoardPage() {
                startActivity(new Intent(mContext,Dashboard.class));
            }
        });
        dataBinding.cbReferal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    dataBinding.tifReferal.setVisibility(View.VISIBLE);
                }else {
                    dataBinding.tifReferal.setText("");
                    dataBinding.tifReferal.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isValidated() {
        if (getEtText(dataBinding.etUserName).isEmpty()) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.loginIdCannotBeBlank));
        }else if (getEtText(dataBinding.etPassword).isEmpty()) {
            showEditTextError(dataBinding.etPassword, mContext, getResources().getString(R.string.passwordCannotBeBlank));
        }else if(dataBinding.cbReferal.isChecked() && getEtText(dataBinding.tifReferal).isEmpty()){
            showEditTextError(dataBinding.tifReferal, mContext, getResources().getString(R.string.enterReferalCode));
        }else {
            return true;
        }
        return false;
    }
}



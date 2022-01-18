package com.rjesture.activities;

import static com.Tools.ApiStrings.apiErrorException;
import static com.Tools.ApiStrings.apiErrorMessage;
import static com.Tools.ApiStrings.apiJson;
import static com.Tools.ApiStrings.apiResponse;
import static com.Tools.ApiStrings.apiUrl;
import static com.Tools.AppUrls.signInUrl;
import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.makeJsonObjectFromPojo;
import static com.Tools.AppUtils.showApiLog;
import static com.Tools.AppUtils.showEditTextError;
import static com.Tools.AppUtils.showLog;
import static com.Tools.AppUtils.showToast;
import static com.Tools.IntentConstants.pagePath;
import static com.Tools.Loading.loadingDialog;
import static com.Tools.Loading.loadingDismiss;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.Tools.ApiStrings;
import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityForgotPasswordBinding;
import com.rjesture.pojos.DefaultResponse;
import com.rjesture.presenters.ForgetPassword;
import com.rjesture.remote.retrofit.ApiService;
import com.rjesture.remote.retrofit.RetroClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends BaseActivity<ActivityForgotPasswordBinding> {

    Context mContext;
    String lastPage="";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
    }

    private void setIds() {
        mContext = ForgotPassword.this;
        lastPage= getIntent().getStringExtra(pagePath);
        if(lastPage.equalsIgnoreCase("loginVerify")){
            dataBinding.tvForgotHeader.setText(getResources().getString(R.string.verify_mobileNumber));
            dataBinding.tvSubHeader.setText(getResources().getString(R.string.enterYourMobileNumber));
            dataBinding.etUserName.setHint(getResources().getString(R.string.mobileNumber));
        }else{
            dataBinding.tvForgotHeader.setText(getResources().getString(R.string.forgot_password));
            dataBinding.tvSubHeader.setText(getResources().getString(R.string.enterYourEmailOrMobileNumber));
        }
        dataBinding.setForgotPresenter(new ForgetPassword() {
            @Override
            public void sendOtp() {
                if (isValidated()) {
                    startActivity(new Intent(mContext, OtpVerification.class).putExtra(pagePath,lastPage));

/*
                    ApiStrings.setApiString("SignIn");
                    showApiLog(apiUrl, signInUrl);
                    loadingDialog(mContext);
                    try {
                        ApiService apiService = RetroClass.getApiService();
                        apiService.signInUser(getEtText(dataBinding.etUserName),
                                getEtText(dataBinding.etPassword)).enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                loadingDismiss(mContext);
                                showApiLog(apiJson, makeJsonObjectFromPojo(response.raw().request().body(), mContext).toString());
                                if (response.body() != null) {
                                    DefaultResponse defaultResponse = response.body();
                                    showApiLog(apiResponse, makeJsonObjectFromPojo(response.body(), mContext).toString());
                                    if (defaultResponse.getStatus().toString().equalsIgnoreCase("true")) {
                                        showToast(mContext, defaultResponse.getMessage());
*/
/*
                                        showToast(mContext,getResources().getString(R.string.registerationSuccessful));
                                        startActivity(new Intent(mContext, ActivitySignIn.class));
                                        finish();
*//*

                                        */
/*    dataBinding.tvResponse.setText(makeJsonObjectFromPojo(defaultResponse,
                                                mContext).toString());
                                    *//*

                                    }else {
                                        startActivity(new Intent(mContext, OtpVerification.class).putExtra(pagePath,lastPage));
                                        showToast(mContext,defaultResponse.getMessage());
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                loadingDismiss(mContext);
                                showLog(apiErrorMessage, t.toString());
                                showToast(mContext, t.toString());
                            }
                        });
                    } catch (Exception e) {
                        showApiLog(apiErrorException, e.getMessage());
                        loadingDismiss(mContext);
                        showToast(mContext, e.getMessage());
                    }
*/

                }
            }
        });
    }

    private boolean isValidated() {
        if (getEtText(dataBinding.etUserName).isEmpty()) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.loginIdCannotBeBlank));
        } else {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(mContext,Login.class));
        finish();
    }
}
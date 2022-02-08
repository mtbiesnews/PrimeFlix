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
import com.rcloud.netflix.databinding.ActivityLoginBinding;
import com.rjesture.pojos.DefaultResponse;
import com.rjesture.presenters.SignInUser;
import com.rjesture.remote.retrofit.ApiService;
import com.rjesture.remote.retrofit.RetroClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                if (isValidated()) {
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
                                showApiLog(apiJson, response.toString());
                                  showApiLog(apiJson, makeJsonObjectFromPojo(response.body(), mContext).toString());
               /*                 if (response.body() != null) {
                                    DefaultResponse defaultResponse = response.body();
                                    showApiLog(apiResponse, makeJsonObjectFromPojo(response.body(), mContext).toString());
                                    if (defaultResponse.getStatus().toString().equalsIgnoreCase("true")) {
                                        showToast(mContext, defaultResponse.getMessage());
*//*
                                        showToast(mContext,getResources().getString(R.string.registerationSuccessful));
                                        startActivity(new Intent(mContext, ActivitySignIn.class));
                                        finish();
*//*
                                        *//*    dataBinding.tvResponse.setText(makeJsonObjectFromPojo(defaultResponse,
                                                mContext).toString());
                                    *//*
                                    } else if (defaultResponse.getMessage().equalsIgnoreCase("Your mobile is not verifed.")) {
                                        showToast(mContext, defaultResponse.getMessage());
                                        startActivity(new Intent(mContext, ForgotPassword.class).putExtra(pagePath,"loginVerify"));
                                        finish();

                                        *//*
                                        dataBinding.etName.setText(defaultResponse.getMessage());
                                        dataBinding.tvUserName.setText();
*//*
                                    }else {
                                        showToast(mContext,defaultResponse.getMessage());
                                    }

                                }
             */               }

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


                }
            }

            @Override
            public void moveToForgetPasswordPage() {
                startActivity(new Intent(mContext, ForgotPassword.class).putExtra(pagePath,"forgetPassword"));
                finish();

            }

            @Override
            public void moveToDashBoardPage() {
                startActivity(new Intent(mContext, Dashboard.class));
                finish();
            }
        });
    }

    private boolean isValidated() {
        if (getEtText(dataBinding.etUserName).isEmpty()) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.loginIdCannotBeBlank));
        } else if (getEtText(dataBinding.etPassword).isEmpty()) {
            showEditTextError(dataBinding.etPassword, mContext, getResources().getString(R.string.passwordCannotBeBlank));
        } else {
            return true;
        }
        return false;
    }
}



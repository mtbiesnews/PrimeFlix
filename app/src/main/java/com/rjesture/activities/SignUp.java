package com.rjesture.activities;

import static com.Tools.ApiStrings.apiErrorMessage;
import static com.Tools.ApiStrings.apiJson;
import static com.Tools.ApiStrings.apiResponse;
import static com.Tools.ApiStrings.apiUrl;
import static com.Tools.AppUrls.checkUserNameUrl;
import static com.Tools.AppUrls.signUpUrl;
import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.makeJsonObjectFromPojo;
import static com.Tools.AppUtils.showApiLog;
import static com.Tools.AppUtils.showEditTextError;
import static com.Tools.AppUtils.showLog;
import static com.Tools.AppUtils.showToast;
import static com.Tools.Loading.loadingDialog;
import static com.Tools.Loading.loadingDismiss;
import static com.Tools.Validate.isValidEmail;
import static com.Tools.Validate.isValidPhoneNumber;
import static com.Tools.Validate.isValidUserName;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.Tools.ApiStrings;
import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.ActivitySignIn;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivitySignupBinding;
import com.rjesture.pojos.DefaultResponse;
import com.rjesture.pojos.UserName;
import com.rjesture.presenters.SignUpUser;
import com.rjesture.remote.data.DataManager;
import com.rjesture.remote.retrofit.ApiService;
import com.rjesture.remote.retrofit.RetroClass;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends BaseActivity<ActivitySignupBinding> {

    DataManager dataManager;
    Context mContext;
    ApiStrings apiStrings;
    Boolean isUserNameAvailable = false;
    Calendar myCalendar;
    String userNameText="";


    @Override
    public int getLayoutResId() {
        return R.layout.activity_signup;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();

    }

    private void setIds() {
        mContext = SignUp.this;
        dataManager = new DataManager(mContext);
        apiStrings = new ApiStrings();
        dataBinding.etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                isUserNameAvailable = false;
                userNameText ="";
                dataBinding.etUserName.setError(null);
                dataBinding.btRegister.setVisibility(View.GONE);
                dataBinding.tvUserName.setVisibility(View.GONE);
            }
        });

        dataBinding.setSignUpPresenter(new SignUpUser() {
            @Override
            public void checkUsername() {
                isUserNameAvailable = false;
                userNameText ="";
                dataBinding.etUserName.setError(null);
                dataBinding.btRegister.setVisibility(View.GONE);
                dataBinding.tvUserName.setVisibility(View.GONE);
                //empty userName check
                if (getEtText(dataBinding.etUserName).isEmpty()) {
                    showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.userNameCannotBeBlank));
                }
                // userName length check
                else if (getEtText(dataBinding.etUserName).length() < 5) {
                    dataBinding.tvUserName.setVisibility(View.VISIBLE);
                    dataBinding.tvUserName.setText(getResources().getString(R.string.userNameLengthError));
                    dataBinding.tvUserName.setTextColor(getResources().getColor(R.color.red));
                }
                // userName validation
                else if (!isValidUserName(getEtText(dataBinding.etUserName))) {
                    dataBinding.tvUserName.setVisibility(View.VISIBLE);
                    dataBinding.tvUserName.setText(getResources().getString(R.string.userNameCanBeAlphanumeric));
                    dataBinding.tvUserName.setTextColor(getResources().getColor(R.color.red));
                } else {
                    ApiStrings.setApiString("CheckUserName");
                    showApiLog(apiUrl, checkUserNameUrl);
                    loadingDialog(mContext);
                    try {
                        ApiService apiService = RetroClass.getApiService();
                        apiService.getUserName(getEtText(dataBinding.etUserName)).enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                loadingDismiss(mContext);
                                userNameText=getEtText(dataBinding.etUserName)+getResources().getString(R.string.companyHandle);
                                showApiLog(apiJson,makeJsonObjectFromPojo(response.raw().request().body(),mContext).toString());
                                if (response.body() != null) {
                                    dataBinding.tvUserName.setVisibility(View.VISIBLE);
                                    DefaultResponse defaultResponse = response.body();
                                    showApiLog(apiResponse,makeJsonObjectFromPojo(response.body(),mContext).toString());
                                    if (defaultResponse.getStatus().toString().equalsIgnoreCase("true")) {
                                        isUserNameAvailable = true;
                                        dataBinding.tvUserName.setText(userNameText+" "+getResources().getString(R.string.userNameAvailable));
                                        dataBinding.tvUserName.setTextColor(getResources().getColor(R.color.green));
                                        dataBinding.btRegister.setVisibility(View.VISIBLE);

                                    } else {
                                        dataBinding.tvUserName.setTextColor(getResources().getColor(R.color.red));
                                        dataBinding.tvUserName.setText(userNameText+" "+getResources().getString(R.string.userNameUnAvailable));
                                        /*
                                        dataBinding.etName.setText(defaultResponse.getMessage());
                                        dataBinding.tvUserName.setText(makeJsonObjectFromPojo(response.body(),
                                                mContext).toString());
*/
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
                        showLog("MyUser", "c" + e.getMessage());
                        loadingDismiss(mContext);
                        showToast(mContext, e.getMessage());
                    }

                /*dataManager.sendVolleyPostRequest(mContext, checkUserNameUrl, new DataValues() {
                    @Override
                    public void setDataResponse(JSONObject jsonObject) {
                        showApiLog(apiResponse,jsonObject.toString());
                        loadingDismiss(mContext);
                    }

                    @Override
                    public void setVolleyError(VolleyError volleyError) {
                        showApiLog(apiErrorMessage, volleyError.getMessage());
                        loadingDismiss(mContext);
                    }
                }, makeJsonObject());*/
                }
            }

            @Override
            public void moveToLoginPage() {
                startActivity(new Intent(mContext,ActivitySignIn.class));
            }

            @Override
            public void registerUser() {
                if (isDataValidated()) {
                    ApiStrings.setApiString("SignUp");
                    showApiLog(apiUrl, signUpUrl);
                    loadingDialog(mContext);
                    try {
                        ApiService apiService = RetroClass.getApiService();
                        apiService.signUpUser(userNameText,
                                getEtText(dataBinding.etPhone),
                                getEtText(dataBinding.etPassword),
                                getEtText(dataBinding.etName),
                                getEtText(dataBinding.etEmail),
                                getEtText(dataBinding.tvDate)).enqueue(new Callback<DefaultResponse>() {
                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                loadingDismiss(mContext);
                                showApiLog(apiJson,makeJsonObjectFromPojo(response.raw().request().body(),mContext).toString());
                                if (response.body() != null) {
                                    DefaultResponse defaultResponse = response.body();
                                    showApiLog(apiResponse,makeJsonObjectFromPojo(response.body(),mContext).toString());
                                    if (defaultResponse.getStatus().toString().equalsIgnoreCase("true")) {
                                         showToast(mContext,getResources().getString(R.string.registerationSuccessful));
                                         startActivity(new Intent(mContext, ActivitySignIn.class));
                                         finish();
                                        /*    dataBinding.tvResponse.setText(makeJsonObjectFromPojo(defaultResponse,
                                                mContext).toString());
                                    */} else {
                                        showToast(mContext,defaultResponse.getMessage());

                                        /*
                                        dataBinding.etName.setText(defaultResponse.getMessage());
                                        dataBinding.tvUserName.setText();
*/
                                    }
                                }
                                response.message();
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                loadingDismiss(mContext);
                                showLog(apiErrorMessage, t.toString());
                                showToast(mContext, t.toString());
                            }
                        });
                    } catch (Exception e) {
                        showLog("MyUser", "c" + e.getMessage());
                        loadingDismiss(mContext);
                        showToast(mContext, e.getMessage());
                    }

                }

            }

            @Override
            public void pickUpDate() {
                myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                };
                DatePickerDialog datePickerDialog =new DatePickerDialog(mContext, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(myCalendar.getTimeInMillis());
                datePickerDialog.show();
            }

        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";//"dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dataBinding.tvDate.setText(sdf.format(myCalendar.getTime()));
    }


    private boolean isDataValidated() {
        if (getEtText(dataBinding.etName).isEmpty()) {
            showEditTextError(dataBinding.etName, mContext, getResources().getString(R.string.nameCannotBeBlank));
        } else if((!getEtText(dataBinding.etEmail).isEmpty())&&(!isValidEmail(getEtText(dataBinding.etEmail)))) {
                showEditTextError(dataBinding.etEmail, mContext, getResources().getString(R.string.emailIsInvalid));
        } else if (getEtText(dataBinding.etUserName).isEmpty()) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.userNameCannotBeBlank));
        } else if (!isUserNameAvailable) {
            showEditTextError(dataBinding.etUserName, mContext, getResources().getString(R.string.userNameIsNotValidated));
        } else if (!isValidPhoneNumber(getEtText(dataBinding.etPhone))) {
            showEditTextError(dataBinding.etPhone, mContext, getResources().getString(R.string.mobileNumberIsInvalid));
        } else if (getEtText(dataBinding.tvDate).isEmpty()) {
            showToast(mContext, getResources().getString(R.string.dateCannotBeBlank));
        } else if (getEtText(dataBinding.etPassword).isEmpty()) {
            showEditTextError(dataBinding.etPassword, mContext, getResources().getString(R.string.passwordCannotBeBlank));
        } else if (getEtText(dataBinding.etPassword).length()<8) {
            showEditTextError(dataBinding.etPassword, mContext, getResources().getString(R.string.passwordLengthError));
        } else {
            return true;
        }
        return false;
    }


    private JSONObject makeJsonObject() {
        UserName userName = new UserName();
        userName.setUserName(getEtText(dataBinding.etUserName));
        showApiLog(apiJson, makeJsonObjectFromPojo(userName, mContext).toString());
        return makeJsonObjectFromPojo(userName, mContext);

    }


}
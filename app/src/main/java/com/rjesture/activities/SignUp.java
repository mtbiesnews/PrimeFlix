package com.rjesture.activities;

import static com.Tools.ApiStrings.apiErrorMessage;
import static com.Tools.ApiStrings.apiJson;
import static com.Tools.ApiStrings.apiResponse;
import static com.Tools.ApiStrings.apiUrl;
import static com.Tools.AppUrls.checkUserNameUrl;
import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.handleError;
import static com.Tools.AppUtils.makeJsonObjectFromPojo;
import static com.Tools.AppUtils.showApiLog;
import static com.Tools.AppUtils.showLog;
import static com.Tools.AppUtils.showToast;
import static com.Tools.Loading.dialog;
import static com.Tools.Loading.loadingDialog;
import static com.Tools.Loading.loadingDismiss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.Tools.ApiStrings;
import com.Tools.AppUrls;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivitySignupBinding;
import com.rjesture.pojos.UserName;
import com.rjesture.presenters.SignUpUser;
import com.rjesture.remote.data.DataManager;
import com.rjesture.remote.data.DataValues;

import org.json.JSONObject;

public class SignUp extends BaseActivity<ActivitySignupBinding> {

    DataManager dataManager;
    Context mContext;
    ApiStrings apiStrings;

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
        dataBinding.setSignUpPresenter(() -> {
            if(getEtText(dataBinding.etUserName).isEmpty()){
                dataBinding.etUserName.setError("UserName cannot be blank");
//                dataBinding.etUserName
                dataBinding.btRegister.setVisibility(View.GONE);
            }else {
                apiStrings.setApiString("Check");
                showApiLog(apiUrl,checkUserNameUrl);
                loadingDialog(mContext);
                dataManager.sendVolleyPostRequest(mContext, checkUserNameUrl, new DataValues() {
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
                }, makeJsonObject());
            }
        });
    }

    private JSONObject makeJsonObject() {
        UserName userName = new UserName();
        userName.setUserName(getEtText(dataBinding.etUserName));
        showApiLog(apiJson,makeJsonObjectFromPojo(userName,mContext).toString());
        return makeJsonObjectFromPojo(userName,mContext);

    }


}
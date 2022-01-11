package com.rcloud.netflix;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Tools.Loading;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.CustomRequest;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.rcloud.base.BaseActivity;
import com.rjesture.presenters.SignUpUser;
import com.rjesture.remote.data.DataManager;
import com.rjesture.remote.data.DataValues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.util.Constant.social_user_server_url;


public class ActivitySignUp extends AppCompatActivity implements Validator.ValidationListener {

    RelativeLayout lay_sign;
    @NotEmpty
    EditText edtFullName;
    @Email
    EditText edtEmail;
    @Password
    EditText edtPassword;
    @Length(max = 10, min = 10, message = "Enter valid Phone Number")
    EditText edtMobile;
    Button btnSignUp;
    String strName, strEmail, strPassword, strMobi, strMessage;
    private Validator validator;
    ProgressDialog pDialog;
    JsonUtils jsonUtils;
    TextView tv_date,txt_create;
    Calendar myCalendar;
    String from_date,todate,from,to;
    TextView txt_from_date,txt_to_date;
    DataManager dataManager;


    String login_key;


    Date c;
    String todaydate;
    SimpleDateFormat simpleDateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setInits();
        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        lay_sign = findViewById(R.id.lay_sign);
        pDialog = new ProgressDialog(this);
        edtFullName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtMobile = findViewById(R.id.edt_phone);
        tv_date = findViewById(R.id.tv_date);
        txt_create = findViewById(R.id.txt_create);

        btnSignUp = findViewById(R.id.button);

        lay_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_in = new Intent(ActivitySignUp.this, ActivitySignIn.class);
                intent_in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_in);
            }
        });
        txt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_in = new Intent(ActivitySignUp.this, ActivitySignIn.class);
                intent_in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent_in);
            }
        });

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myCalendar = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };
                new DatePickerDialog(ActivitySignUp.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validator.validate();
            }
        });
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void setInits() {
        dataManager = new DataManager(this);
        /*dataBinding.setSignUpPresenter(new SignUpUser() {
            @Override
            public void checkUsername() {
                dataManager.sendVolleyPostRequest(ActivitySignUp.this, new DataValues() {
                    @Override
                    public void setDataResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void setVolleyError(VolleyError volleyError) {

                    }
                },makeJsonData());
            }
        });*/
    }

    private JSONObject makeJsonData() {
        return new JSONObject();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";//"dd-MMM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        from_date= sdf.format(myCalendar.getTime());
        tv_date.setText(from_date);
    }
    @Override
    public void onValidationSucceeded() {
        strName = edtFullName.getText().toString();
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        strMobi = edtMobile.getText().toString();
        if (JsonUtils.isNetworkAvailable(ActivitySignUp.this)) {
            check();
            //new MyTaskRegister().execute(Constant.REGISTER_URL + strName + "&email=" + strEmail+"&password="+strPassword+"&phone="+strMobi);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void check() {
        Loading.loadingDialog(ActivitySignUp.this);

        JSONObject obj = new JSONObject();
        try {
            // obj.put("tpe", "mobile");
            //obj.put("mtbID", "test");
           /* obj.put("name", "Subhash Rathour");
            obj.put("email", "subhashrathour82015@gmail.com");
            obj.put("dob", "15/05/1998");
            obj.put("mobile", "7800750614");
            obj.put("password", "123456");*/

            obj.put("name", strName);
            obj.put("email", strEmail);
            obj.put("dob", tv_date.getText().toString());
            obj.put("mobile", strMobi);
            obj.put("password", strPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, Constant.createCareer, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject=response.getJSONObject("messages");
                            String msg=jsonObject.getString("success");
                            Toast.makeText(ActivitySignUp.this, ""+msg, Toast.LENGTH_SHORT).show();
                            // System.out.println(response + "response_result");
                            Loading.loadingDismiss(ActivitySignUp.this);
                        }
                        catch (Exception e)
                        {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                    }
                });
        RetryPolicy mRetryPolicy = new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        jsObjRequest.setRetryPolicy(mRetryPolicy);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(jsObjRequest);
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTaskRegister extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog();
        }

        @Override
        protected String doInBackground(String... params) {
            return JsonUtils.getJSONString(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dismissProgressDialog();

            if (null == result || result.length() == 0) {
                showToast(getString(R.string.no_data));

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        strMessage = objJson.getString(Constant.MSG);
                        Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult();
            }
        }
    }

    public void setResult() {

        if (Constant.GET_SUCCESS_MSG == 0) {
            edtEmail.setText("");
            edtEmail.requestFocus();
            showToast(strMessage);
        } else {
            showToast(strMessage);
            Intent intent = new Intent(ActivitySignUp.this, ActivitySignIn.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(ActivitySignUp.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog() {
        pDialog.setMessage(getString(R.string.loading));
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    public void dismissProgressDialog() {
        pDialog.dismiss();
    }
}
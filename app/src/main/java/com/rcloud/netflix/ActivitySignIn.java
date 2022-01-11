package com.rcloud.netflix;

import static com.Tools.AppUtils.getEtText;
import static com.Tools.AppUtils.showToast;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.Tools.Loading;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.util.Constant;
import com.example.util.JsonUtils;
import com.example.util.PrefManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.databinding.ActivitySignInBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ActivitySignIn extends BaseActivity<ActivitySignInBinding> implements GoogleApiClient.OnConnectionFailedListener {
    Context mActivity;
    private PrefManager prf;

    private static final String TAG = "ActivitySignIn";
    private static final int RC_SIGN_IN = 9001;
    public JSONObject loginobject;
    public GoogleApiClient mGoogleApiClient;
    RelativeLayout lay_sign, lay_forgot;
    Button button_sign_up;
    TextInputEditText promocode;
    String strEmail, strPassword, strMessage, strName, strUserId, strPromocode;
    Button btnLogin;
    TextView skiplogin;
    MyApplication myApp;
    ProgressDialog pDialog;
    CheckBox checkBox;
    boolean iswhichscreen;
    String videoiddetail;
    JsonUtils jsonUtils;
    //rajan
    private SignInButton sign_in_button_google;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());

                    Log.e("tasss", result.getData().toString());

                    GoogleSignInAccount account = null;
                    try {
                        account = task.getResult(ApiException.class);
                        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                        firebaseAuthWithGoogle(account);
                    } catch (ApiException e) {
                        e.printStackTrace();
                    }


                }
            });
    private GoogleSignInOptions gso;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_sign_in;
    }

    /*  @Override
          public void onStart() {
              super.onStart();
              OptionalPendingResult<GoogleSignInResult> imp = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
              if (imp.isDone()) {
                  GoogleSignInResult result = imp.get();
                  getResultGoogle(result);

               //   GoogleSignInResult result = imp.get();
               //   handleSignInResult(result);
              } else {
                  imp.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                      @Override
                      public void onResult(@NonNull GoogleSignInResult result) {
                          getResultGoogle(result);
                      }
                  });
              }
          }*/
    //  @Override
 /*     public void onStart() {
           super.onStart();
//     Check if user is signed in (non-null) and update UI accordingly.
           FirebaseUser currentUser = mAuth.getCurrentUser();
           if (currentUser!=null){
               startActivity(new Intent(getApplicationContext(), AllVideoFragment.class));
           }
       }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setIds();
        setListeners();

        checkBox = findViewById(R.id.checkBox);
        promocode = findViewById(R.id.promocode);
//        btnLogin = findViewById(R.id.button_login);
        skiplogin = findViewById(R.id.skip);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        iswhichscreen = intent.getBooleanExtra("isfromdetail", false);
        videoiddetail = intent.getStringExtra("isvideoid");

        sign_in_button_google = findViewById(R.id.sign_in_button);
        sign_in_button_google = findViewById(R.id.sign_in_button);
        sign_in_button_google.setSize(SignInButton.SIZE_STANDARD);
        TextView textView = (TextView) sign_in_button_google.getChildAt(0);
        textView.setText(getResources().getString(R.string.login_gg_text));
        processRequest();

//        if(config.demo) {
//            ((TextView) findViewById(R.id.txt)).setVisibility(View.VISIBLE);
//            ((TextView) findViewById(R.id.txtcodecanyon)).setVisibility(View.VISIBLE);
//        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    promocode.setVisibility(View.VISIBLE);
                } else {
                    promocode.setVisibility(View.GONE);
                }

            }
        });

/*        try {
            GoogleSignIn();
        } catch (Exception e) {
            e.printStackTrace();
            showDefToast("Something went wrong. Please try again!");
        }
*/
        sign_in_button_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    signIn();
                } else {
                    connected = false;
                    Toast.makeText(ActivitySignIn.this, "No internet. Check Your Internet is connection", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        lay_sign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent_up = new Intent(ActivitySignIn.this, ActivitySignUp.class);
//                startActivity(intent_up);
//            }
//        });
//
//        button_sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent_up = new Intent(ActivitySignIn.this, ActivitySignUp.class);
//                startActivity(intent_up);
//            }
//        });

        skiplogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySignIn.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

//        lay_forgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ActivitySignIn.this, ActivityForgot.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//            }
//        });
//
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validator.validate();
//            }
//        });


//        if (MyApp.getIsRemember()) {
//            checkBox.setChecked(true);
//            edtEmail.setText(MyApp.getRememberEmail());
//            edtPassword.setText(MyApp.getRememberPassword());
//        }

    }

    private void setListeners() {
        dataBinding.btnSignIn.setOnClickListener(v -> {
            if (isValidated()) {
                signInApi();
            }
        });
    }

    private boolean isValidated() {
        if (getEtText(dataBinding.etUserName).isEmpty())
            showToast(mActivity, getResources().getString(R.string.userNameCannotBeBlank));
        else if (getEtText(dataBinding.etPassword).isEmpty())
            showToast(mActivity, getResources().getString(R.string.passwordCannotBeBlank));
        else
            return true;
        return false;
    }

    private void setIds() {
        prf = new PrefManager(getApplicationContext());
        mActivity = ActivitySignIn.this;
        myApp = MyApplication.getInstance();
        pDialog = new ProgressDialog(this);
        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());
    }

    private void signInApi() {
        Loading.loadingDialog(ActivitySignIn.this);
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", getEtText(dataBinding.etUserName));
            obj.put("password", getEtText(dataBinding.etPassword));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, Constant.login, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonObject = response.getJSONObject("messages");
                            String msg = jsonObject.getString("success");
                            Toast.makeText(ActivitySignIn.this, "" + msg, Toast.LENGTH_SHORT).show();
                            // System.out.println(response + "response_result");
                            Loading.loadingDismiss(ActivitySignIn.this);
                        } catch (Exception e) {
                        }
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

    private void processRequest() {

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();
    }

    private void signIn() {

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);

    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        OptionalPendingResult<GoogleSignInResult> imp = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "SignIn : Success", Toast.LENGTH_LONG).show();
                            // Sign in success, update UI with the signed-in user's information
                            //  FirebaseUser user = mAuth.getCurrentUser();
                            // getResultGoogle(result);
                            if (imp.isDone()) {
                                GoogleSignInResult result = imp.get();
                                getResultGoogle(result);
                            } else {
                                // If sign in fails, display a message to the user.
                                //  Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "SignIn : Failure", Toast.LENGTH_LONG).show();
                                getResultGoogle(null);
                            }
                        }
                    }
                });
    }

    public void GoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        System.out.println("Rajan_google_login_onConnectionFailed:" + connectionResult);

    }

    private void getResultGoogle(GoogleSignInResult result) {
//        System.out.println("Rajan_google_login_handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();
            String photo = "https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg";
            if (acct.getPhotoUrl() != null) {
                photo = acct.getPhotoUrl().toString();
            }

//            System.out.println("Rajan_google_login_detail"+acct.getId().toString()+acct.getId()+ acct.getDisplayName().toString()+"google"+photo);
            String gid;
            if (acct.getId() != null) {
                gid = acct.getId();
            } else {
                gid = "1234567890";
            }

            strPromocode = promocode.getText().toString();

            if (acct.getDisplayName() != null) {
                signUp(gid, acct.getEmail(), acct.getDisplayName().replaceAll("[^A-Za-z0-9]", ""), strPromocode, "google", photo);
            } else {
                signUp(gid, acct.getEmail(), acct.getEmail().replaceAll("[^A-Za-z0-9]", ""), strPromocode, "google", photo);
            }
            //    GoogleSignInClient.signOut(mGoogleSignInClient);
            mGoogleSignInClient.signOut();
            //    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        } else {
//            System.out.println("Rajan_google_login_handleSignInResult:" + result.getStatus());

        }
    }

    private void signUp(String toString, String strEmail, String strName, String strPromocode, String google, String photo) {
        try {
            if (JsonUtils.isNetworkAvailable(ActivitySignIn.this)) {
                strPassword = "12345";
                new MyTaskRegister().execute(Constant.REGISTER_URL_GOOGLE + strName + "&email=" + strEmail + "&password=" + strPassword + "&phone=" + toString + "&promocode=" + strPromocode + "&pkg=" + getPackageName());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showDefToast("Something went wrong. Please try again!");
        }
    }

    public void setResult() {

        if (Constant.GET_SUCCESS_MSG == 0) {
            showDefToast(getString(R.string.error_title) + "\n" + strMessage);
        } else {
            myApp.saveIsLogin(true);
            myApp.saveLogin(strUserId, strName, strEmail);
            if (iswhichscreen) {
                Intent i = new Intent(ActivitySignIn.this, ActivityVideoDetails.class);
                i.putExtra("isvideoid", videoiddetail);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            } else {
                ActivityCompat.finishAffinity(ActivitySignIn.this);
                Intent i = new Intent(ActivitySignIn.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
/*

    @Override
    public void onValidationSucceeded() {
        // TODO Auto-generated method stub
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();

        if (checkBox.isChecked()) {
            myApp.saveIsRemember(true);
            myApp.saveRemember(strEmail, strPassword);
        } else {
            myApp.saveIsRemember(false);
        }

        try {
            if (JsonUtils.isNetworkAvailable(ActivitySignIn.this)) {
                prf.setString(Constant.USER_EMAIL, strEmail);
                new MyTaskLogin().execute(Constant.LOGIN_URL + strEmail + "&password=" + strPassword);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showDefToast("Something went wrong. Please try again!");
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
*/

    public void showDefToast(String msg) {
        Toast.makeText(ActivitySignIn.this, msg, Toast.LENGTH_SHORT).show();
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

    class MyTaskRegister extends AsyncTask<String, Void, String> {

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

            if (result == null) {
                if (result.length() == 0) {
                    showDefToast("Something went wrong. Try Again!");
                }

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        loginobject = jsonArray.getJSONObject(i);

                        if (objJson.has(Constant.MSG)) {
                            strMessage = objJson.getString(Constant.MSG);
                            Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);
                        } else {
                            Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);
                            strName = objJson.getString(Constant.USER_NAME);
                            strUserId = objJson.getString(Constant.USER_ID);

                            if (loginobject != null) {
                                try {
                                    System.out.println("Rajan_login_user_id" + loginobject.getString(Constant.USER_ID));
                                    prf.setString(Constant.USER_ID, loginobject.getString(Constant.USER_ID));
                                    prf.setString(Constant.USER_NAME, loginobject.getString(Constant.USER_NAME));
                                    prf.setString(Constant.USER_EMAIL, loginobject.getString(Constant.USER_EMAIL));
                                    prf.setString(Constant.TAG_PLANID, loginobject.getString(Constant.TAG_PLANID));
                                    prf.setString(Constant.TAG_PLANACTIVE, loginobject.getString(Constant.TAG_PLANACTIVE));
                                    prf.setString(Constant.TAG_PLANDAYS, loginobject.getString(Constant.TAG_PLANDAYS));
                                    prf.setString(Constant.TAG_PLANSTART, loginobject.getString(Constant.TAG_PLANSTART));
                                    prf.setString(Constant.TAG_PLANEND, loginobject.getString(Constant.TAG_PLANEND));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult();
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class MyTaskLogin extends AsyncTask<String, Void, String> {

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

            if (result == null) {
                if (result.length() == 0) {
                    showDefToast("Something went wrong. Try Again!");
                }

            } else {

                try {
                    JSONObject mainJson = new JSONObject(result);
                    JSONArray jsonArray = mainJson.getJSONArray(Constant.LATEST_ARRAY_NAME);
                    JSONObject objJson;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        objJson = jsonArray.getJSONObject(i);
                        loginobject = jsonArray.getJSONObject(i);

                        if (objJson.has(Constant.MSG)) {
                            strMessage = objJson.getString(Constant.MSG);
                            Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);
                        } else {
                            Constant.GET_SUCCESS_MSG = objJson.getInt(Constant.SUCCESS);
                            strName = objJson.getString(Constant.USER_NAME);
                            strUserId = objJson.getString(Constant.USER_ID);

                            if (loginobject != null) {
                                try {
                                    System.out.println("Rajan_login_user_id" + loginobject.getString(Constant.USER_ID));
                                    prf.setString(Constant.USER_ID, loginobject.getString(Constant.USER_ID));
                                    prf.setString(Constant.USER_NAME, loginobject.getString(Constant.USER_NAME));
                                    prf.setString(Constant.TAG_PLANID, loginobject.getString(Constant.TAG_PLANID));
                                    prf.setString(Constant.TAG_PLANACTIVE, loginobject.getString(Constant.TAG_PLANACTIVE));
                                    prf.setString(Constant.TAG_PLANDAYS, loginobject.getString(Constant.TAG_PLANDAYS));
                                    prf.setString(Constant.TAG_PLANSTART, loginobject.getString(Constant.TAG_PLANSTART));
                                    prf.setString(Constant.TAG_PLANEND, loginobject.getString(Constant.TAG_PLANEND));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                setResult();
            }
        }
    }
}

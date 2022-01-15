package com.Tools;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by Rjesture on 12/28/2021.
 */
public class AppUtils {

    public static void showToast(Context context, String message) {
        if (message.isEmpty())
            message = "Null Message";
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }


    public static void showLog(String title, String message) {
        Log.v(title, "Message:  " + message);
    }

    public static void showApiLog(String title, String message) {
        Log.v(title, message);
    }

    public static void handleError(Context context, String message) {
        Log.v("Exception", "Error:  " + message);
        showToast(context, "Exception Error");
    }

    public static String getEtText(TextInputEditText editText) {
        return editText.getText().toString().trim();
    }

    public static String getEtText(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static String getEtText(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static JSONObject makeJsonObjectFromPojo(Object object, Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(object);
            jsonObject = new JSONObject(json);
        } catch (Exception e) {
            handleError(context, e.getMessage());
        }

        return jsonObject;
    }
    public static void makePojoFromJSON(){
        String maxJson = "{\"firstName\":\"Max\",\"lastName\":\"Tegmark\",\"studentID\":1254,\"email\":\"max.tegmark@mit.edu\",\"courses\":[\"Physics 8.01\",\"Physics 8.012\"],\"financeType\":\"SUBSIDIZED\"}";
        Gson gson = new Gson();
//        Student max = gson.fromJson(maxJson, Student.class);
    }

    public static void showEditTextError(EditText editText, Context mContext, String message) {
        editText.setError(message);
        showToast(mContext,message);
    }



}

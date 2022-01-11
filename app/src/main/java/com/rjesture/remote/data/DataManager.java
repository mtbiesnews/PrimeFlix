package com.rjesture.remote.data;

import static com.rjesture.remote.APICALL.BASEURL;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.rjesture.remote.VolleySingleton;

import org.json.JSONObject;

/**
 * Created by Rjesture on 12/12/2021.
 */
public class DataManager {
    private final Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    public void sendVolleyGetRequest(Context context, DataValues dataValues) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, BASEURL, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dataValues.setDataResponse(response);

            }
        }, error -> dataValues.setVolleyError(error));
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void sendVolleyPostRequest(Context context,String baseUrl, DataValues dataValues, JSONObject jsonObject) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, baseUrl,
                jsonObject, response -> dataValues.setDataResponse(response),
                            error -> dataValues.setVolleyError(error));
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}

package com.rjesture.remote.data;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Rjesture on 12/21/2021.
 */
public interface DataValues {

    public void setDataResponse(JSONObject jsonObject);
    public void setVolleyError(VolleyError volleyError);
}

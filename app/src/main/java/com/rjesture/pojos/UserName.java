
package com.rjesture.pojos;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

public class UserName {

    @SerializedName("user_name")
    private String mUserName;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

}

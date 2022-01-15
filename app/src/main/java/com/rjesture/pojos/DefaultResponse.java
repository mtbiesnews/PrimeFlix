
package com.rjesture.pojos;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;


public class DefaultResponse {

    @Expose
    private String message;
    @Expose
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}

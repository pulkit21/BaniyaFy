package com.example.pulkit.baniyafy;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pulkit on 6/5/15.
 */
public class RestError {
    @SerializedName("code")
    public int code;
    @SerializedName("error")
    public String errorDetails;
}

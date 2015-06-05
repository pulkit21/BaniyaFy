package com.example.pulkit.baniyafy;



import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by pulkit on 6/4/15.
 */
public interface BaniyafyServices {
    @POST("/api/sessions")
    public void signInUser(@Body UserObject userObject, Callback<Object> userSignInCallback);
}

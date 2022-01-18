package com.rjesture.remote.retrofit;

import com.rjesture.pojos.DefaultResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Rjesture on 1/13/2022.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("User/Check_username")
    Call<DefaultResponse> getUserName(@Field("user_name") String userName);

    @FormUrlEncoded
    @POST("User/u_signup")
    Call<DefaultResponse> signUpUser(@Field("user_name") String userName,
                                       @Field("phone") String phoneNumber,
                                       @Field("password") String password,
                                       @Field("name") String name,
                                       @Field("email") String email,
                                       @Field("dob") String dob);
    @FormUrlEncoded
    @POST("User/u_signinpassword")
    Call<DefaultResponse> signInUser(@Field("user_phone") String userName,
                                       @Field("password") String password);

}

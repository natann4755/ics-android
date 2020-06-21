package com.example.icsproject.retrofit;

import com.example.model.Result;
import com.example.model.User;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceIcs {

    @GET("/api/users/{userId}")
    Call<Result<User>> getUser(@Header("Authorization") String token, @Path("userId")String userId);

    @POST("/api/users/{userId}")
    Call <Result> sendFirebaseToken(@Header("Authorization") String userToken, @Path("userId")String userId, @Body String userMail);


    @POST("/api/users/{userId}")
    Call <Result> sendPhoneNumber (@Header("Authorization") String userToken, @Path("userId")String userId, @Body String userPhone);

    @POST("/api/users/{userId}")
    Call <Result> sendSignUpVerificationCode  (@Header("Authorization") String userToken, @Path("userId") String userId, @Body String VerificationCode);



}

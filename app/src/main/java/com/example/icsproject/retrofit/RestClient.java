package com.example.icsproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String BASE_URL = "https:....";

    private static Retrofit retrofit = new Retrofit.Builder().
            baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static ServiceIcs icsService = retrofit.create(ServiceIcs.class);

}

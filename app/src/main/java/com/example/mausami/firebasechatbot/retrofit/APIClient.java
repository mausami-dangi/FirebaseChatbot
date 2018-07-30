package com.example.mausami.firebasechatbot.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mausami on 15/05/2018.
 */

public class APIClient {
    private static Retrofit retrofit = null;
//    static String BASE_URL = "http://192.168.71.77:8000";
      static String BASE_URL = "http://aeassist.msbcgroup.co.uk:8090/";


    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
//    private static Retrofit retrofit = null;
//    static String BASE_URL = "https://fcm.googleapis.com";
//
//
//    public static Retrofit getClient() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit;
//    }
}

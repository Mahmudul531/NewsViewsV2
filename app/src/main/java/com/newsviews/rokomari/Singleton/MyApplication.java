package com.newsviews.rokomari.Singleton;

import android.app.Application;
import android.content.res.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.newsviews.rokomari.Api.MyApiEndpointInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static final String BASE_URL = "https://newsapi.org/v2/";

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    MyApiEndpointInterface apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService =
                retrofit.create(MyApiEndpointInterface.class);
    }

    public MyApiEndpointInterface getApiService() {
        return apiService;
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

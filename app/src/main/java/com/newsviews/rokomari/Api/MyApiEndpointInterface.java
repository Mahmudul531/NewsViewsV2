package com.newsviews.rokomari.Api;

import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.Model.NewsRequestModel;
import com.newsviews.rokomari.Model.SearchNumberModel;
import com.newsviews.rokomari.Model.SearchYearModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyApiEndpointInterface {

    @GET("top-headlines")
    Call<NewsModel> getNews(@Query("country") String country, @Query("apiKey") String apiKey);


    @GET("{month}/{day}/date?json")
    Call<SearchYearModel> getSearchDate(@Path("month") String month, @Path("day") String day);

    @GET("{number}?json")
    Call<SearchNumberModel> getSearchNumber(@Path("number") String number);


}

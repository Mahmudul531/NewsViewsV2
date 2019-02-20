package com.newsviews.rokomari.Features.Home;

import android.content.Context;
import android.widget.Toast;

import com.newsviews.rokomari.Api.MyApiEndpointInterface;
import com.newsviews.rokomari.BasePresenter.iBasePresenter;
import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.Model.NewsRequestModel;
import com.newsviews.rokomari.Singleton.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeModel {

    Context context;
    MyApiEndpointInterface myApiEndpointInterface;

    iBasePresenter<NewsModel> basePresenter;

    public HomeModel(Context context, iBasePresenter<NewsModel> iBasePresenter) {
        this.context = context;
        this.basePresenter = iBasePresenter;
    }

    public void fetchMyNewsData() {
        myApiEndpointInterface = ((MyApplication) this.context.getApplicationContext()).getApiService();

        NewsRequestModel newsRequestModel = new NewsRequestModel();
        newsRequestModel.setCountry("us");
        newsRequestModel.setApiKey("bc3bd9d44dd646958655fa0b162b5486");
        Call<NewsModel> call = myApiEndpointInterface.getNews(newsRequestModel.getCountry(), newsRequestModel.getApiKey());
        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {

                NewsModel news = response.body();
                basePresenter.onDataLoaded(news);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(context, "Failed fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

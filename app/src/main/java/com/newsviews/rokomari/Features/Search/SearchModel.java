package com.newsviews.rokomari.Features.Search;

import android.content.Context;
import android.widget.Toast;

import com.newsviews.rokomari.Api.MyApiEndpointInterface;
import com.newsviews.rokomari.BasePresenter.iBasePresenter;
import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.Model.NewsRequestModel;
import com.newsviews.rokomari.Model.SearchNumberModel;
import com.newsviews.rokomari.Model.SearchYearModel;
import com.newsviews.rokomari.Singleton.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchModel {

    private final iSearchPresenter<SearchNumberModel> iSearchPresenter;
    private final iSearchPresenter<SearchYearModel> iSearchPresenterYear;
    Context context;
    MyApiEndpointInterface myApiEndpointInterface;


    public SearchModel(Context context, iSearchPresenter<SearchNumberModel> iSearchPresenter, iSearchPresenter<SearchYearModel> iSearchPresenterYear) {

        this.iSearchPresenter = iSearchPresenter;
        this.iSearchPresenterYear = iSearchPresenterYear;
        this.context = context;
    }

    public void fetchMyNumberData(String number) {
        myApiEndpointInterface = ((MyApplication) this.context.getApplicationContext()).getApiServiceForSearch();


        Call<SearchNumberModel> call = myApiEndpointInterface.getSearchNumber(number);
        call.enqueue(new Callback<SearchNumberModel>() {
            @Override
            public void onResponse(Call<SearchNumberModel> call, Response<SearchNumberModel> response) {
                SearchNumberModel searchNumberModel = response.body();
                iSearchPresenter.onDataLoaded(searchNumberModel);
            }

            @Override
            public void onFailure(Call<SearchNumberModel> call, Throwable t) {
                Toast.makeText(context, "Failed fetch data", Toast.LENGTH_SHORT).show();

            }


        });
    }

    public void fetchMyYearData(String day, String month) {
        myApiEndpointInterface = ((MyApplication) this.context.getApplicationContext()).getApiServiceForSearch();


        Call<SearchYearModel> call = myApiEndpointInterface.getSearchDate(month, day);
        call.enqueue(new Callback<SearchYearModel>() {
            @Override
            public void onResponse(Call<SearchYearModel> call, Response<SearchYearModel> response) {
                SearchYearModel searchNumberModel = response.body();
                iSearchPresenterYear.onDataLoaded(searchNumberModel);
            }

            @Override
            public void onFailure(Call<SearchYearModel> call, Throwable t) {
                Toast.makeText(context, "Failed fetch data", Toast.LENGTH_SHORT).show();

            }


        });
    }
}

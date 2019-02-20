package com.newsviews.rokomari.Features.Search;

import android.content.Context;

import com.newsviews.rokomari.BasePresenter.iBasePresenter;
import com.newsviews.rokomari.BasePresenter.iBaseView;
import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.Model.SearchNumberModel;
import com.newsviews.rokomari.Model.SearchYearModel;

public class SearchPresenter {

    Context context;
    SearchModel searchModel;
    iSearchView<String> baseView;

    public SearchPresenter(Context context, iSearchView<String> baseView) {
        this.context = context;
        this.searchModel = new SearchModel(context, iSearchPresenter, iSearchPresenterYear);
        this.baseView = baseView;
    }

    public void fetchNumber(String number) {
        this.searchModel.fetchMyNumberData(number);
    }

    public void fetchDate(String month, String day) {
        this.searchModel.fetchMyYearData(day, month);
    }

    iSearchPresenter<SearchNumberModel> iSearchPresenter = myloadedData -> {
        this.baseView.onDataLoaded(myloadedData.getText());

    };
    iSearchPresenter<SearchYearModel> iSearchPresenterYear = myloadedData -> {
        this.baseView.onDataLoaded(myloadedData.getText());

    };
}

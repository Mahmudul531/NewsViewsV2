package com.newsviews.rokomari.Features.Home;

import android.content.Context;

import com.newsviews.rokomari.BasePresenter.iBasePresenter;
import com.newsviews.rokomari.BasePresenter.iBaseView;
import com.newsviews.rokomari.Model.NewsModel;

public class HomePresenter implements iBasePresenter<NewsModel> {

    Context context;
    HomeModel homeModel;
    iBaseView<NewsModel> baseView;

    public HomePresenter(Context context, iBaseView<NewsModel> baseView) {
        this.context = context;
        this.homeModel = new HomeModel(context, this);
        this.homeModel.fetchMyNewsData();
        this.baseView = baseView;
    }

    @Override
    public void onDataLoaded(NewsModel myloadedData) {
        baseView.onDataLoaded(myloadedData);

    }
}

package com.newsviews.rokomari.Features.Home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.newsviews.rokomari.Adapter.NewsViewAdapter;
import com.newsviews.rokomari.BasePresenter.iBaseView;
import com.newsviews.rokomari.Listener.onListItemClickListener;
import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements iBaseView<NewsModel>, onListItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.textViewLoad)
    TextView textViewLoad;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        View view = inflater.inflate(R.layout.home_fragment, parent, false);
        ButterKnife.bind(this, view);
        return view;
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        HomePresenter homePresenter = new HomePresenter(getActivity(), this);

    }

    @Override
    public void onDataLoaded(NewsModel ondataLoaded) {

        NewsViewAdapter newsViewAdapter = new NewsViewAdapter(ondataLoaded.getArticles(), getActivity(), this);
        recyclerView.setAdapter(newsViewAdapter);
        textViewLoad.setText("News Headline");

    }

    @Override
    public void onClickOfNewsLinkClick(String url, String extra) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("Open URl in Browser");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onWebViewOpen(url);
            }
        });
        alert.show();
    }

    public void onWebViewOpen(String url) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Title  ");

        WebView wv = new WebView(getActivity());
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
}

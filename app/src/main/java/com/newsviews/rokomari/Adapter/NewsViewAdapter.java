package com.newsviews.rokomari.Adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.newsviews.rokomari.Listener.onListItemClickListener;
import com.newsviews.rokomari.Model.NewsModel;
import com.newsviews.rokomari.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewAdapter extends RecyclerView.Adapter<NewsViewAdapter.ViewHolder> {


    private ArrayList<NewsModel.Articles> mDataset;
    Context context;
    onListItemClickListener onListClick;

    public NewsViewAdapter(ArrayList<NewsModel.Articles> mDataset, Context context, onListItemClickListener onListClick) {
        this.mDataset = mDataset;
        this.context = context;
        this.onListClick = onListClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_one, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItem(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private static final String TAG = "test";
        private NewsModel.Articles mItem;

        @BindView(R.id.textViewTitle)
        TextView textViewTitle;

        @BindView(R.id.textViewShortDesc)
        TextView textViewShortDesc;

        @BindView(R.id.txSource)
        TextView txSource;

        @BindView(R.id.txPublishTime)
        TextView txPublishTime;
        @BindView(R.id.textViewRating)
        TextView textViewRating;


        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            ButterKnife.bind(this, view);
        }

        public void setItem(NewsModel.Articles item) {
            mItem = item;
            textViewTitle.setText(item.getTitle());
            textViewShortDesc.setText(item.getDescription());
            txPublishTime.setText("Published: " + item.getPublishedat());
            textViewRating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsViewAdapter.this.onListClick.onClickOfNewsLinkClick(mItem.getUrl(), "Nothing");
                }
            });
            Glide
                    .with(NewsViewAdapter.this.context)
                    .load(item.getUrltoimage())
                    .centerCrop()
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick " + getPosition() + " " + mItem);
        }
    }

}
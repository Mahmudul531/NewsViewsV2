package com.newsviews.rokomari.Features.Search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newsviews.rokomari.R;
import com.newsviews.rokomari.Utility.MyDatePickerFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by user on 2/20/2019.
 */

public class SearchActivity extends AppCompatActivity implements iSearchView<String> {
    @BindView(R.id.tx_search_result)
    TextView tx_search_result;

    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.im_calender)
    ImageView im_calender;
    SearchPresenter searchPresenter;

    @OnClick(R.id.im_calender)
    public void onClickCalender() {
        MyDatePickerFragment newFragment = new MyDatePickerFragment();
        newFragment.setDatePickerListener(new DatePicked() {
            @Override
            public void onDatePicked(String day, String month) {
                tx_search_result.setText("Searching...");

                searchPresenter.fetchDate(month, day);

            }
        });
        newFragment.show(getSupportFragmentManager(), "date picker");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchPresenter = new SearchPresenter(this, this);

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // do your stuff here
                    if (et_search.getText().toString().trim().length() > 0 ){
                        searchPresenter.fetchNumber(et_search.getText().toString());
                        tx_search_result.setText("Searching...");
                    }
                    else
                        Toast.makeText(SearchActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

    }

    @Override
    public void onDataLoaded(String ondataLoaded) {
        tx_search_result.setText(ondataLoaded);
    }


    public interface DatePicked {
        void onDatePicked(String day, String month);
    }
}

package com.newsviews.rokomari.Utility;

/**
 * Created by user on 2/20/2019.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import com.newsviews.rokomari.Features.Search.SearchActivity;

import java.util.Calendar;

import androidx.fragment.app.DialogFragment;

public class MyDatePickerFragment extends DialogFragment {

    private SearchActivity.DatePicked datePickerListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                 /*   Toast.makeText(getActivity(), "selected date is " + view.getYear() +
                            " / " + (view.getMonth() + 1) +
                            " / " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();
*/
                    datePickerListener.onDatePicked(Integer.toString(view.getDayOfMonth()), Integer.toString((view.getMonth() + 1)));
                }
            };

    public void setDatePickerListener(SearchActivity.DatePicked datePickerListener) {
        this.datePickerListener = datePickerListener;
    }
}

package com.tth.yelowbus_attendant.ui.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.tth.yelowbus_attendant.R;

import java.util.Date;

public class RequestHolidayFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvDate;
    private TextView tvYes, tvNo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_request_holiday, container, false);

        tvDate = view.findViewById(R.id.tvDate);
        tvYes = view.findViewById(R.id.tvYes);
        tvNo = view.findViewById(R.id.tvNo);


        tvDate.setOnClickListener(this);
        tvYes.setOnClickListener(this);
        tvNo.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvDate:
                showDatePicker(tvDate);
                break;

            case R.id.tvYes:
                tvYes.setBackground(getResources().getDrawable(R.drawable.sphere_green));
                tvYes.setTextColor(getResources().getColor(R.color.white));
                tvNo.setBackground(getResources().getDrawable(R.drawable.circle_transparent_grey_border));
                tvNo.setTextColor(getResources().getColor(R.color.black));
                break;

            case R.id.tvNo:
                tvNo.setBackground(getResources().getDrawable(R.drawable.sphere_red));
                tvNo.setTextColor(getResources().getColor(R.color.white));
                tvYes.setBackground(getResources().getDrawable(R.drawable.circle_transparent_grey_border));
                tvYes.setTextColor(getResources().getColor(R.color.black));
                break;
        }

    }


    public void showDatePicker(final TextView dateText){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog =  new DatePickerDialog(getActivity());
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMinDate(new Date().getTime());
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dateText.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            });
        }
        else {
            final DatePicker datePicker =new  DatePicker(getActivity());
            datePicker.setMinDate(new Date().getTime());
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            builder.setView(datePicker);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dateText.setText(datePicker.getDayOfMonth()+"/"+(datePicker.getMonth()+1)+"/"+datePicker.getYear());
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }
    }

}

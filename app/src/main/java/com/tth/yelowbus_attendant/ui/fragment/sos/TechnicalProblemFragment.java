package com.tth.yelowbus_attendant.ui.fragment.sos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.BaseFragment;


public class TechnicalProblemFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_technical_problem, container, false);
    }


    @Override
    public void onClick(View v) {

    }
}

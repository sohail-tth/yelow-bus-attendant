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


public class ChildNotFeelingWell extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_not_feeling_well, container, false);

        return view;
    }


}

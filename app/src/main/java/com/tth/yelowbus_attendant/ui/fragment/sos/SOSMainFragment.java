package com.tth.yelowbus_attendant.ui.fragment.sos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.BaseFragment;


public class SOSMainFragment extends BaseFragment implements View.OnClickListener {

    private AppCompatTextView tvTechnicalProblem, tvAccident, tvChildNotWell, tvPuncture, tvFuelEmpty, tvCallExecutive;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sosmain, container, false);

        tvTechnicalProblem = view.findViewById(R.id.tvTechnicalProblem);
        tvAccident = view.findViewById(R.id.tvAccident);
        tvChildNotWell = view.findViewById(R.id.tvChildNotWell);
        tvPuncture = view.findViewById(R.id.tvPuncture);
        tvFuelEmpty = view.findViewById(R.id.tvFuelEmpty);
        tvCallExecutive = view.findViewById(R.id.tvCallExecutive);

        tvTechnicalProblem.setOnClickListener(this);
        tvAccident.setOnClickListener(this);
        tvChildNotWell.setOnClickListener(this);
        tvPuncture.setOnClickListener(this);
        tvFuelEmpty.setOnClickListener(this);
        tvCallExecutive.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTechnicalProblem:
            case R.id.tvPuncture:
            case R.id.tvFuelEmpty:
                setFragment((AppCompatActivity)getActivity(),new TechnicalProblemFragment(),R.id.container,TechnicalProblemFragment.class.getSimpleName(),true);
                break;
            case R.id.tvAccident:
                setFragment((AppCompatActivity)getActivity(),new AccidentFragment(),R.id.container,AccidentFragment.class.getSimpleName(),true);
                break;
            case R.id.tvChildNotWell:
                setFragment((AppCompatActivity)getActivity(),new ChildNotFeelingWell(),R.id.container,ChildNotFeelingWell.class.getSimpleName(),true);
                break;

        }
    }
}

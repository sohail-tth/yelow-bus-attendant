package com.tth.yelowbus_attendant.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tth.yelowbus_attendant.R;


public class HelpFragment extends BaseFragment implements View.OnClickListener {

    private ImageView ivBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_help, container, false);

        ivBack = view.findViewById(R.id.ivBack);

        ivBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;
        }
    }
}

package com.tth.yelowbus_attendant.ui.fragment.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.BaseFragment;

public class SettingsFragment extends BaseFragment implements View.OnClickListener {

    private View layoutPerson;
    private ImageView ivBack;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        layoutPerson = view.findViewById(R.id.layoutPerson);
        ivBack = view.findViewById(R.id.ivBack);

        layoutPerson.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layoutPerson:
                setFragment((AppCompatActivity)getActivity(), new UpdateProfileFragment(), R.id.container, UpdateProfileFragment.class.getSimpleName(), true);
                break;

            case R.id.ivBack:
                getActivity().onBackPressed();
                break;
        }
    }
}

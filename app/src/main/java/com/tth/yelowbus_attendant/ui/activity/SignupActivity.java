package com.tth.yelowbus_attendant.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.SignupFragment;
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

public class SignupActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_container);

        setFragment(new SignupFragment(),R.id.signupContainer,SignupFragment.class.getSimpleName(),false);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }


}

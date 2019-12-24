package com.tth.yelowbus_attendant.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.MainFragment;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setFragment(new MainFragment(),R.id.container, MainFragment.class.getSimpleName(), true);
    }
}

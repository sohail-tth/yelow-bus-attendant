package com.tth.yelowbus_attendant.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.OnboardingPagerAdapter;
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

public class SplashActivity extends BaseActivity implements View.OnClickListener {


    private View layoutOnBoarding;
    private ImageView ivSplash;
    private ViewPager viewPager;
    private OnboardingPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private TextView tvProceed;
    private String userProfileType = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ivSplash.setVisibility(View.GONE);
                layoutOnBoarding.setVisibility(View.VISIBLE);
            }
        }, 3000);
    }

    private void init() {
        layoutOnBoarding = findViewById(R.id.layoutOnBoarding);
        ivSplash = findViewById(R.id.ivSplash);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tvProceed = findViewById(R.id.tvProceed);
        pagerAdapter = new OnboardingPagerAdapter(getApplicationContext(), getSupportFragmentManager(), 4);

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ivSplash.setVisibility(View.VISIBLE);
        layoutOnBoarding.setVisibility(View.GONE);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 8, 0);
            tab.requestLayout();
        }

        tvProceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvProceed:
                startActivity(new Intent(SplashActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                break;

        }
    }


}

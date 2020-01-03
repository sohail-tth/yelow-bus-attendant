package com.tth.yelowbus_attendant.adapter;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.tth.yelowbus_attendant.ui.fragment.onBoarding.OnBoarding1;
import com.tth.yelowbus_attendant.ui.fragment.onBoarding.OnBoarding2;
import com.tth.yelowbus_attendant.ui.fragment.onBoarding.OnBoarding3;
import com.tth.yelowbus_attendant.ui.fragment.onBoarding.OnBoarding4;
public class OnboardingPagerAdapter  extends FragmentStatePagerAdapter {
    private Context context;
    private int totalTabs;
    public OnboardingPagerAdapter(Context context , @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new OnBoarding1();
            case 1: return new OnBoarding2();
            case 2: return new OnBoarding3();
            case 3: return new OnBoarding4();
            default: return  null;
        }
    }
    @Override
    public int getCount() { return totalTabs; }
}

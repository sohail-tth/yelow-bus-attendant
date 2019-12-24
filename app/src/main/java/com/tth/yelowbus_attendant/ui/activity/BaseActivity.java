package com.tth.yelowbus_attendant.ui.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void setFragment(Fragment fragment, int container , String tag, boolean addToBackStack){
        if (addToBackStack){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, tag)
                    .commit();
        }
    }

    public void toast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }




}

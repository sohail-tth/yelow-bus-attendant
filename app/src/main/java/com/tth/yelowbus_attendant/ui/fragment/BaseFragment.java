package com.tth.yelowbus_attendant.ui.fragment;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class BaseFragment extends Fragment {


    public void setFragment(AppCompatActivity activity, Fragment fragment, int container , String tag, boolean addToBackStack){
        if (addToBackStack){
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, tag)
                    .addToBackStack(null)
                    .commit();
        }
        else {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, tag)
                    .commit();
        }
    }

    public void toast(Activity activity, String msg){
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }


    public void snackBar(View view, String msg){
        Snackbar.make(view, msg,Snackbar.LENGTH_LONG).show();
    }


}

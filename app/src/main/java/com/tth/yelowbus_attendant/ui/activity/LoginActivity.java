package com.tth.yelowbus_attendant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout btnSignUp, btnLogin ;
    private String userProfileType = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:
                showSignupOption(R.style.DialogAnimation_2);
                break;
            case R.id.btnLogin:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                break;
        }
    }

    public void showSignupOption(final int animationSource) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_signup_type, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge2);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();

        final TextView tvDriveWithYelow = view.findViewById(R.id.tvDriveWithYelow);
        final TextView tvLeaseVehicle = view.findViewById(R.id.tvLeaseVehicle);
        final TextView tvAttachFleet = view.findViewById(R.id.tvAttachFleet);
        final TextView tvSignUp = view.findViewById(R.id.tvSignUp);

        tvDriveWithYelow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfileType = Constants.DRIVE_WITH_YELOW;
                tvDriveWithYelow.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
                tvLeaseVehicle.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvAttachFleet.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvSignUp.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
            }
        });
        tvLeaseVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfileType  = Constants.LEASE_VEHICLE;
                tvLeaseVehicle.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
                tvDriveWithYelow.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvAttachFleet.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvSignUp.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
            }
        });
        tvAttachFleet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userProfileType = Constants.FLEET;
                tvAttachFleet.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
                tvDriveWithYelow.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvLeaseVehicle.setBackground(getResources().getDrawable(R.drawable.rounded_corner_transparent_grey_border));
                tvSignUp.setBackground(getResources().getDrawable(R.drawable.rounded_corner_yellow_filled));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userProfileType.equals("")){
                    toast(LoginActivity.this,"Please choose the profile type first");
                }
                else
                {
                    dialog.dismiss();
                    showEmailMobileSignUp();
                }
            }
        });
    }

    public void showEmailMobileSignUp() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_email_mobile_signup, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge2);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView tvSubmit = view.findViewById(R.id.tvSubmit);

        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showOTPSignup();
            }
        });
    }

    public void showOTPSignup() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_verify_otp_signup, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge2);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView tvSubmitOtp = view.findViewById(R.id.tvSubmitOtp);

        tvSubmitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Preference preference = Preference.getInstance(getApplicationContext());
                preference.setUserProfileType(userProfileType);

                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}

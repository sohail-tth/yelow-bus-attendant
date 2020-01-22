package com.tth.yelowbus_attendant.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.JsonObject;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.api.APICaller;
import com.tth.yelowbus_attendant.api.APIClient;
import com.tth.yelowbus_attendant.api.APIInterface;
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout btnSignUp, btnLogin ;
    private String userProfileType = "";
    private EditText edtMobile, edtPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        edtMobile = findViewById(R.id.edtMobile);
        edtPassword = findViewById(R.id.edtPassword);

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
                if (isLoginValid()){
                    login();
                }
                break;
        }
    }

    private boolean isLoginValid(){
        if (edtMobile.getText().toString().isEmpty() || edtMobile.getText().toString().length() < 10){
            snackBar(btnLogin, getResources().getString(R.string.err_mobile));
            return false;
        }
        if (edtPassword.getText().toString().isEmpty()){
            snackBar(btnLogin, getResources().getString(R.string.err_password));
            return false;
        }

        return true;
    }

    private void login(){
        JsonObject request  = new JsonObject();
        request.addProperty("mobileNumber", edtMobile.getText().toString());
        request.addProperty("password", edtPassword.getText().toString());

        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        APICaller apiCaller = new APICaller();
        Call<JsonObject> call = apiInterface.login(request);
        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    if (jsonObject.get("emailVerified").getAsBoolean()){
                        if (jsonObject.get("approvedByAdmin").getAsBoolean()){
                            Preference.getInstance(getApplicationContext()).setIS_LOGGED_IN(true);
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            finish();
                        }
                        else {
                            showUnderVerificationDialog(R.style.DialogAnimation_2);
                        }
                    }
                    else {
                        showEmailVerificationDialog(R.style.DialogAnimation_2);
                    }
                }
                else{
                    snackBar(btnLogin, jsonObject.get("message").getAsString());
                }
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(btnLogin, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(btnLogin, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(btnLogin, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(LoginActivity.this, call, "Please wait");


    }

    private void showUnderVerificationDialog(final int animationSource) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_msg_acc_under_verification, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
    }

    public void showEmailVerificationDialog(final int animationSource){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge);
        View view  = LayoutInflater.from(getApplicationContext()).inflate(R.layout.account_create_dialog_layout, null ,false);
        builder.setView(view);
        final Dialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;
        dialog.show();
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

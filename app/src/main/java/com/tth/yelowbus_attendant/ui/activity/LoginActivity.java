package com.tth.yelowbus_attendant.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.tth.yelowbus_attendant.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    LinearLayout btnSignUp, btnLogin ;

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
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            break;
            case R.id.btnLogin:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                break;


        }
    }


}

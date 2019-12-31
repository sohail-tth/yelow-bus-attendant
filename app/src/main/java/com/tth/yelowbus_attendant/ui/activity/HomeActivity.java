package com.tth.yelowbus_attendant.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.MainFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends BaseActivity implements MainFragment.QRButtonClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MainFragment mainFragment = new MainFragment();
        mainFragment.setQrButtonClickListener(this);

        setFragment( mainFragment,R.id.container, MainFragment.class.getSimpleName(), false);
    }

    @Override
    public void onQRClick() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCaptureActivity(QRScannerActivity.class);
        intentIntegrator.setPrompt("Scan QRCode");
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(HomeActivity.this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    showQRValueDialog(result.getContents());
                    Toast.makeText(HomeActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                    showQRValueDialog(result.getContents());
                    Toast.makeText(HomeActivity.this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



    public void showQRValueDialog(String value){
        new AlertDialog.Builder(HomeActivity.this)
                .setTitle("Success")
                .setMessage(value)
        .setCancelable(true)
        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();

    }
}

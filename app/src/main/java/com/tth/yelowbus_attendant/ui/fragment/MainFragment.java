package com.tth.yelowbus_attendant.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.StoppageAdapter;
import com.tth.yelowbus_attendant.model.StoppageModel;
import com.tth.yelowbus_attendant.ui.activity.QRScannerActivity;
import com.tth.yelowbus_attendant.ui.fragment.settings.SettingsFragment;
import com.tth.yelowbus_attendant.ui.fragment.sos.SOSMainFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment implements View.OnClickListener, OnMapReadyCallback {

    private final int RC_CAMERA = 102;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View fabMenu;
    private QRButtonClickListener qrButtonClickListener;

    private RecyclerView recyclerStoppage;
    private StoppageAdapter adapter;
    private List<StoppageModel> stoppageList = new ArrayList<>();
    private GoogleMap map;
    private ImageView ivScanQR;
    private View navRequestHoliday, navCalendar, navNotification, navMyWallet, navReferEarn, navRouteMaps, navSettings, navHelp, navLogout
            ,btnSOS, btnMissedPickup, btnFeedback, btnCall, btnManualScan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_drawer_home, container, false);
        recyclerStoppage = view.findViewById(R.id.recyclerStoppage);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = view.findViewById(R.id.navigationView);
        fabMenu = view.findViewById(R.id.fabMenu);
        ivScanQR = view.findViewById(R.id.ivScanQR);
        navRequestHoliday = view.findViewById(R.id.navRequestHoliday);
        navCalendar = view.findViewById(R.id.navCalendar);
        navNotification = view.findViewById(R.id.navNotification);
        navMyWallet = view.findViewById(R.id.navMyWallet);
        navReferEarn = view.findViewById(R.id.navReferEarn);
        navRouteMaps = view.findViewById(R.id.navRouteMaps);
        navSettings = view.findViewById(R.id.navSettings);
        navHelp = view.findViewById(R.id.navHelp);
        navLogout = view.findViewById(R.id.navLogout);
        btnSOS = view.findViewById(R.id.btnSOS);
        btnMissedPickup = view.findViewById(R.id.btnMissedPickup);
        btnFeedback = view.findViewById(R.id.btnFeedback);
        btnCall = view.findViewById(R.id.btnCall);
        btnManualScan = view.findViewById(R.id.btnManualScan);

        adapter = new StoppageAdapter(getContext(), stoppageList);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        recyclerStoppage.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerStoppage.setLayoutManager(linearLayoutManager);

        fabMenu.setOnClickListener(this);
        ivScanQR.setOnClickListener(this);
        navRequestHoliday.setOnClickListener(this);
        navCalendar.setOnClickListener(this);
        navNotification.setOnClickListener(this);
        navMyWallet.setOnClickListener(this);
        navReferEarn.setOnClickListener(this);
        navRouteMaps.setOnClickListener(this);
        navSettings.setOnClickListener(this);
        navHelp.setOnClickListener(this);
        navLogout.setOnClickListener(this);
        btnSOS.setOnClickListener(this);
        btnMissedPickup.setOnClickListener(this);
        btnFeedback.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnManualScan.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stoppageList.clear();
        for (int i=0; i< 13; i++){
            StoppageModel model = new StoppageModel(i,"Bela Nivas",false,false,false,"6:40 AM");
            stoppageList.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabMenu:
                if (!drawerLayout.isDrawerOpen(GravityCompat.END))
                    drawerLayout.openDrawer(GravityCompat.END);
                break;

            case R.id.ivScanQR:
                if (hasCameraPermission()){

                    if (qrButtonClickListener != null)
                        qrButtonClickListener.onQRClick();

//                    setFragment((AppCompatActivity) getActivity(), QRScanFragment.newInstance(true,false), R.id.container, QRScanFragment.class.getSimpleName(), true);

                }
                else requestCameraPermission();

                break;

            case R.id.navRequestHoliday:
                drawerLayout.closeDrawer(GravityCompat.END);
                setFragment((AppCompatActivity) getActivity(), new RequestHolidayFragment(), R.id.container, RequestHolidayFragment.class.getSimpleName(), true);
                break;

            case R.id.navSettings:
                drawerLayout.closeDrawer(GravityCompat.END);
                setFragment((AppCompatActivity) getActivity(), new SettingsFragment(), R.id.container, SettingsFragment.class.getSimpleName(), true);
                break;

            case R.id.navCalendar:
                drawerLayout.closeDrawer(GravityCompat.END);
                setFragment((AppCompatActivity) getActivity(), new CalendarFragment(), R.id.container, CalendarFragment.class.getSimpleName(), true);
                break;

            case R.id.navReferEarn:
                drawerLayout.closeDrawer(GravityCompat.END);
                setFragment((AppCompatActivity) getActivity(), new ReferEarnFragment(), R.id.container, ReferEarnFragment.class.getSimpleName(), true);

                break;

            case R.id.btnSOS:
                setFragment((AppCompatActivity) getActivity(), new SOSMainFragment(), R.id.container, SOSMainFragment.class.getSimpleName(), true);
                break;

            case R.id.btnMissedPickup:
                setFragment((AppCompatActivity) getActivity(), new MissedPickupFragment(), R.id.container, MissedPickupFragment.class.getSimpleName(), true);
                break;

            case R.id.btnCall:
                setFragment((AppCompatActivity) getActivity(), new HelpFragment(), R.id.container, HelpFragment.class.getSimpleName(), true);
                break;

            case R.id.btnManualScan:
                showManualScan();
                break;
        }

    }

    public boolean hasCameraPermission(){
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
            return true;
        else return false;
    }

    public void requestCameraPermission(){
        requestPermissions(new String[]{Manifest.permission.CAMERA}, RC_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!hasCameraPermission()){
            requestCameraPermission();
        }
        else {

        }
    }


    public void showManualScan(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_manual_scan,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setView(view);

        final Dialog dialog = builder.create();
        dialog.show();

        AppCompatTextView tvCancel = view.findViewById(R.id.tvCancel);
        AppCompatTextView tvConfirm = view.findViewById(R.id.tvConfirm);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMapToolbarEnabled(false);

    }



    public void setQrButtonClickListener(QRButtonClickListener qrButtonClickListener){
        this.qrButtonClickListener = qrButtonClickListener;
    }

    public interface QRButtonClickListener{
        void onQRClick();
    }
}

package com.tth.yelowbus_attendant.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.StoppageAdapter;
import com.tth.yelowbus_attendant.model.StoppageModel;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends BaseFragment implements View.OnClickListener, OnMapReadyCallback {

    private final int RC_CAMERA = 102;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fabMenu;

    private RecyclerView recyclerStoppage;
    private StoppageAdapter adapter;
    private List<StoppageModel> stoppageList = new ArrayList<>();
    private GoogleMap map;
    private ImageView ivScanQR;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.layout_drawer_home, container, false);
        recyclerStoppage = view.findViewById(R.id.recyclerStoppage);
        drawerLayout = view.findViewById(R.id.drawerLayout);
        navigationView = view.findViewById(R.id.navigationView);
        fabMenu = view.findViewById(R.id.fabMenu);
        ivScanQR = view.findViewById(R.id.ivScanQR);

        adapter = new StoppageAdapter(getContext(), stoppageList);
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

        recyclerStoppage.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerStoppage.setLayoutManager(linearLayoutManager);

        fabMenu.setOnClickListener(this);
        ivScanQR.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                    setFragment((AppCompatActivity) getActivity(), QRScanFragment.newInstance(true,false), R.id.container, QRScanFragment.class.getSimpleName(), true);

                }
                else requestCameraPermission();

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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMapToolbarEnabled(false);

    }
}

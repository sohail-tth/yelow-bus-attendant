package com.tth.yelowbus_attendant.ui.fragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.StoppageAdapter;
import com.tth.yelowbus_attendant.model.StoppageModel;
import com.tth.yelowbus_attendant.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class RouteDetailsSelectionFragment extends BaseFragment implements View.OnClickListener {

    private View  tabPickup, tabDrop, layoutPickup, layoutDrop;
    private AppCompatTextView tvSelectPickupRoute, tvSelectedPickupRoute, tvPickupStartTime, tvDropStartTime,
                    tvPickupConfirm, tvDropConfirm;
    private RecyclerView recyclerPickupStoppages,recyclerDropStoppages ;
    private ImageView pickupIndicator, dropIndicator;
    private StoppageAdapter pickupStoppageAdapter, dropStoppageAddpter;
    private List<StoppageModel> pickupStoppageList = new ArrayList<>();
    private List<StoppageModel> dropStoppageList = new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_route_details_selection, container, false);

        tabPickup = view.findViewById(R.id.tabPickup);
        tabDrop = view.findViewById(R.id.tabDrop);
        layoutPickup = view.findViewById(R.id.layoutPickup);
        layoutDrop = view.findViewById(R.id.layoutDrop);
        tvSelectedPickupRoute = view.findViewById(R.id.tvSelectedPickupRoute);
        tvSelectPickupRoute = view.findViewById(R.id.tvSelectPickupRoute);
        tvPickupStartTime = view.findViewById(R.id.tvPickupStartTime);
        tvDropStartTime = view.findViewById(R.id.tvDropStartTime);
        tvPickupConfirm = view.findViewById(R.id.tvPickupConfirm);
        tvDropConfirm = view.findViewById(R.id.tvDropConfirm);
        recyclerPickupStoppages = view.findViewById(R.id.recyclerPickupStoppages);
        recyclerDropStoppages = view.findViewById(R.id.recyclerDropStoppages);
        pickupIndicator = view.findViewById(R.id.pickupIndicator);
        dropIndicator = view.findViewById(R.id.dropIndicator);

        pickupStoppageAdapter = new StoppageAdapter(getContext(), pickupStoppageList);
        recyclerPickupStoppages.setAdapter(pickupStoppageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerPickupStoppages.setLayoutManager(linearLayoutManager);

        dropStoppageAddpter = new StoppageAdapter(getContext(), dropStoppageList);
        recyclerDropStoppages.setAdapter(dropStoppageAddpter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerDropStoppages.setLayoutManager(linearLayoutManager2);

        tabPickup.setOnClickListener(this);
        tabDrop.setOnClickListener(this);
        tvSelectPickupRoute.setOnClickListener(this);
        tvPickupStartTime.setOnClickListener(this);
        tvPickupConfirm.setOnClickListener(this);
        tvDropConfirm.setOnClickListener(this);
        tvDropStartTime.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabPickup.performClick();

        pickupStoppageList.clear();
        for (int i=0; i< 13; i++){
            StoppageModel model = new StoppageModel(i,"Bharat Petroleum, Trombay",false,false,false,"6:40 AM");
            pickupStoppageList.add(model);
        }
        pickupStoppageAdapter.notifyDataSetChanged();

        dropStoppageList.clear();
        for (int i=0; i< 10; i++){
            StoppageModel model = new StoppageModel(i,"Mazgaon Dock",false,false,false,"10:00 AM");
            dropStoppageList.add(model);
        }
        dropStoppageAddpter.notifyDataSetChanged();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tabPickup:
                layoutPickup.setVisibility(View.VISIBLE);
                layoutDrop.setVisibility(View.GONE);
                pickupIndicator.setVisibility(View.VISIBLE);
                dropIndicator.setVisibility(View.INVISIBLE);
                break;

            case R.id.tabDrop:
            case R.id.tvPickupConfirm:
                layoutPickup.setVisibility(View.GONE);
                layoutDrop.setVisibility(View.VISIBLE);
                pickupIndicator.setVisibility(View.INVISIBLE);
                dropIndicator.setVisibility(View.VISIBLE);
                break;

            case R.id.tvPickupStartTime:
                showTimeDialog(tvPickupStartTime);
                break;

            case R.id.tvDropStartTime:
                showTimeDialog(tvDropStartTime);
                break;

            case R.id.tvDropConfirm:
                startActivity(new Intent(getActivity(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                getActivity().finish();

                break;
        }
    }


    public void showTimeDialog(final AppCompatTextView textView){
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText(hourOfDay+" : "+minute);
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);

        timePickerDialog.show();
    }
}

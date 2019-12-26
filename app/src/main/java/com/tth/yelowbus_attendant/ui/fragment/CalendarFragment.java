package com.tth.yelowbus_attendant.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.EventAdapter;
import com.tth.yelowbus_attendant.model.EventModel;
import com.tth.yelowbus_attendant.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalendarFragment extends BaseFragment implements View.OnClickListener {

    CalendarView calendarView;
    RecyclerView eventRecycler;
    List<EventModel> list = new ArrayList<>();
    EventAdapter adapter;
    ImageView ivBack;
    private TextView tvDay, tvDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        eventRecycler = view.findViewById(R.id.eventRecycler);
        ivBack = view.findViewById(R.id.ivBack);
        tvDay = view.findViewById(R.id.tvDay);
        tvDate = view.findViewById(R.id.tvDate);
        eventRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EventAdapter(getContext(), list, new EventAdapter.EventClickListener() {
            @Override
            public void onEventClick(EventModel tripModel) {

            }
        });

        eventRecycler.setAdapter(adapter);

        ivBack.setOnClickListener(this);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Date today = new Date();
        tvDay.setText(Util.getDayOfWeek(today));
        tvDate.setText(Util.getShortMonthName(today)+" "+Util.getOrdinalNumber(today.getDate()));
        getEvents();
    }

    private void getEvents() {
        EventModel event;
        for (int i=0; i< 7; i++){
            event = new EventModel();
            event.setId(i);
            event.setTitle("Daily pickup and drop schedule");
            event.setSubTitle("At home");
            event.setDate("June 14");

            list.add(event);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivBack:
                getActivity().onBackPressed();
                break;
        }
    }
}

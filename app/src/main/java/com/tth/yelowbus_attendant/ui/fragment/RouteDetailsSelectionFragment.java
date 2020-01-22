package com.tth.yelowbus_attendant.ui.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.adapter.StoppageAdapter;
import com.tth.yelowbus_attendant.api.APICaller;
import com.tth.yelowbus_attendant.api.APIClient;
import com.tth.yelowbus_attendant.api.APIInterface;
import com.tth.yelowbus_attendant.model.RouteModel;
import com.tth.yelowbus_attendant.model.StoppageModel;
import com.tth.yelowbus_attendant.ui.activity.HomeActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class RouteDetailsSelectionFragment extends BaseFragment implements View.OnClickListener {

    private View  tabPickup, tabDrop, layoutPickup, layoutDrop;
    private AppCompatTextView tvSelectPickupRoute, tvSelectedPickupRoute, tvSelectDropRoute, tvSelectedDropRoute, tvPickupStartTime, tvDropStartTime,
                    tvPickupConfirm, tvDropConfirm;
    private RecyclerView recyclerPickupStoppages,recyclerDropStoppages ;
    private ImageView pickupIndicator, dropIndicator;
    private StoppageAdapter pickupStoppageAdapter, dropStoppageAddpter;
    private List<StoppageModel> pickupStoppageList = new ArrayList<>();
    private List<StoppageModel> dropStoppageList = new ArrayList<>();

    private List<RouteModel> pickupRouteList = new ArrayList<>();
    private String[] pickupRouteNames;
    private int selectedPickupRouteId = -1;

    private List<RouteModel> dropRouteList = new ArrayList<>();
    private String[] dropRouteNames;
    private int selectedDropRouteId = -1;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_route_details_selection, container, false);

        tabPickup = view.findViewById(R.id.tabPickup);
        tabDrop = view.findViewById(R.id.tabDrop);
        layoutPickup = view.findViewById(R.id.layoutPickup);
        layoutDrop = view.findViewById(R.id.layoutDrop);
        tvSelectedPickupRoute = view.findViewById(R.id.tvSelectedPickupRoute);
        tvSelectPickupRoute = view.findViewById(R.id.tvSelectPickupRoute);
        tvSelectDropRoute = view.findViewById(R.id.tvSelectDropRoute);
        tvSelectedDropRoute = view.findViewById(R.id.tvSelectedDropRoute);
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
        tvSelectDropRoute.setOnClickListener(this);
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


        getPickupRoutes();

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

            case R.id.tvSelectPickupRoute:
                if (pickupRouteList.size()>0)
                    showPickupRoutes();
                else
                    snackBar(tvPickupConfirm, "No route");
                break;

            case R.id.tvSelectDropRoute:
                if (dropRouteList.size() > 0)
                    showDropRoutes();
                else
                    snackBar(tvPickupConfirm, "No route");
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

    private void getPickupRoutes(){
        JsonObject request = new JsonObject();
        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JsonObject> call = apiInterface.getAllPickupRoutes(request);
        APICaller apiCaller = new APICaller();
        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    JsonArray routeArray = jsonObject.get("pickUpRouteData").getAsJsonArray();
                    pickupRouteNames = new String[routeArray.size()];
                    for (int i=0; i<routeArray.size(); i++){
                        JsonObject object = routeArray.get(i).getAsJsonObject();
                        int id = object.get("pickupRouteID").getAsInt();
                        String name = object.get("pickupRouteName").getAsString();
                        String startTime = object.get("pickupRouteStartTime").getAsString();
                        String endTime = object.get("pickupRouteEndTime").getAsString();
                        RouteModel model = new RouteModel(id, name, startTime, endTime);

                        pickupRouteList.add(model);
                        pickupRouteNames[i] = name;
                    }
                }

                getDropRoutes();
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(tvPickupConfirm, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(tvPickupConfirm, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(tvPickupConfirm, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(), call, "Getting routes");
    }

    private void showPickupRoutes(){
        ListView listView = new ListView(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, pickupRouteNames);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(listView);
        final Dialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                selectedPickupRouteId = pickupRouteList.get(position).getId();
                tvSelectedPickupRoute.setText(pickupRouteList.get(position).getName());
            }
        });

    }

    private void getDropRoutes(){
        JsonObject request = new JsonObject();
        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JsonObject> call = apiInterface.getAllDropRoutes(request);
        APICaller apiCaller = new APICaller();
        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    JsonArray routeArray = jsonObject.get("dropRouteData").getAsJsonArray();
                    dropRouteNames = new String[routeArray.size()];
                    for (int i=0; i<routeArray.size(); i++){
                        JsonObject object = routeArray.get(i).getAsJsonObject();
                        int id = object.get("dropRouteID").getAsInt();
                        String name = object.get("dropRouteName").getAsString();
                        String startTime = object.get("dropRouteStartTime").getAsString();
                        String endTime = object.get("dropRouteEndTime").getAsString();
                        RouteModel model = new RouteModel(id, name, startTime, endTime);

                        dropRouteList.add(model);
                        dropRouteNames[i] = name;
                    }
                }
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(tvPickupConfirm, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(tvPickupConfirm, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(tvPickupConfirm, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(), call, "Getting routes");

    }

    private void showDropRoutes(){
        ListView listView = new ListView(getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, dropRouteNames);
        listView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(listView);
        final Dialog dialog = builder.create();
        dialog.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                selectedDropRouteId = dropRouteList.get(position).getId();
                tvSelectedDropRoute.setText(pickupRouteList.get(position).getName());
            }
        });

    }

}

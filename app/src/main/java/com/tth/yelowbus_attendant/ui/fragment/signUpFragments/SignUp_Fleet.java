package com.tth.yelowbus_attendant.ui.fragment.signUpFragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.api.APICaller;
import com.tth.yelowbus_attendant.api.APIClient;
import com.tth.yelowbus_attendant.api.APIInterface;
import com.tth.yelowbus_attendant.model.SchoolModel;
import com.tth.yelowbus_attendant.ui.fragment.BaseFragment;
import com.tth.yelowbus_attendant.ui.fragment.PhotoUploadFragment;
import com.tth.yelowbus_attendant.ui.fragment.RouteDetailsSelectionFragment;
import com.tth.yelowbus_attendant.util.Preference;
import com.tth.yelowbus_attendant.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignUp_Fleet extends BaseFragment implements View.OnClickListener {

    private View tabFleetInfo, tabBusDriver, fleetIndicator, busDriverIndicator, layoutTerms, next, layout_fleet_owners_info, layout_bus_and_driver_info ;
    private EditText edtOwnerEmail, edtOwnerName, edtFleetName, edtFleetPassword, edtFleetConfPassword, edtFleetAddress1, edtFleetAddress2,
            edtFleetCity, edtFleetPincode, edtFleetState, edtFleetMobileNo, edtFleetBankAccount, edtFleetOwnersAdharNo,
            edtFleetDriversName, edtFleetDriversMobileNo, edtFleetDriversLicenseNo,edtFleetAttendantsName, edtFleetAttendantsMobileNo,
            edtFleetBusNo, edtFleetNoOfSets, edtFleetChassisNo;

    private TextView txtFleetOwnerBirthDate, tvFleetOwnersCancelledCheque, tvFleetOwnersAadharPhoto, tvFleetOwnersPhoto,
            tvFleetDriversAadhaarPhoto, tvFleetDriverPhoto, tvFleetDriverLicensePhoto, tvFleetAttendantsAadhaarPhoto,
            tvFleetAttendantsPhoto, tvFleetBusPhoto, edtFleetSchoolZoneRange;

    AppCompatCheckBox cbTermsCond;

    public static final String FROM_FLEET_CANCELLED_CHEQUE = "fleet_cancelled_cheque";
    public static final String FROM_FLEET_OWNER_AADHAAR = "fleet_owner_aadhaar";
    public static final String FROM_FLEET_OWNER_PHOTO = "fleet_owner_photo";
    public static final String FROM_FLEET_DRIVER_AADHAAR = "fleet_driver_aadhaar";
    public static final String FROM_FLEET_DRIVER_PHOTO = "fleet_driver_photo";
    public static final String FROM_FLEET_LICENSE_PHOTO = "fleet_license_photo";
    public static final String FROM_FLEET_ATTENDANTS_AADHAAR = "fleet_attendants_aadhaar";
    public static final String FROM_FLEET_ATTENDANTS_PHOTO = "fleet_attendants_photo";
    public static final String FROM_FLEET_BUS_PHOTO = "fleet_bus_photo";

    public static String fleetOwnersCancelledChequePath="", fleetOwnersAadhaarPath="", fleetOwnersPhotoPath="",
            fleetDriversAadhaarPath="", fleetDriversPhotoPath="", fleetlicensePath="", fleetAttendantsAadhaarPath ="",
            fleetAttendantsPhotoPath="", fleetBusPhotoPath="";

    public static String cancelledChequePhotoServerUrl="", ownerAadhaarPhotoServerUrl="", ownerPhotoServerUrl="",
            driverAadhaarPhotoServerUrl="", driverPhotoServerUrl="", licensePhotoServerUrl="", attendantAadhaarPhotoServerUrl="",
            attendantPhotoServerUrl="", busPhotoServerUrl="";

    String[] schoolNames;
    final List<SchoolModel> schoolModels = new ArrayList<>();
    public static String schoolNameVal = "";
    public static int schoolIdVal = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up__fleet, container, false);

        tabFleetInfo = view.findViewById(R.id.tabFleetInfo);
        tabBusDriver = view.findViewById(R.id.tabBusDriver);
        fleetIndicator = view.findViewById(R.id.fleetIndicator);
        busDriverIndicator = view.findViewById(R.id.busDriverIndicator);
        layoutTerms = view.findViewById(R.id.layoutTerms);
        next = view.findViewById(R.id.next);
        edtOwnerEmail = view.findViewById(R.id.edtOwnerEmail);
        edtOwnerName = view.findViewById(R.id.edtOwnerName);
        edtFleetName = view.findViewById(R.id.edtFleetName);
        edtFleetPassword = view.findViewById(R.id.edtFleetPassword);
        edtFleetConfPassword = view.findViewById(R.id.edtFleetConfPassword);
        edtFleetAddress1 = view.findViewById(R.id.edtFleetAddress1);
        edtFleetAddress2 = view.findViewById(R.id.edtFleetAddress2);
        edtFleetCity = view.findViewById(R.id.edtFleetCity);
        edtFleetPincode = view.findViewById(R.id.edtFleetPincode);
        edtFleetState = view.findViewById(R.id.edtFleetState);
        edtFleetMobileNo = view.findViewById(R.id.edtFleetMobileNo);
        edtFleetBankAccount = view.findViewById(R.id.edtFleetBankAccount);
        edtFleetOwnersAdharNo = view.findViewById(R.id.edtFleetOwnersAdharNo);
        edtFleetDriversName = view.findViewById(R.id.edtFleetDriversName);
        edtFleetDriversMobileNo = view.findViewById(R.id.edtFleetDriversMobileNo);
        edtFleetDriversLicenseNo = view.findViewById(R.id.edtFleetDriversLicenseNo);
        edtFleetAttendantsName = view.findViewById(R.id.edtFleetAttendantsName);
        edtFleetAttendantsMobileNo = view.findViewById(R.id.edtFleetAttendantsMobileNo);
        edtFleetBusNo = view.findViewById(R.id.edtFleetBusNo);
        edtFleetNoOfSets = view.findViewById(R.id.edtFleetNoOfSets);
        edtFleetChassisNo = view.findViewById(R.id.edtFleetChassisNo);
        edtFleetSchoolZoneRange = view.findViewById(R.id.edtFleetSchoolZoneRange);
        txtFleetOwnerBirthDate = view.findViewById(R.id.txtStudBirthDate);
        tvFleetOwnersCancelledCheque = view.findViewById(R.id.tvFleetOwnersCancelledCheque);
        tvFleetOwnersAadharPhoto = view.findViewById(R.id.tvFleetOwnersAadharPhoto);
        tvFleetOwnersPhoto = view.findViewById(R.id.tvFleetOwnersPhoto);
        tvFleetDriversAadhaarPhoto = view.findViewById(R.id.tvFleetDriversAadhaarPhoto);
        tvFleetDriverPhoto = view.findViewById(R.id.tvFleetDriverPhoto);
        tvFleetDriverLicensePhoto = view.findViewById(R.id.tvFleetDriverLicensePhoto);
        tvFleetAttendantsAadhaarPhoto = view.findViewById(R.id.tvFleetAttendantsAadhaarPhoto);
        tvFleetAttendantsPhoto = view.findViewById(R.id.tvFleetAttendantsPhoto);
        tvFleetBusPhoto = view.findViewById(R.id.tvFleetBusPhoto);
        cbTermsCond = view.findViewById(R.id.cbTermsCond);
        layout_fleet_owners_info = view.findViewById(R.id.layout_fleet_owners_info);
        layout_bus_and_driver_info = view.findViewById(R.id.layout_bus_and_driver_info);



        tabFleetInfo.setOnClickListener(this);
        tabBusDriver.setOnClickListener(this);
        layoutTerms.setOnClickListener(this);
        next.setOnClickListener(this);
        txtFleetOwnerBirthDate.setOnClickListener(this);
        tvFleetOwnersCancelledCheque.setOnClickListener(this);
        tvFleetOwnersAadharPhoto.setOnClickListener(this);
        tvFleetOwnersPhoto.setOnClickListener(this);
        tvFleetDriversAadhaarPhoto.setOnClickListener(this);
        tvFleetDriverPhoto.setOnClickListener(this);
        tvFleetDriverLicensePhoto.setOnClickListener(this);
        tvFleetAttendantsAadhaarPhoto.setOnClickListener(this);
        tvFleetAttendantsPhoto.setOnClickListener(this);
        tvFleetBusPhoto.setOnClickListener(this);
        edtFleetSchoolZoneRange.setOnClickListener(this);
        txtFleetOwnerBirthDate.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabFleetInfo.performClick();

        getSchoolList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tabFleetInfo:
                fleetIndicator.setVisibility(View.VISIBLE);
                busDriverIndicator.setVisibility(View.INVISIBLE);
                layout_fleet_owners_info.setVisibility(View.VISIBLE);
                layout_bus_and_driver_info.setVisibility(View.GONE);
                layoutTerms.setVisibility(View.GONE);
                break;

            case R.id.tabBusDriver:
                fleetIndicator.setVisibility(View.INVISIBLE);
                busDriverIndicator.setVisibility(View.VISIBLE);
                layout_fleet_owners_info.setVisibility(View.GONE);
                layout_bus_and_driver_info.setVisibility(View.VISIBLE);
                layoutTerms.setVisibility(View.VISIBLE);

                break;

            case R.id.next:
                if (layout_fleet_owners_info.getVisibility() == View.VISIBLE){
                    if (isOwnerValid()){
                        tabBusDriver.performClick();
                    }
                }
                else{
                    if (isOwnerValid()){
                        if (isDriverAttendantValid()){
                            buildConfirmationDialog(R.style.DialogAnimation_2);
                        }
                    }
                    else {
                        tabFleetInfo.performClick();
                    }
                }
                break;

            case R.id.tvFleetOwnersCancelledCheque:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetOwnersCancelledChequePath,"Cancelled Cheque","Upload Cancelled Cheque",FROM_FLEET_CANCELLED_CHEQUE),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetOwnersAadharPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetOwnersAadhaarPath,"Owner's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_FLEET_OWNER_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetOwnersPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetOwnersPhotoPath,"Owner's Photo","Upload Owner's Photo",FROM_FLEET_OWNER_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetDriversAadhaarPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetDriversAadhaarPath,"Driver's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_FLEET_DRIVER_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetDriverPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetDriversPhotoPath,"Driver's Photo","Upload Driver's Photo",FROM_FLEET_DRIVER_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetDriverLicensePhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetlicensePath,"License Photo","Upload License's Photo",FROM_FLEET_LICENSE_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetAttendantsAadhaarPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetAttendantsAadhaarPath,"Attendant's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_FLEET_ATTENDANTS_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetAttendantsPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetAttendantsPhotoPath,"Attendant's Photo","Upload Attendant's Photo",FROM_FLEET_ATTENDANTS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvFleetBusPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(fleetBusPhotoPath,"Bus Photo","Upload Bus Photo",FROM_FLEET_BUS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.edtFleetSchoolZoneRange:
                if (schoolModels.size() > 0)
                    showSchoolList();
                else
                    snackBar(next, "No school found");

                break;

            case R.id.txtStudBirthDate:
                showDatePicker();
                break;
        }

    }

    private void buildConfirmationDialog(final int animationSource) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_confirm_register, null, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.Theme_MaterialComponents_Light_NoActionBar_Bridge);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().getAttributes().windowAnimations = animationSource;

        dialog.show();

        Button btnYes = view.findViewById(R.id.btnYes);
        Button btnCheck = view.findViewById(R.id.btnCheck);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                addFleetRegister();
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }


    public void showDatePicker() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity());
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    txtFleetOwnerBirthDate.setText(Util.toDDMMYYYY(dayOfMonth, month, year));
                }
            });
        } else {
            final DatePicker datePicker = new DatePicker(getActivity());
            datePicker.setMaxDate(new Date().getTime());
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
            builder.setView(datePicker);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    txtFleetOwnerBirthDate.setText(Util.toDDMMYYYY(datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()));
                }
            });

            Dialog dialog = builder.create();
            dialog.show();
        }
    }

    public void getSchoolList(){

        final JsonObject request = new JsonObject();
        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JsonObject> call  = apiInterface.getSchools(request);
        APICaller apiCaller = new APICaller();
        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    JsonArray schoolArray = jsonObject.get("schoolData").getAsJsonArray();
                    schoolNames = new String[schoolArray.size()];
                    for (int i=0; i<schoolArray.size(); i++){
                        int id = schoolArray.get(i).getAsJsonObject().get("schoolID").getAsInt();
                        String schoolName = schoolArray.get(i).getAsJsonObject().get("schoolName").getAsString();

                        schoolNames[i] = schoolName;
                        schoolModels.add(new SchoolModel(id, schoolName));
                    }
                }
                else {
                    snackBar(next, jsonObject.get("message").getAsString());
                }
            }
            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(next, response.message());
            }
            @Override
            public void onException(Throwable t) {
                snackBar(next, t.getMessage());
                t.printStackTrace();
            }
            @Override
            public void noInternetConnection() {
                snackBar(next, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(), call, "Fetching schools");
    }

    public void showSchoolList(){
        final ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,schoolNames);
        adapter.notifyDataSetChanged();
        final ListView listView = new ListView(getContext());
        listView.setAdapter(adapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(listView);
        final Dialog dialog  = builder.create();
        dialog.show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edtFleetSchoolZoneRange.setText(schoolNames[position]);
                edtFleetSchoolZoneRange.setTag( schoolModels.get(position).getId());
                schoolNameVal = schoolNames[position];
                schoolIdVal = schoolModels.get(position).getId();
                dialog.dismiss();
            }
        });
    }


    public boolean isOwnerValid(){

        if (edtOwnerEmail.getText().toString().isEmpty()){
            snackBar(next, "Owner's email id is mandatory");
            return false;
        }
        if (edtOwnerName.getText().toString().isEmpty()){
            snackBar(next, "Owner's name is mandatory");
            return false;
        }
        if (edtFleetName.getText().toString().isEmpty()){
            snackBar(next, "Fleet name is mandatory");
            return false;
        }
        if (edtFleetPassword.getText().toString().isEmpty()){
            snackBar(next, "Please set password");
            return false;
        }
        if (!edtFleetPassword.getText().toString().equals(edtFleetConfPassword.getText().toString())){
            snackBar(next, "Password doesn't match");
            return false;
        }
        if (!edtFleetPassword.getText().toString().equals(edtFleetConfPassword.getText().toString())){
            snackBar(next, "Password doesn't match");
            return false;
        }
        if (edtFleetAddress1.getText().toString().isEmpty()){
            snackBar(next, "Address1 is mandatory");
            return false;
        }
        if (edtFleetCity.getText().toString().isEmpty()){
            snackBar(next, "City is mandatory");
            return false;
        }
        if (edtFleetPincode.getText().toString().isEmpty()){
            snackBar(next, "Pincode is mandatory");
            return false;
        }
        if (edtFleetState.getText().toString().isEmpty()){
            snackBar(next, "State is mandatory");
            return false;
        }
        if (edtFleetBankAccount.getText().toString().isEmpty()){
            snackBar(next, "Bank account is mandatory");
            return false;
        }
        if (edtFleetOwnersAdharNo.getText().toString().isEmpty() || edtFleetOwnersAdharNo.getText().toString().length() < 12){
            snackBar(next, "Enter 12 digit Aadhaar Card Number");
            return false;
        }
        if (cancelledChequePhotoServerUrl.equals("")){
            snackBar(next, "Upload cancelled cheque");
            return false;
        }
        if (ownerAadhaarPhotoServerUrl.equals("")){
            snackBar(next, "Upload owner's Aadhaar card photo");
            return false;
        }
        if (ownerPhotoServerUrl.equals("")){
            snackBar(next, "Upload owner's photo");
            return false;
        }



        return true;
    }

    public boolean isDriverAttendantValid(){
        if (edtFleetDriversName.getText().toString().isEmpty()){
            snackBar(next,"Driver name is mandatory");
            return false;
        }
        if (edtFleetDriversMobileNo.getText().toString().isEmpty() || edtFleetDriversMobileNo.getText().toString().length() <10){
            snackBar(next,"Enter driver's 10 digit mobile number");
            return false;
        }
        if (driverAadhaarPhotoServerUrl.equals("")){
            snackBar(next,"Upload driver's Aadhaar photo");
            return false;
        }
        if (driverPhotoServerUrl.equals("")){
            snackBar(next,"Upload driver's photo");
            return false;
        }
        if (edtFleetDriversLicenseNo.getText().toString().isEmpty()){
            snackBar(next,"License number is mandatory");
            return false;
        }
        if (licensePhotoServerUrl.equals("")){
            snackBar(next,"Upload license's photo");
            return false;
        }
        if (edtFleetAttendantsName.getText().toString().isEmpty()){
            snackBar(next,"Attendant's name is mandatory");
            return false;
        }
        if (edtFleetAttendantsMobileNo.getText().toString().isEmpty() || edtFleetAttendantsMobileNo.getText().toString().length() < 10){
            snackBar(next,"Enter attendant's 10 digit mobile number");
            return false;
        }
        if (attendantAadhaarPhotoServerUrl.equals("")){
            snackBar(next,"Upload attendant's Aadhaar card photo");
            return false;
        }
        if (attendantPhotoServerUrl.equals("")){
            snackBar(next,"Upload attendant's photo");
            return false;
        }
        if (edtFleetBusNo.getText().toString().isEmpty()){
            snackBar(next,"Bus number is mandatory");
            return false;
        }
        if (busPhotoServerUrl.equals("")){
            snackBar(next,"Upload bus photo");
            return false;
        }
        if (edtFleetNoOfSets.getText().toString().isEmpty()){
            snackBar(next,"Enter number of seats in the nus");
            return false;
        }
        if (edtFleetChassisNo.getText().toString().isEmpty()){
            snackBar(next,"Enter Chassis number");
            return false;
        }
        if (!cbTermsCond.isChecked()){
            snackBar(next,"You should accept the terms and conditions");
            return false;
        }


        return true;
    }

    public void addFleetRegister(){
        JsonObject request = new JsonObject();
        request.addProperty("emailID", edtOwnerEmail.getText().toString());
        request.addProperty("ownerName",edtOwnerName.getText().toString());
        request.addProperty("fleetName", edtFleetName.getText().toString());
        request.addProperty("password", edtFleetPassword.getText().toString());
        request.addProperty("address1", edtFleetAddress1.getText().toString());
        request.addProperty("address2", edtFleetAddress2.getText().toString());
        request.addProperty("city", edtFleetCity.getText().toString());
        request.addProperty("pincode", edtFleetPincode.getText().toString());
        request.addProperty("state", edtFleetState.getText().toString());
        request.addProperty("birthDate", txtFleetOwnerBirthDate.getText().toString());
        request.addProperty("mobileNumber", edtFleetMobileNo.getText().toString());
        request.addProperty("owner_Bank_AC_Details",edtFleetBankAccount.getText().toString());
        request.addProperty("owner_Cancel_Cheque_Photo", cancelledChequePhotoServerUrl);
        request.addProperty("owner_AadharCard_Number", edtFleetOwnersAdharNo.getText().toString());
        request.addProperty("owner_AadharCard_Photo", ownerAadhaarPhotoServerUrl);
        request.addProperty("owner_Photo", ownerPhotoServerUrl);
        request.addProperty("driverName", edtFleetDriversName.getText().toString());
        request.addProperty("driverMobileNumber", edtFleetDriversMobileNo.getText().toString());
        request.addProperty("driverAadharCardPhoto", driverAadhaarPhotoServerUrl);
        request.addProperty("driverPhoto", driverPhotoServerUrl);
        request.addProperty("licenseNumber", edtFleetDriversLicenseNo.getText().toString());
        request.addProperty("licensePhoto", licensePhotoServerUrl);
        request.addProperty("attendantName", edtFleetAttendantsName.getText().toString());
        request.addProperty("attendantMobileNumber", edtFleetAttendantsMobileNo.getText().toString());
        request.addProperty("attendantAadharCardPhoto", attendantAadhaarPhotoServerUrl);
        request.addProperty("attendantPhoto", attendantPhotoServerUrl);
        request.addProperty("busNumber", edtFleetBusNo.getText().toString());
        request.addProperty("busPhoto", busPhotoServerUrl);
        request.addProperty("noOfSeats", edtFleetNoOfSets.getText().toString());
        request.addProperty("chassisNumber", edtFleetChassisNo.getText().toString());
        request.addProperty("schoolID", schoolIdVal);


        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JsonObject> call = apiInterface.addFleetRegister(request);
        APICaller apiCaller = new APICaller();
        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    snackBar(next, "Registered successfully");
                    Preference.getInstance(getContext()).setREGISTERED(true);
                    setFragment((AppCompatActivity)getActivity(), new RouteDetailsSelectionFragment(),R.id.signupContainer,RouteDetailsSelectionFragment.class.getSimpleName(),false);

                }
                else {
                    snackBar(next, jsonObject.get("message").getAsString());
                }
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(next, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(next, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(next, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(),call, "Saving your data");
    }


}

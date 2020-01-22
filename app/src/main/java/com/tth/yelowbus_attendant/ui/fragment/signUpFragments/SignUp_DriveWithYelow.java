package com.tth.yelowbus_attendant.ui.fragment.signUpFragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignUp_DriveWithYelow extends BaseFragment implements View.OnClickListener {

    private EditText edtDriversEmail, edtDriversName, edtDriversMobileNo, edtDriversLicenseNo,  edtAttendantsName, edtAttendantsMobileNo, edtPassword, edtConfPassword, edtBankAccount;
    private EditText edtDriveBusNo, edtDriveNoOfSets;
    private TextView tvDriversAadhaarPhoto, tvDriverPhoto, tvLicensePhoto, tvAttendantsAadhaarPhoto, tvAttendantsPhoto,
            tvOwnersCancelledCheque, tvDriveBusPhoto, tvDriveBusRcPhoto, tvDriveBusPermitPhoto, tvDriveBusinsurancePhoto, edtSchoolZoneRange;
    View tabDriverAndAttendant, tabBus, layout_driver_attendants_info, layoutBusInfo, layoutTerms, next;
    ImageView busDriverAndAttendantIndicator, busBusIndicator;
    AppCompatCheckBox cbTermsCond;

    public static final String FROM_DRIVE_DRIVERS_AADHAAR = "drive_driver_aadhaar";
    public static final String FROM_DRIVE_DRIVER_PHOTO = "drive_driver_photo";
    public static final String FROM_DRIVE_LICENSE_PHOTO = "drive_license_photo";
    public static final String FROM_DRIVE_ATTENDANT_AADHAAR = "drive_attention";
    public static final String FROM_DRIVE_ATTENDANTS_PHOTO = "drive_attendants_photo";
    public static final String FROM_DRIVE_CANCELLED_CHEQUE = "drive_cancelled_cheque";
    public static final String FROM_DRIVE_BUS_PHOTO = "drive_bus_photo";
    public static final String FROM_DRIVE_BUS_RC = "drive_bus_rc";
    public static final String FROM_DRIVE_BUS_PERMIT = "drive_bus_permit";
    public static final String FROM_DRIVE_BUS_INSURANCE = "drive_bus_insurance";

    public static String driveCancelledChequePath = "",    driveDriversAadhaarPath="", driveDriverPhotoPath="", driveLicensePath="", driveAttendantsAadhaarPath="",
                                    driveAttendantsPhotoPath="", driveBusPhotoPath="", driveBusRcPath="", driveBusPermitPath="", driveBusInsurancePath="";

    public static String cancelChequePhotoServerUrl="", driverAadharPhotoServerUrl="", driverPhotoServerUrl="", licensePhotoServerUrl="", attendantAadharPhotoServerUrl="",
                    attendantPhotoServerUrl="", busPhotoServerUrl="", busRcPhotoServerUrl="", busPermitPhotoServerUrl="", busInsurancePhotoServerUrl="";

    String[] schoolNames;
    final List<SchoolModel> schoolModels = new ArrayList<>();
    public static String schoolNameVal = "";
    public static int schoolIdVal = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sign_up__drive_with_yelow, container, false);
        edtDriversEmail = view.findViewById(R.id.edtDriversEmail);
        edtDriversName = view.findViewById(R.id.edtDriversName);
        edtDriversMobileNo = view.findViewById(R.id.edtDriversMobileNo);
        edtDriversLicenseNo = view.findViewById(R.id.edtDriversLicenseNo);
        edtAttendantsName = view.findViewById(R.id.edtAttendantsName);
        edtAttendantsMobileNo = view.findViewById(R.id.edtAttendantsMobileNo);
        edtPassword = view.findViewById(R.id.edtPassword);
        edtConfPassword = view.findViewById(R.id.edtConfPassword);
        edtBankAccount = view.findViewById(R.id.edtBankAccount);
        edtDriveBusNo = view.findViewById(R.id.edtDriveBusNo);
        edtDriveNoOfSets = view.findViewById(R.id.edtDriveNoOfSets);
        tvDriveBusPhoto = view.findViewById(R.id.tvDriveBusPhoto);
        tvDriveBusRcPhoto = view.findViewById(R.id.tvDriveBusRcPhoto);
        tvDriveBusPermitPhoto = view.findViewById(R.id.tvDriveBusPermitPhoto);
        tvDriveBusinsurancePhoto = view.findViewById(R.id.tvDriveBusinsurancePhoto);
        tvOwnersCancelledCheque = view.findViewById(R.id.tvOwnersCancelledCheque);
        tvDriversAadhaarPhoto = view.findViewById(R.id.tvDriversAadhaarPhoto);
        tvDriverPhoto = view.findViewById(R.id.tvDriverPhoto);
        tvLicensePhoto = view.findViewById(R.id.tvLicensePhoto);
        tvAttendantsAadhaarPhoto = view.findViewById(R.id.tvAttendantsAadhaarPhoto);
        tvAttendantsPhoto = view.findViewById(R.id.tvAttendantsPhoto);
        edtSchoolZoneRange = view.findViewById(R.id.edtSchoolZoneRange);
        tabDriverAndAttendant = view.findViewById(R.id.tabDriverAndAttendant);
        tabBus = view.findViewById(R.id.tabBus);
        layout_driver_attendants_info = view.findViewById(R.id.layout_driver_attendants_info);
        layoutBusInfo = view.findViewById(R.id.layoutBusInfo);
        busDriverAndAttendantIndicator = view.findViewById(R.id.busDriverAndAttendantIndicator);
        busBusIndicator = view.findViewById(R.id.busBusIndicator);
        layoutTerms = view.findViewById(R.id.layoutTerms);
        next = view.findViewById(R.id.next);
        cbTermsCond = view.findViewById(R.id.cbTermsCond);


        tvOwnersCancelledCheque.setOnClickListener(this);
        tvDriversAadhaarPhoto.setOnClickListener(this);
        tvDriverPhoto.setOnClickListener(this);
        tvLicensePhoto.setOnClickListener(this);
        tvAttendantsAadhaarPhoto.setOnClickListener(this);
        tvAttendantsPhoto.setOnClickListener(this);
        tvDriveBusPhoto.setOnClickListener(this);
        tvDriveBusRcPhoto.setOnClickListener(this);
        tvDriveBusPermitPhoto.setOnClickListener(this);
        tvDriveBusinsurancePhoto.setOnClickListener(this);
        tabDriverAndAttendant.setOnClickListener(this);
        tabBus.setOnClickListener(this);
        next.setOnClickListener(this);
        edtSchoolZoneRange.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabDriverAndAttendant.performClick();
        getSchoolList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tabDriverAndAttendant:
                layout_driver_attendants_info.setVisibility(View.VISIBLE);
                layoutBusInfo.setVisibility(View.GONE);
                busDriverAndAttendantIndicator.setVisibility(View.VISIBLE);
                busBusIndicator.setVisibility(View.INVISIBLE);
                layoutTerms.setVisibility(View.GONE);
                break;

            case R.id.tabBus:
                layout_driver_attendants_info.setVisibility(View.GONE);
                layoutBusInfo.setVisibility(View.VISIBLE);
                busDriverAndAttendantIndicator.setVisibility(View.INVISIBLE);
                busBusIndicator.setVisibility(View.VISIBLE);
                layoutTerms.setVisibility(View.VISIBLE);
                break;

            case R.id.next:
                if (layout_driver_attendants_info.getVisibility() == View.VISIBLE){
                    if (isDriverAttendantValid()){
                        tabBus.performClick();
                    }
                }
                else{
                    if (isDriverAttendantValid()){
                        if (isBusValid()){
                            addDriveWithYelow();
                        }
                    }
                    else {
                        tabDriverAndAttendant.performClick();
                    }
                }

                    break;

            case R.id.tvOwnersCancelledCheque:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveCancelledChequePath,"Cancelled Cheque","Upload Cancelled Cheque",FROM_DRIVE_CANCELLED_CHEQUE),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriversAadhaarPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveDriversAadhaarPath,"Driver's Aadhaar Card Photo","Upload Driver's Aadhaar Card Photo",FROM_DRIVE_DRIVERS_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriverPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveDriverPhotoPath,"Driver's Photo","Upload Driver's Photo",FROM_DRIVE_DRIVER_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLicensePhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveLicensePath,"License Photo","Upload License's Photo",FROM_DRIVE_LICENSE_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvAttendantsAadhaarPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveAttendantsAadhaarPath,"Attendant's Aadhaar Card Photo","Upload Attendant's Aadhaar Card Photo",FROM_DRIVE_ATTENDANT_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvAttendantsPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveAttendantsPhotoPath,"Attendant's Photo","Upload Attendant's Photo",FROM_DRIVE_ATTENDANTS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriveBusPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveBusPhotoPath,"Bus Photo","Upload Bus Photo",FROM_DRIVE_BUS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriveBusRcPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveBusRcPath,"Bus RC Photo","Upload Bus RC Photo",FROM_DRIVE_BUS_RC),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriveBusPermitPhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveBusPermitPath,"Bus Permit Photo","Upload Bus Permit Photo",FROM_DRIVE_BUS_PERMIT),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvDriveBusinsurancePhoto:
                setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveBusInsurancePath,"Bus Insurance Photo","Upload Bus Insurance Photo",FROM_DRIVE_BUS_INSURANCE),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.edtSchoolZoneRange:
                if (schoolModels.size() > 0)
                    showSchoolList();
                else
                    snackBar(next, "No school found");

                break;
        }
    }

    public boolean isDriverAttendantValid(){
        if (edtDriversEmail.getText().toString().isEmpty()){
            snackBar(next,"Email is mandatory");
            return false;
        }
        if (edtDriversName.getText().toString().isEmpty()){
            snackBar(next,"Driver name is mandatory");
            return false;
        }
        if (edtDriversMobileNo.getText().toString().isEmpty() || edtDriversMobileNo.getText().toString().length() < 10){
            snackBar(next,"Driver's 10 digit mobile number is mandatory");
            return false;
        }
        if (driverAadharPhotoServerUrl.equals("")){
            snackBar(next,"Upload Driver's Aadhaar card photo");
            return false;
        }
        if (driverPhotoServerUrl.equals("")){
            snackBar(next,"Upload Driver's photo");
            return false;
        }
        if (edtDriversName.getText().toString().isEmpty()){
            snackBar(next,"License Number is mandatory");
            return false;
        }
        if (licensePhotoServerUrl.equals("")){
            snackBar(next,"Upload license photo");
            return false;
        }
        if (edtAttendantsName.getText().toString().isEmpty()){
            snackBar(next,"Attendant name is mandatory");
            return false;
        }
        if (edtAttendantsMobileNo.getText().toString().isEmpty() || edtAttendantsMobileNo.getText().toString().length() < 10){
            snackBar(next,"Attendant's 10 digit mobile number is mandatory");
            return false;
        }
        if (attendantAadharPhotoServerUrl.equals("")){
            snackBar(next,"Upload Attendant's Aadhaar card photo");
            return false;
        }
        if (attendantPhotoServerUrl.equals("")){
            snackBar(next,"Upload Attendant's photo");
            return false;
        }
        if (edtPassword.getText().toString().isEmpty()){
            snackBar(next,"Enter Password");
            return false;
        }
        if (!edtPassword.getText().toString().equals(edtConfPassword.getText().toString())){
            snackBar(next,"Passwords doesn't match");
            return false;
        }
        if (cancelChequePhotoServerUrl.equals("")){
            snackBar(next,"Upload Cancelled Cheque");
            return false;
        }



        return true;
    }

    public boolean isBusValid(){
        if (edtDriveBusNo.getText().toString().isEmpty()){
            snackBar(next, "Bus number is mandatory");
            return false;
        }
        if (edtDriveNoOfSets.getText().toString().isEmpty()){
            snackBar(next, "Enter number of seats in the bus");
            return false;
        }
        if (busPhotoServerUrl.equals("")){
            snackBar(next, "Upload bus photo");
            return false;
        }
        if (busRcPhotoServerUrl.equals("")){
            snackBar(next, "Upload bus Rc photo");
            return false;
        }
        if (busPermitPhotoServerUrl.equals("")){
            snackBar(next, "Upload bus Permit photo");
            return false;
        }
        if (busInsurancePhotoServerUrl.equals("")){
            snackBar(next, "Upload bus insurance photo");
            return false;
        }
        if (!cbTermsCond.isChecked()){
            snackBar(next, "You should accept the terms and conditions");
            return false;

        }







        return true;
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
                edtSchoolZoneRange.setText(schoolNames[position]);
                edtSchoolZoneRange.setTag( schoolModels.get(position).getId());
                schoolNameVal = schoolNames[position];
                schoolIdVal = schoolModels.get(position).getId();
                dialog.dismiss();
            }
        });
    }



    public void addDriveWithYelow(){
        JsonObject request = new JsonObject();
        request.addProperty("emailID",edtDriversEmail.getText().toString());
        request.addProperty("driverName",edtDriversName.getText().toString());
        request.addProperty("driverMobileNumber",edtDriversMobileNo.getText().toString());
        request.addProperty("driverAadharCardPhoto", driverAadharPhotoServerUrl);
        request.addProperty("driverPhoto", driverPhotoServerUrl);
        request.addProperty("licenseNumber",edtDriversLicenseNo.getText().toString());
        request.addProperty("licensePhoto", licensePhotoServerUrl);
        request.addProperty("attendantName",edtAttendantsName.getText().toString());
        request.addProperty("attendantMobileNumber",edtAttendantsMobileNo.getText().toString());
        request.addProperty("attendantAadharCardPhoto", attendantAadharPhotoServerUrl);
        request.addProperty("attendantPhoto", attendantPhotoServerUrl);
        request.addProperty("schoolID", schoolIdVal);
        request.addProperty("password",edtPassword.getText().toString());
        request.addProperty("bankAccountDetails",edtBankAccount.getText().toString());
        request.addProperty("cancel_cheque_photo", cancelChequePhotoServerUrl);

        request.addProperty("busNumber",edtDriveBusNo.getText().toString());
        request.addProperty("busPhoto",busPhotoServerUrl);
        request.addProperty("noOfSeats", edtDriveNoOfSets.getText().toString());
        request.addProperty("chassisNumber", "");
        request.addProperty("busRCBookPhoto",busRcPhotoServerUrl);
        request.addProperty("busPermitPhoto", busPermitPhotoServerUrl);
        request.addProperty("busInsurancePhoto", busInsurancePhotoServerUrl);

        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        Call<JsonObject> call = apiInterface.addDriveWithYelow(request);
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
        apiCaller.callAPI(getContext(), call, "Saving your details");
    }


}

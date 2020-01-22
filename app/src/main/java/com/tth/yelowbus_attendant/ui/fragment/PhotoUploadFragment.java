package com.tth.yelowbus_attendant.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.api.APICaller;
import com.tth.yelowbus_attendant.api.APIClient;
import com.tth.yelowbus_attendant.api.APIInterface;
import com.tth.yelowbus_attendant.ui.fragment.signUpFragments.SignUp_DriveWithYelow;
import com.tth.yelowbus_attendant.ui.fragment.signUpFragments.SignUp_Fleet;
import com.tth.yelowbus_attendant.util.ImageUtils;
import com.tth.yelowbus_attendant.util.Util;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PhotoUploadFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvUpload, tvTitle;
    private ImageView ivPhoto;
    private View btnNext;
    private AppCompatImageView ivBack;
    private final int IMAGE_PICK = 111, REQUEST_CAPTURE_IMAGE = 222;
    private final int RC_CAMERA = 123;
    ImageUtils imageUtils;
    ImageUtils.ImageIntentListener imageIntentListener;

    String selectedImagePath = "";
    String title = "", uploadMsg = "";
    String from = "";

    public PhotoUploadFragment(String selectedImagePath, String title, String uploadMsg, String from) {
        this.selectedImagePath = selectedImagePath;
        this.title = title;
        this.uploadMsg = uploadMsg;
        this.from = from;
    }

    public String getSelectedImagePath() {
        return selectedImagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getUploadMsg() {
        return uploadMsg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo_upload, container, false);

        tvUpload = view.findViewById(R.id.tvUpload);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        tvTitle = view.findViewById(R.id.tvTitle);
        btnNext = view.findViewById(R.id.btnNext);
        ivBack = view.findViewById(R.id.ivBack);

        tvUpload.setText(uploadMsg);
        tvTitle.setText(title);

        tvUpload.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        ivBack.setOnClickListener(this);

        imageIntentListener = new ImageUtils.ImageIntentListener() {
            @Override
            public void onCameraIntent(Intent intent, int requestCode) {
                startActivityForResult(intent, requestCode);
            }

            @Override
            public void onGalleryIntent(Intent intent, int requestCode) {
                startActivityForResult(intent, requestCode);
            }
        };
        imageUtils = new ImageUtils(imageIntentListener);


        if (! selectedImagePath.equals(""))
            ivPhoto.setImageURI(Uri.parse(selectedImagePath));

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tvUpload:
                if (imageUtils.hasCameraAndStoragePermission(getContext())) {
                    imageUtils.actionDialogBox((AppCompatActivity) getActivity(), IMAGE_PICK, REQUEST_CAPTURE_IMAGE);
                } else {
                    requestForCameraAndStorage();
                }
                break;
            case R.id.btnNext:
//                getActivity().onBackPressed();
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.ivBack:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
        }
    }
    private void requestForCameraAndStorage() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_CAMERA) {
            if (imageUtils.hasCameraAndStoragePermission(getContext())) {

            } else {
                requestForCameraAndStorage();
            }
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case IMAGE_PICK:
                    selectedImagePath = imageUtils.getAbsolutePath(getActivity(), data.getData());
                    setSelectedImagePath(selectedImagePath);
                    ivPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;

                case REQUEST_CAPTURE_IMAGE:
                    selectedImagePath = ImageUtils.imageFilePath;
                    setSelectedImagePath(selectedImagePath);
                    ivPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;
            }
        }
    }


    public void setSelectedImagePath(String selectedImagePath){
        switch (from){

            case SignupFragment.FROM_LEASE_DRIVERS_AADHAAR:
                SignupFragment.leaseDriversAadhaarPath = selectedImagePath;
                break;

            case SignupFragment.FROM_LEASE_DRIVER_PHOTO:
                SignupFragment.leaseDriverPhotoPath = selectedImagePath;
                break;
            case SignupFragment.FROM_LEASE_LICENSE_PHOTO:
                SignupFragment.leaseLicensePath = selectedImagePath;
                break;
            case SignupFragment.FROM_LEASE_ATTENDANT_AADHAAR:
                SignupFragment.leaseAttendantsAadhaarPath = selectedImagePath;
                break;

            case SignupFragment.FROM_LEASE_ATTENDANTS_PHOTO:
                SignupFragment.leaseAttendantsPhotoPath = selectedImagePath;
                break;

            case SignupFragment.FROM_LEASE_CANCELLED_CHEQUE:
                SignupFragment.leaseCancelledChequePath = selectedImagePath;
                break;




        /***********************FLEET*******************************/
            case SignUp_Fleet.FROM_FLEET_CANCELLED_CHEQUE:
                SignUp_Fleet.fleetOwnersCancelledChequePath = selectedImagePath;
                uploadFileForFleet("OperatorChequePhoto", selectedImagePath);
                break;
            case SignUp_Fleet.FROM_FLEET_OWNER_AADHAAR:
                SignUp_Fleet.fleetOwnersAadhaarPath = selectedImagePath;
                uploadFileForFleet("OperatorAadharPhoto", selectedImagePath);
                break;
            case SignUp_Fleet.FROM_FLEET_OWNER_PHOTO:
                SignUp_Fleet.fleetOwnersPhotoPath = selectedImagePath;
                uploadFileForFleet("OperatorPhoto", selectedImagePath);
                break;

            case SignUp_Fleet.FROM_FLEET_DRIVER_AADHAAR:
                SignUp_Fleet.fleetDriversAadhaarPath = selectedImagePath;
                uploadFileForFleet("DriverAadharPhoto", selectedImagePath);
                break;

            case SignUp_Fleet.FROM_FLEET_DRIVER_PHOTO:
                SignUp_Fleet.fleetDriversPhotoPath = selectedImagePath;
                uploadFileForFleet("DriverPhoto", selectedImagePath);
                break;

            case SignUp_Fleet.FROM_FLEET_LICENSE_PHOTO:
                SignUp_Fleet.fleetlicensePath = selectedImagePath;
                uploadFileForFleet("LicensePhoto", selectedImagePath);
                break;
            case SignUp_Fleet.FROM_FLEET_ATTENDANTS_AADHAAR:
                SignUp_Fleet.fleetAttendantsAadhaarPath = selectedImagePath;
                uploadFileForFleet("AttendantAadharPhoto", selectedImagePath);
                break;
            case SignUp_Fleet.FROM_FLEET_ATTENDANTS_PHOTO:
                SignUp_Fleet.fleetAttendantsPhotoPath = selectedImagePath;
                uploadFileForFleet("AttendantPhoto", selectedImagePath);
                break;

            case SignUp_Fleet.FROM_FLEET_BUS_PHOTO:
                SignUp_Fleet.fleetBusPhotoPath = selectedImagePath;
                uploadFileForFleet("BusPhoto", selectedImagePath);
                break;






            /********************** DRIVE WITH YELOW BUS *****************/

            case SignUp_DriveWithYelow.FROM_DRIVE_DRIVERS_AADHAAR:
                SignUp_DriveWithYelow.driveDriversAadhaarPath = selectedImagePath;
                uploadFileForDriveWithYelow("DriverAadharPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_DRIVER_PHOTO:
                SignUp_DriveWithYelow.driveDriverPhotoPath = selectedImagePath;
                uploadFileForDriveWithYelow("DriverPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_LICENSE_PHOTO:
                SignUp_DriveWithYelow.driveLicensePath = selectedImagePath;
                uploadFileForDriveWithYelow("LicensePhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_ATTENDANT_AADHAAR:
                SignUp_DriveWithYelow.driveAttendantsAadhaarPath = selectedImagePath;
                uploadFileForDriveWithYelow("AttendantAadharPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_ATTENDANTS_PHOTO:
                SignUp_DriveWithYelow.driveAttendantsPhotoPath = selectedImagePath;
                uploadFileForDriveWithYelow("AttendantPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_CANCELLED_CHEQUE:
                SignUp_DriveWithYelow.driveCancelledChequePath = selectedImagePath;
                uploadFileForDriveWithYelow("AttendantChequePhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_BUS_PHOTO:
                SignUp_DriveWithYelow.driveBusPhotoPath = selectedImagePath;
                uploadFileForDriveWithYelow("BusPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_BUS_RC:
                SignUp_DriveWithYelow.driveBusRcPath = selectedImagePath;
                uploadFileForDriveWithYelow("BusRcPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_BUS_PERMIT:
                SignUp_DriveWithYelow.driveBusPermitPath = selectedImagePath;
                uploadFileForDriveWithYelow("BusPermitPhoto", selectedImagePath);
                break;

            case SignUp_DriveWithYelow.FROM_DRIVE_BUS_INSURANCE:
                SignUp_DriveWithYelow.driveBusInsurancePath = selectedImagePath;
                uploadFileForDriveWithYelow("BusInsurancePhoto", selectedImagePath);
                break;


        }
    }

    public void uploadFileForDriveWithYelow(final String fileKey, String filePath){

        JsonObject request = new JsonObject();
        request.addProperty("imageKey",fileKey);
        request.addProperty("base64Image", Util.imageToBase64(filePath));
        request.addProperty("extension", Util.getExtension(filePath));

        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        APICaller apiCaller = new APICaller();
        Call<JsonObject> call = apiInterface.uploadFile(request);

        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    if (!jsonObject.get("path").getAsString().equals("")){
                        snackBar(tvUpload, "Photo uploaded successfully");
                        switch (fileKey){
                            case "DriverAadharPhoto":
                                SignUp_DriveWithYelow.driverAadharPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "DriverPhoto":
                                SignUp_DriveWithYelow.driverPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "LicensePhoto":
                                SignUp_DriveWithYelow.licensePhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "AttendantAadharPhoto":
                                SignUp_DriveWithYelow.attendantAadharPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "AttendantPhoto":
                                SignUp_DriveWithYelow.attendantPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "AttendantChequePhoto":
                                SignUp_DriveWithYelow.cancelChequePhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "BusPhoto":
                                SignUp_DriveWithYelow.busPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "BusRcPhoto":
                                SignUp_DriveWithYelow.busRcPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "BusPermitPhoto":
                                SignUp_DriveWithYelow.busPermitPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                            case "BusInsurancePhoto":
                                SignUp_DriveWithYelow.busInsurancePhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                        }
                    }
                    else {
                        snackBar(tvUpload, "Failed! Something went wrong");
                    }
                }
                else{
                    snackBar(tvUpload, jsonObject.get("message").getAsString()+" - "+ jsonObject.get("statusCode").getAsInt());
                }
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(tvUpload, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(btnNext, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(tvUpload, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(), call,"Uploading");

    }


    public void uploadFileForFleet(final String fileKey, String filePath){

        JsonObject request = new JsonObject();
        request.addProperty("imageKey",fileKey);
        request.addProperty("base64Image", Util.imageToBase64(filePath));
        request.addProperty("extension", Util.getExtension(filePath));

        Retrofit retrofit = APIClient.getRetrofit();
        APIInterface apiInterface = retrofit.create(APIInterface.class);
        APICaller apiCaller = new APICaller();
        Call<JsonObject> call = apiInterface.uploadFile(request);

        apiCaller.setApiListener(new APICaller.APIListener() {
            @Override
            public void onSuccess(JsonObject jsonObject) {
                if (jsonObject.get("statusCode").getAsInt() == 200){
                    if (!jsonObject.get("path").getAsString().equals("")){
                        snackBar(tvUpload, "Photo uploaded successfully");
                        switch (fileKey){
                            case "OperatorChequePhoto":
                                SignUp_Fleet.cancelledChequePhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "OperatorAadharPhoto":
                                SignUp_Fleet.ownerAadhaarPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "OperatorPhoto":
                                SignUp_Fleet.ownerPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "DriverAadharPhoto":
                                SignUp_Fleet.driverAadhaarPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "DriverPhoto":
                                SignUp_Fleet.driverPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "LicensePhoto":
                                SignUp_Fleet.licensePhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "AttendantAadharPhoto":
                                SignUp_Fleet.attendantAadhaarPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "AttendantPhoto":
                                SignUp_Fleet.attendantPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;

                            case "BusPhoto":
                                SignUp_Fleet.busPhotoServerUrl = jsonObject.get("path").getAsString();
                                break;
                        }
                    }
                    else {
                        snackBar(tvUpload, "Failed! Something went wrong");
                    }
                }
                else{
                    snackBar(tvUpload, jsonObject.get("message").getAsString()+" - "+ jsonObject.get("statusCode").getAsInt());
                }
            }

            @Override
            public void onError(Response<JsonObject> response) {
                snackBar(tvUpload, response.message());
            }

            @Override
            public void onException(Throwable t) {
                t.printStackTrace();
                snackBar(btnNext, t.getMessage());
            }

            @Override
            public void noInternetConnection() {
                snackBar(tvUpload, getResources().getString(R.string.network_not_connected));
            }
        });
        apiCaller.callAPI(getContext(), call,"Uploading");

    }


}

package com.tth.yelowbus_attendant.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.util.ImageUtils;

import java.io.File;


public class PhotoUploadFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvUpload, tvTitle;
    private ImageView ivPhoto;
    private final int IMAGE_PICK = 111, REQUEST_CAPTURE_IMAGE = 222;
    private final int RC_CAMERA = 123;
    ImageUtils imageUtils;
    ImageUtils.ImageIntentListener imageIntentListener;

    String selectedImagePath = "";
    String title = "", uploadMsg = "";

    public PhotoUploadFragment(String selectedImagePath, String title, String uploadMsg) {
        this.selectedImagePath = selectedImagePath;
        this.title = title;
        this.uploadMsg = uploadMsg;
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

        tvUpload.setText(uploadMsg);
        tvTitle.setText(title);

        tvUpload.setOnClickListener(this);



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
//                    SignupFragment1.aadharPhotoPath = selectedAadharImagePath;
                    File file2 = new File(selectedImagePath);
                    ivPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;

                case REQUEST_CAPTURE_IMAGE:
                    selectedImagePath = ImageUtils.imageFilePath;
//                    SignupFragment1.aadharPhotoPath = selectedAadharImagePath;
                    File file = new File(selectedImagePath);
                    ivPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;
            }
        }
    }

}

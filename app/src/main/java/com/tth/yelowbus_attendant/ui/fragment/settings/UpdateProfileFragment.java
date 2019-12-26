package com.tth.yelowbus_attendant.ui.fragment.settings;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.fragment.BaseFragment;
import com.tth.yelowbus_attendant.util.ImageUtils;

import java.io.File;

public class UpdateProfileFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvCancel, tvEditPhoto;
    private ImageView ivPersonPhoto, ivCamera;

    private final int IMAGE_PICK = 111, REQUEST_CAPTURE_IMAGE = 222;
    private final int RC_CAMERA = 123;
    public static String selectedImagePath = "";
    ImageUtils imageUtils;
    ImageUtils.ImageIntentListener imageIntentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update_profile, container, false);

        tvCancel = view.findViewById(R.id.tvCancel);
        tvEditPhoto = view.findViewById(R.id.tvEditPhoto);
        ivPersonPhoto = view.findViewById(R.id.ivPersonPhoto);
        ivCamera = view.findViewById(R.id.ivCamera);

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


        tvCancel.setOnClickListener(this);
        tvEditPhoto.setOnClickListener(this);
        ivCamera.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvCancel:
                getActivity().onBackPressed();
                break;

            case R.id.ivCamera:
            case R.id.tvEditPhoto:
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
                    File file2 = new File(selectedImagePath);
                    ivPersonPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;

                case REQUEST_CAPTURE_IMAGE:
                    selectedImagePath = ImageUtils.imageFilePath;
                    File file = new File(selectedImagePath);
                    ivPersonPhoto.setImageURI(Uri.parse(selectedImagePath));
                    break;
            }
        }
    }

}

package com.tth.yelowbus_attendant.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.tth.yelowbus_attendant.ui.fragment.AddPhotoBottomDialogFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImageUtils {

    public static String imageFilePath  ="";
    private ImageIntentListener imageIntentListener;

    public ImageUtils(ImageIntentListener imageIntentListener) {
        this.imageIntentListener = imageIntentListener;
    }

    public  boolean hasCameraAndStoragePermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED)
            return true;
        else
            return false;
    }

    public  void actionDialogBox(final AppCompatActivity activity, final int IMAGE_PICK, final int REQUEST_CAPTURE_IMAGE) {

        final AddPhotoBottomDialogFragment addPhotoBottomDialogFragment = AddPhotoBottomDialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(activity.getSupportFragmentManager(), "add_photo_dialog_fragment");

        addPhotoBottomDialogFragment.setOnOptionSelectedListener(new AddPhotoBottomDialogFragment.OnOptionSelectedListener() {
            @Override
            public void optionSelected(String option) {
                switch (option){
                    case AddPhotoBottomDialogFragment.CAMERA:
                        addPhotoBottomDialogFragment.dismiss();
                        openCameraIntent(activity, REQUEST_CAPTURE_IMAGE);
                        break;

                    case AddPhotoBottomDialogFragment.GALLERY:
                        addPhotoBottomDialogFragment.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        imageIntentListener.onGalleryIntent(Intent.createChooser(intent, "Select Photo"), IMAGE_PICK);
                        break;
                }

            }
        });


    }

    private void openCameraIntent(Activity activity,int REQUEST_CAPTURE_IMAGE) {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if (pictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile(activity);
            } catch (IOException ex) {

                ex.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(activity, "com.tth.yelowbus_attendant.provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                pictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageIntentListener.onCameraIntent(pictureIntent, REQUEST_CAPTURE_IMAGE);
//                activity.startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }
    private static File createImageFile(Activity activity) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",         /* suffix */
                storageDir      /* directory */
        );
        imageFilePath = image.getAbsolutePath();
        return image;
    }


    public  String getAbsolutePath(Activity activity, Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        @SuppressWarnings("deprecation")
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

    public interface ImageIntentListener {
        void onCameraIntent(Intent intent, int requestCode);
        void onGalleryIntent(Intent intent, int requestCode);
    }
}

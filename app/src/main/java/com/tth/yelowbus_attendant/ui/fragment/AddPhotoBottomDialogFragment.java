package com.tth.yelowbus_attendant.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tth.yelowbus_attendant.R;

public class AddPhotoBottomDialogFragment extends BottomSheetDialogFragment {

    LinearLayout layoutCamera, layoutGallery;
    public static final String CAMERA  ="camera";
    public static final String GALLERY  ="gallery";
    OnOptionSelectedListener onOptionSelectedListener;

    public static AddPhotoBottomDialogFragment newInstance() {
        return new AddPhotoBottomDialogFragment();
    }

    public void setOnOptionSelectedListener(OnOptionSelectedListener onOptionSelectedListener){
        this.onOptionSelectedListener = onOptionSelectedListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.select_image_layout, container,false);
        layoutCamera = view.findViewById(R.id.layoutCamera);
        layoutGallery = view.findViewById(R.id.layoutGallery);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOptionSelectedListener !=  null){
                    onOptionSelectedListener.optionSelected(CAMERA);
                }
            }
        });

        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOptionSelectedListener !=  null){
                    onOptionSelectedListener.optionSelected(GALLERY);
                }
            }
        });
    }

   public interface OnOptionSelectedListener{
        void optionSelected(String option);
    }
}
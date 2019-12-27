package com.tth.yelowbus_attendant.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.ui.activity.HomeActivity;
import com.tth.yelowbus_attendant.util.Constants;
import com.tth.yelowbus_attendant.util.Preference;

public class SignupFragment extends BaseFragment implements View.OnClickListener {


    private String profileType = "";
    private TextView tvProfileLabel;
    private View tabFleetOwner, tabBusDriver, tabDriverAndAttendant, tabBus, next;
    private ImageView fleetIndicator, busDriverIndicator, busDriverAndAttendantIndicator,busBusIndicator;
    private View layout_fleet_owners_info, layout_bus_and_driver_info, layout_driver_attendants_info, layoutBusInfo, layoutTerms;
    private AppCompatCheckBox cbTermsCond;


/* *********************   fleet photos textview *****************************************************/
    private TextView tvFleetOwnersCancelledCheque, tvFleetOwnersAadharPhoto, tvFleetOwnersPhoto, tvFleetDriversAadhaarPhoto,
        tvFleetDriverPhoto, tvFleetDriverLicensePhoto, tvFleetAttendantsAadhaarPhoto, tvFleetAttendantsPhoto, tvFleetBusPhoto;


/*  ********************  lease photoa textview **************************************************/
    private TextView tvLeaseDriversAadhaarPhoto, tvLeaseDriverPhoto, tvLeaseLicensePhoto, tvLeaseAttendantsAadhaarPhoto,
        tvLeaseAttendantsPhoto, tvLeaseOwnersCancelledCheque;


/*   ******************* bus info textview ************************************/
    private TextView tvDriveBusPhoto, tvDriveBusRcPhoto, tvDriveBusPermitPhoto, tvDriveBusinsurancePhoto;


/*  ************** ALL  image paths*/
    public static String fleetOwnersCancelledChequePath="", fleetOwnersAadhaarPath="", fleetOwnersPhotoPath="",
            fleetDriversAadhaarPath="", fleetDriversPhotoPath="", fleetlicensePath="", fleetAtendantsAadhaarPath="",
            fleetAttendantsPhotoPath="", fleetBusPhotoPath="",


            leaseDriversAadhaarPath="", leaseDriverPhotoPath="", leaseLicensePath="", leaseAttendantsAadhaarPath="",
            leaseAttendantsPhotoPath="", leaseCancelledCheque="",


            driveBusPhotoPath="", driveBusRcPath="", driveBusPermitPath="", driveBusInsurancePath="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_signup, container, false);
        profileType = Preference.getInstance(getContext()).getUserProfileType();
        tvProfileLabel = view.findViewById(R.id.tvProfileLabel);
        tabFleetOwner = view.findViewById(R.id.tabFleetOwner);
        tabBusDriver = view.findViewById(R.id.tabBusDriver);
        tabDriverAndAttendant = view.findViewById(R.id.tabDriverAndAttendant);
        tabBus = view.findViewById(R.id.tabBus);
        fleetIndicator = view.findViewById(R.id.fleetIndicator);
        busDriverIndicator = view.findViewById(R.id.busDriverIndicator);
        busDriverAndAttendantIndicator = view.findViewById(R.id.busDriverAndAttendantIndicator);
        busBusIndicator = view.findViewById(R.id.busBusIndicator);
        layout_fleet_owners_info = view.findViewById(R.id.layout_fleet_owners_info);
        layout_bus_and_driver_info = view.findViewById(R.id.layout_bus_and_driver_info);
        layout_driver_attendants_info = view.findViewById(R.id.layout_driver_attendants_info);
        layoutBusInfo = view.findViewById(R.id.layoutBusInfo);
        layoutTerms = view.findViewById(R.id.layoutTerms);
        cbTermsCond = view.findViewById(R.id.cbTermsCond);
        next = view.findViewById(R.id.next);



        tvFleetOwnersCancelledCheque = view.findViewById(R.id.tvFleetOwnersCancelledCheque);

        layout_fleet_owners_info.setVisibility(View.GONE);
        layout_bus_and_driver_info.setVisibility(View.GONE);
        layout_driver_attendants_info.setVisibility(View.GONE);
        layoutBusInfo.setVisibility(View.GONE);
        layoutTerms.setVisibility(View.GONE);



        tabFleetOwner.setOnClickListener(this);
        tabBusDriver.setOnClickListener(this);
        tabDriverAndAttendant.setOnClickListener(this);
        tabBus.setOnClickListener(this);
        next.setOnClickListener(this);


        tvFleetOwnersCancelledCheque.setOnClickListener(this);


        showProfileTypeFields();


        return view;

    }

    public void showProfileTypeFields() {
        if (profileType.equals(Constants.FLEET)) {
            tvProfileLabel.setText("For Fleet");
            tabFleetOwner.setVisibility(View.VISIBLE);
            tabBusDriver.setVisibility(View.VISIBLE);
            tabDriverAndAttendant.setVisibility(View.GONE);
            tabBus.setVisibility(View.GONE);
            tabFleetOwner.performClick();

        } else if (profileType.equals(Constants.LEASE_VEHICLE)) {
            tvProfileLabel.setText("For Lease a Vehicle");
            tabFleetOwner.setVisibility(View.GONE);
            tabBusDriver.setVisibility(View.GONE);
            tabDriverAndAttendant.setVisibility(View.VISIBLE);
            tabBus.setVisibility(View.GONE);
            tabDriverAndAttendant.performClick();

        } else if (profileType.equals(Constants.DRIVE_WITH_YELOW)) {
            tvProfileLabel.setText("For Drive with Yelow");
            tabFleetOwner.setVisibility(View.GONE);
            tabBusDriver.setVisibility(View.GONE);
            tabDriverAndAttendant.setVisibility(View.VISIBLE);
            tabBus.setVisibility(View.VISIBLE);
            tabDriverAndAttendant.performClick();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tabFleetOwner:
                layout_fleet_owners_info.setVisibility(View.VISIBLE);
                layout_bus_and_driver_info.setVisibility(View.GONE);
                layout_driver_attendants_info.setVisibility(View.GONE);
                layoutBusInfo.setVisibility(View.GONE);

                fleetIndicator.setVisibility(View.VISIBLE);
                busDriverIndicator.setVisibility(View.INVISIBLE);

                layoutTerms.setVisibility(View.GONE);
                break;

            case R.id.tabBusDriver:
                layout_fleet_owners_info.setVisibility(View.GONE);
                layout_bus_and_driver_info.setVisibility(View.VISIBLE);
                layout_driver_attendants_info.setVisibility(View.GONE);
                layoutBusInfo.setVisibility(View.GONE);

                fleetIndicator.setVisibility(View.INVISIBLE);
                busDriverIndicator.setVisibility(View.VISIBLE);

                layoutTerms.setVisibility(View.VISIBLE);

                break;

            case R.id.tabDriverAndAttendant:

                layout_fleet_owners_info.setVisibility(View.GONE);
                layout_bus_and_driver_info.setVisibility(View.GONE);
                layout_driver_attendants_info.setVisibility(View.VISIBLE);
                layoutBusInfo.setVisibility(View.GONE);

                busDriverAndAttendantIndicator.setVisibility(View.VISIBLE);
                busBusIndicator.setVisibility(View.INVISIBLE);

                if (profileType.equals(Constants.LEASE_VEHICLE))
                    layoutTerms.setVisibility(View.VISIBLE);

                else if (profileType.equals(Constants.DRIVE_WITH_YELOW))
                    layoutTerms.setVisibility(View.GONE);

                break;

            case R.id.tabBus:
                layout_fleet_owners_info.setVisibility(View.GONE);
                layout_bus_and_driver_info.setVisibility(View.GONE);
                layout_driver_attendants_info.setVisibility(View.GONE);
                layoutBusInfo.setVisibility(View.VISIBLE);

                busDriverAndAttendantIndicator.setVisibility(View.INVISIBLE);
                busBusIndicator.setVisibility(View.VISIBLE);

                layoutTerms.setVisibility(View.VISIBLE);
                break;

            case R.id.next:

                if (profileType.equals(Constants.FLEET)) {
                    if (layout_fleet_owners_info.getVisibility() == View.VISIBLE){
                        tabBusDriver.performClick();
                    }
                    else{
                        gotoHome();
                    }
                }
                else if (profileType.equals(Constants.LEASE_VEHICLE)){
                    if (layout_driver_attendants_info.getVisibility() == View.VISIBLE)
                        gotoHome();
                }
                else{
                    if (layout_driver_attendants_info.getVisibility() == View.VISIBLE)
                        tabBus.performClick();
                    else
                        gotoHome();

                }
                break;

            case R.id.tvFleetOwnersCancelledCheque:
                PhotoUploadFragment uploadFragment = new PhotoUploadFragment(fleetOwnersCancelledChequePath,"Owenr's Cancelled Cheque","Upload Cancelled Cheque");
                setFragment((AppCompatActivity)getActivity(),uploadFragment,R.id.signupContainer,uploadFragment.getClass().getSimpleName(),true);
                break;
        }

    }
    public void gotoHome(){
        startActivity(new Intent(getActivity(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        getActivity().finish();

    }
}

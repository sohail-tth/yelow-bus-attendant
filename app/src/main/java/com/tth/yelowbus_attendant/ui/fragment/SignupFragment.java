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


/*  ********************  lease photos textview **************************************************/
    private TextView tvLeaseDriversAadhaarPhoto, tvLeaseDriverPhoto, tvLeaseLicensePhoto, tvLeaseAttendantsAadhaarPhoto,
        tvLeaseAttendantsPhoto, tvLeaseOwnersCancelledCheque;


/*   ******************* bus info photos textview ************************************/
    private TextView tvDriveBusPhoto, tvDriveBusRcPhoto, tvDriveBusPermitPhoto, tvDriveBusinsurancePhoto;


/*  ************** ALL  image paths*/
    public static String fleetOwnersCancelledChequePath="", fleetOwnersAadhaarPath="", fleetOwnersPhotoPath="",
            fleetDriversAadhaarPath="", fleetDriversPhotoPath="", fleetlicensePath="", fleetAttendantsAadhaarPath ="",
            fleetAttendantsPhotoPath="", fleetBusPhotoPath="",


            leaseDriversAadhaarPath="", leaseDriverPhotoPath="", leaseLicensePath="", leaseAttendantsAadhaarPath="",
            leaseAttendantsPhotoPath="", leaseCancelledChequePath ="",

            driveDriversAadhaarPath="", driveDriverPhotoPath="", driveLicensePath="", driveAttendantsAadhaarPath="",
            driveAttendantsPhotoPath="", driveCancelledCheque="",
            driveBusPhotoPath="", driveBusRcPath="", driveBusPermitPath="", driveBusInsurancePath="";

    public static final String FROM_FLEET_CANCELLED_CHEQUE = "fleet_cancelled_cheque";
    public static final String FROM_FLEET_OWNER_AADHAAR = "fleet_owner_aadhaar";
    public static final String FROM_FLEET_OWNER_PHOTO = "fleet_owner_photo";
    public static final String FROM_FLEET_DRIVER_AADHAAR = "fleet_driver_aadhaar";
    public static final String FROM_FLEET_DRIVER_PHOTO = "fleet_driver_photo";
    public static final String FROM_FLEET_LICENSE_PHOTO = "fleet_license_photo";
    public static final String FROM_FLEET_ATTENDANTS_AADHAAR = "fleet_attendants_aadhaar";
    public static final String FROM_FLEET_ATTENDANTS_PHOTO = "fleet_attendants_photo";
    public static final String FROM_FLEET_BUS_PHOTO = "fleet_bus_photo";
    public static final String FROM_LEASE_DRIVERS_AADHAAR = "lease_driver_aadhaar";
    public static final String FROM_LEASE_DRIVER_PHOTO = "lease_driver_photo";
    public static final String FROM_LEASE_LICENSE_PHOTO = "lease_license_photo";
    public static final String FROM_LEASE_ATTENDANT_AADHAAR = "lease_attention";
    public static final String FROM_LEASE_ATTENDANTS_PHOTO = "lease_attendants_photo";
    public static final String FROM_LEASE_CANCELLED_CHEQUE = "lease_cancelled_cheque";

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
        tvFleetOwnersAadharPhoto = view.findViewById(R.id.tvFleetOwnersAadharPhoto);
        tvFleetOwnersPhoto = view.findViewById(R.id.tvFleetOwnersPhoto);
        tvFleetDriversAadhaarPhoto = view.findViewById(R.id.tvFleetDriversAadhaarPhoto);
        tvFleetDriverPhoto = view.findViewById(R.id.tvFleetDriverPhoto);
        tvFleetDriverLicensePhoto = view.findViewById(R.id.tvFleetDriverLicensePhoto);
        tvFleetAttendantsAadhaarPhoto = view.findViewById(R.id.tvFleetAttendantsAadhaarPhoto);
        tvFleetAttendantsPhoto = view.findViewById(R.id.tvFleetAttendantsPhoto);
        tvFleetBusPhoto = view.findViewById(R.id.tvFleetBusPhoto);
        tvLeaseDriversAadhaarPhoto = view.findViewById(R.id.tvLeaseDriversAadhaarPhoto);
        tvLeaseDriverPhoto = view.findViewById(R.id.tvLeaseDriverPhoto);
        tvLeaseLicensePhoto = view.findViewById(R.id.tvLeaseLicensePhoto);
        tvLeaseAttendantsAadhaarPhoto = view.findViewById(R.id.tvLeaseAttendantsAadhaarPhoto);
        tvLeaseAttendantsPhoto = view.findViewById(R.id.tvLeaseAttendantsPhoto);
        tvLeaseOwnersCancelledCheque = view.findViewById(R.id.tvLeaseOwnersCancelledCheque);
        tvDriveBusPhoto = view.findViewById(R.id.tvDriveBusPhoto);
        tvDriveBusRcPhoto = view.findViewById(R.id.tvDriveBusRcPhoto);
        tvDriveBusPermitPhoto = view.findViewById(R.id.tvDriveBusPermitPhoto);
        tvDriveBusinsurancePhoto = view.findViewById(R.id.tvDriveBusinsurancePhoto);





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
        tvFleetOwnersAadharPhoto.setOnClickListener(this);
        tvFleetOwnersPhoto.setOnClickListener(this);
        tvFleetDriversAadhaarPhoto.setOnClickListener(this);
        tvFleetDriverPhoto.setOnClickListener(this);
        tvFleetDriverLicensePhoto.setOnClickListener(this);
        tvFleetAttendantsAadhaarPhoto.setOnClickListener(this);
        tvFleetAttendantsPhoto.setOnClickListener(this);
        tvFleetBusPhoto.setOnClickListener(this);
        tvLeaseDriversAadhaarPhoto.setOnClickListener(this);
        tvLeaseDriverPhoto.setOnClickListener(this);
        tvLeaseLicensePhoto.setOnClickListener(this);
        tvLeaseAttendantsAadhaarPhoto.setOnClickListener(this);
        tvLeaseAttendantsPhoto.setOnClickListener(this);
        tvLeaseOwnersCancelledCheque.setOnClickListener(this);
        tvDriveBusPhoto.setOnClickListener(this);
        tvDriveBusRcPhoto.setOnClickListener(this);
        tvDriveBusPermitPhoto.setOnClickListener(this);
        tvDriveBusinsurancePhoto.setOnClickListener(this);


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
                PhotoUploadFragment uploadFragment = new PhotoUploadFragment(fleetOwnersCancelledChequePath,"Owner's Cancelled Cheque","Upload Cancelled Cheque",FROM_FLEET_CANCELLED_CHEQUE);
                setFragment((AppCompatActivity)getActivity(),uploadFragment,R.id.signupContainer,uploadFragment.getClass().getSimpleName(),true);
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

            case R.id.tvLeaseDriversAadhaarPhoto:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseDriversAadhaarPath,"Driver's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_LEASE_DRIVERS_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveDriversAadhaarPath,"Driver's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_DRIVE_DRIVERS_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLeaseDriverPhoto:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseDriverPhotoPath,"Driver's Photo","Upload Driver's Photo",FROM_LEASE_DRIVER_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveDriverPhotoPath,"Driver's Photo","Upload Driver's Photo",FROM_DRIVE_DRIVER_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLeaseLicensePhoto:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseLicensePath,"License Photo","Upload License's Photo",FROM_LEASE_LICENSE_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveLicensePath,"License Photo","Upload License's Photo",FROM_DRIVE_LICENSE_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLeaseAttendantsAadhaarPhoto:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseAttendantsAadhaarPath,"Attendant's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_LEASE_ATTENDANT_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveAttendantsAadhaarPath,"Attendant's Aadhaar Card Photo","Upload Aadhaar Card Photo",FROM_DRIVE_ATTENDANT_AADHAAR),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLeaseAttendantsPhoto:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseAttendantsPhotoPath,"Attendant's Photo","Upload Attendant's Photo",FROM_LEASE_ATTENDANTS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveAttendantsPhotoPath,"Attendant's Photo","Upload Attendant's Photo",FROM_LEASE_ATTENDANTS_PHOTO),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                break;

            case R.id.tvLeaseOwnersCancelledCheque:
                if (profileType.equals(Constants.LEASE_VEHICLE))
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(leaseCancelledChequePath,"Cancelled Cheque","Upload Cancelled Cheque",FROM_LEASE_CANCELLED_CHEQUE),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
                else
                    setFragment((AppCompatActivity)getActivity(),new PhotoUploadFragment(driveCancelledCheque,"Cancelled Cheque","Upload Cancelled Cheque",FROM_DRIVE_CANCELLED_CHEQUE),R.id.signupContainer,PhotoUploadFragment.class.getSimpleName(),true);
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


        }

    }
    public void gotoHome(){
        startActivity(new Intent(getActivity(), HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
        getActivity().finish();

    }
}

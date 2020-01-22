package com.tth.yelowbus_attendant.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {


    public static final String PREF_NAME =  "myPref";
    public static final String USER_PROFILE_TYPE = "user_profile_type";

    private final String REGISTERED = "registered";
    private final String EMAIL_VERIFIED = "email_verified";
    private final String APPROVED_BY_ADMIN = "approved_by_admin";
    private final String ROUTE_DEFINED  = "route_defined";
    private final String IS_LOGGED_IN = "is_logged_in";


    public  static  SharedPreferences sharedPreferences;
    Context context;


    public static Preference getInstance(Context context){
       return new Preference(context);
    }

    public Preference(Context context){
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public void setUserProfileType(String profileType){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PROFILE_TYPE, profileType);
        editor.apply();
    }

    public String getUserProfileType(){
        return sharedPreferences.getString(USER_PROFILE_TYPE,"");
    }


    public void setREGISTERED(boolean registered){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(REGISTERED, registered);
        editor.apply();
    }
    public boolean isREGISTERED(){
        return sharedPreferences.getBoolean(REGISTERED, false);
    }

    public void setEMAIL_VERIFIED(boolean verified){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(EMAIL_VERIFIED, verified);
        editor.apply();
    }
    public boolean isEMAIL_VERIFIED(){
        return sharedPreferences.getBoolean(EMAIL_VERIFIED, false);
    }

    public void setAPPROVED_BY_ADMIN(boolean approved){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(APPROVED_BY_ADMIN, approved);
        editor.apply();
    }
    public boolean isAPPROVED_BY_ADMIN(){
        return sharedPreferences.getBoolean(APPROVED_BY_ADMIN, false);
    }
    public void setROUTE_DEFINED(boolean routeDefined){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(ROUTE_DEFINED, routeDefined);
        editor.apply();
    }
    public boolean isROUTE_DEFINED(){
        return sharedPreferences.getBoolean(ROUTE_DEFINED, false);
    }

    public void setIS_LOGGED_IN(boolean logged_in){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGGED_IN, logged_in);
        editor.apply();
    }
    public boolean isLOGGED_IN(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

}

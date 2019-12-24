package com.tth.yelowbus_attendant.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {


    public static final String PREF_NAME =  "myPref";
    public static final String USER_PROFILE_TYPE = "user_profile_type";


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



}

package com.tth.yelowbus_attendant.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST(APIList.ADD_DRIVE_WITH_YELOW)
    Call<JsonObject> addDriveWithYelow(@Body JsonObject json);

    @POST(APIList.GET_SCHOOLS)
    Call<JsonObject> getSchools(@Body JsonObject json);

    @Headers("Content-Type:application/json")
    @POST(APIList.UPLOAD_FILE)
    Call<JsonObject> uploadFile(@Body JsonObject json);

}

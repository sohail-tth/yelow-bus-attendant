package com.tth.yelowbus_attendant.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {

    @POST(APIList.ADD_DRIVE_WITH_YELOW)
    Call<JsonObject> addDriveWithYelow(@Body JsonObject json);

    @POST(APIList.ADD_FLEET_REGISTER)
    Call<JsonObject> addFleetRegister(@Body JsonObject json);

    @POST(APIList.GET_SCHOOLS)
    Call<JsonObject> getSchools(@Body JsonObject json);

    @Headers("Content-Type:application/json")
    @POST(APIList.UPLOAD_FILE)
    Call<JsonObject> uploadFile(@Body JsonObject json);

    @POST(APIList.LOGIN)
    Call<JsonObject> login(@Body JsonObject json);

    @POST(APIList.GET_ALL_PICKUP_ROUTE_DATA)
    Call<JsonObject> getAllPickupRoutes(@Body JsonObject json);

    @POST(APIList.GET_ALL_DROP_ROUTE_DATA)
    Call<JsonObject> getAllDropRoutes(@Body JsonObject json);

}

package com.tth.yelowbus_attendant.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleAPI {

    String BASE_URL = "https://maps.googleapis.com";

    @GET("/maps/api/directions/json")
    Call<JsonObject> getDirection(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("key") String key,
            @Query("mode") String mode,
            @Query("alternatives") String alternatives
    );

    @GET("/maps/api/geocode/json")
    Call<JsonObject> geocode(@Query("latlng") String latlng,
                             @Query("key") String key
    );

    @GET("/maps/api/place/autocomplete/json")
    Call<JsonObject> placeAutoComplete(
            @Query("input") String input,
            @Query("components") String components,
            @Query("sensor") String sensor,
            @Query("key") String key
    );


}

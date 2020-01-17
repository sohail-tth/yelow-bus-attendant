package com.tth.yelowbus_attendant.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.tth.yelowbus_attendant.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APICaller {

    APIListener apiListener;


    public void callAPI(final Context context, Call<JsonObject> call, String progressMessage){

        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(progressMessage);

        if (Util.isNetworkAvailable(context)){
            progressDialog.show();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()){
                        apiListener.onSuccess(response.body());
                    }
                    else {
                        apiListener.onError(response);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    progressDialog.dismiss();
                    apiListener.onException(t);
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            apiListener.noInternetConnection();
        }

    }


    public APIListener getApiListener() {
        return apiListener;
    }

    public void setApiListener(APIListener apiListener) {
        this.apiListener = apiListener;
    }

    public interface APIListener{
        void onSuccess(JsonObject jsonObject);
        void onError(Response<JsonObject> response);
        void onException(Throwable t);
        void noInternetConnection();
    }

}

package com.tth.yelowbus_attendant.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Date;

public class Util {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getDayOfWeek(Date date){
        String day = "";
        switch (date.getDay()){
            case 0: day =  "Sunday";break;
            case 1: day =  "Monday";break;
            case 2: day =  "Tuesday";break;
            case 3: day =  "Wednesday";break;
            case 4: day =  "Thursday";break;
            case 5: day =  "Friday";break;
            case 6: day =  "Saturday";break;
        }
        return day;
    }

    public static String getShortMonthName(Date date){
        String month = "";
        switch (date.getMonth()){
            case 0: month = "Jan"; break;
            case 1: month = "Feb"; break;
            case 2: month = "Mar"; break;
            case 3: month = "Apr"; break;
            case 4: month = "May"; break;
            case 5: month = "Jun"; break;
            case 6: month = "Jul"; break;
            case 7: month = "Aug"; break;
            case 8: month = "Sep"; break;
            case 9: month = "Oct"; break;
            case 10: month = "Nov"; break;
            case 11: month = "Dec"; break;
        }
        return month;
    }

    public static String getOrdinalNumber(int n){
        String suffix = "";
        if (4 <= n && n <= 20){
            suffix = "th";
        }
        else if (n == 1 || (n%10) == 1){
            suffix = "st";
        }
        else if (n == 2 || (n%10) == 2){
            suffix = "nd";
        }
        else if (n == 3 || (n%10) == 3){
            suffix = "rd";
        }
        else if (n < 100){
            suffix = "th";
        }
        return String.valueOf(n).concat(suffix);
    }

    public static String toDDMMYYYY(int dd, int mm, int yyyy){
        mm++;
        if (dd < 10){
            return mm < 10 ? "0"+dd+"/0"+(mm)+"/"+yyyy : "0"+dd+"/"+mm+"/"+yyyy;
        }
        else {
            return mm < 10 ? dd+"/0"+(mm)+"/"+yyyy : dd+"/"+mm+"/"+yyyy;
        }

    }

    public static String imageToBase64(String filePath){
        String encodedImage = null;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            encodedImage = Base64.encodeToString(bytes,Base64.DEFAULT);
            encodedImage = encodedImage.replaceAll("[\\n]", "");
            return encodedImage;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Bitmap base64ToImage(String encodedString){
        byte[] decodedString = Base64.decode(encodedString, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static String getExtension(String filePath){
        try {
            return filePath.substring(filePath.lastIndexOf('.')+1);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}

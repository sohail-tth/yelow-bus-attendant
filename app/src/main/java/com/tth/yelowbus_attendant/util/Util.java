package com.tth.yelowbus_attendant.util;

import java.util.Date;

public class Util {

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
}

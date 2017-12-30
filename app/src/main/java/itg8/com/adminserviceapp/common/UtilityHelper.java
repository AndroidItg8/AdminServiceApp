package itg8.com.adminserviceapp.common;


import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import itg8.com.adminserviceapp.R;


/**
 * Created by Android itg 8 on 10/11/2017.
 */

public class UtilityHelper {


    private static final String TAG = UtilityHelper.class.getSimpleName();
    SimpleDateFormat simpleDateFormate = new SimpleDateFormat("dd/mm/yyyy");
    public static SimpleDateFormat dateFormat = new SimpleDateFormat(CommonMethod.DATE_FORMAT, Locale.getDefault());


    static UtilityHelper helper;

    public static UtilityHelper getInstance() {
        if (helper == null) {
            helper = new UtilityHelper();
        }
        return helper;

    }


    public static String getDateFromMillies(long millies) {
        String newDate = "";
        try {
            Calendar calendar= Calendar.getInstance();
            calendar.setTimeInMillis(millies);
            newDate = dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getCurrentDate() {
        String newDate = "";
        try {
            newDate = dateFormat.format(Calendar.getInstance().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }




    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }





    public static long daysBetween(Date one, Date two) {
        long difference = (one.getTime()-two.getTime())/86400000;
        Log.d(TAG,"daysBetween"+ difference);
        return difference;
    }

    public Date convertStringToDate(String s, int monthToAdd) {
        DateFormat formatter ;
        Date date = null;
        formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS", Locale.getDefault());
        try {

            date = formatter.parse(s);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            if(monthToAdd>0)
                calender.add(Calendar.MONTH,monthToAdd);
            else
                calender.add(Calendar.YEAR,1);
            date = calender.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public Date getTodaysDate() {
        DateFormat formatter ;
        Date currentDate= null;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        currentDate = Calendar.getInstance().getTime();
        simpleDateFormate.format(currentDate);
        return currentDate;
    }



}

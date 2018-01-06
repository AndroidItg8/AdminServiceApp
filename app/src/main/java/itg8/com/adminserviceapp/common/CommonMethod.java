package itg8.com.adminserviceapp.common;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Android itg 8 on 10/7/2017.
 */

public class CommonMethod {

    public static final String SHARED = "SHARED";
    //    public static final String BASE_URL = "http://192.168.1.54:8080";
//    public static final String BASE_URL = "http://itechservices.itechgalaxyprojects.com";
    public static final String BASE_URL = "http://itechservices.itechgalaxyprojects.com";


    public static final String HEADER = "QsSBetVvJt2ld9vLLL067EwLO4KowfVefUPHtwWGi1amq2pl9ByH2A-hJG_jFe4ENMq3QJWyNJnkZRXzijEDz6YnuIZG1Qg0PgOM2oEEOi9zHWxHqlwdQaIHkBAh0_8OYfNyUnewowRNVMziYFNYBX7GJgB7baQVRNeKwX_wB-AHxBAWMQ1Biw4XnpRic9WFWx5IjvUYfNuKr8N-9QzkQZz3Oetv4md5RjEVtE-Qd-1FfuEy1oPz7bqYqX9AFYMiuWw-U4Bpecja1oShIFRXQba7CfUm0i2ax8ZJVfLIHc8RoPVxsov9RboQEYPObmE7kGQTbiscE-MZ1C2ZblFpeCFQZLOeL7EC3nxLf_h9t3egB0D9U-HTH33vmecBHfl6c2pKgttotWfreze6JWST_375p9fKeBdvHv5UeoO6nYSChri4nzRkql9Ae5LL2ylvJrfMV8AfjT1ZTuJONVCIWo1M45WLZos1K6M7e6qOJzwWo6hXvXdpd6CFeLXIU9fWEgUR-lFy4rOCGzZGY5gNyQ";
    public static final java.lang.String DATE_FORMAT = "dd/mm/yyyy";
    public static final int FROM_ACCEPT = 0;
    public static final int FROM_PENDING = 1;
    public static final int FROM_REJECT = 2;
    public static final int FROM_ERROR = 0;
    /**
     * Open Means Pending Ticket List
     * Assign Means Accept Ticket List
     * Close Means Closed Ticket List
     **/
    public static final int TICKET_STATUS_OPEN = 0;
    public static final int TICKET_STATUS_ASSIGN = 1;
    public static final int TICKET_STATUS_CLOSE = 2;
    public static final int TICKET_STATUS_ALL = 10;

    public static final int FROM_INTERNET = 1;
    public static final String TENDER = "TENDER";
    public static final String SALES_PERSON = "SALES_PERSON";
    public static final String FROM = "FROM";
    public static final String FROM_SUBMITED = "FROM_SUBMITED";
    public static final String FROM_PENDINGS = "FROM_PENDINGS";
    public static final String TENDER_DOCUMENT = "TENDER_DOCUMENT";
    public static final String TICKET = "ticket";
    public static final int FROM_ASSIGN = 2;
    public static final String from = "from";
    public static final String FBASE_TOKEN = "FBASE_TOKEN";
    public static final String ENQUIRY = "ENQUIRY";
    public static final String FROM_NOTIFICATION = "FROM_NOTIFICATION";
    public static final String FEEDBACK = "FEEDBACK";
    public static final String FROM_ACTIVITY = "FROM_ACTIVITY";
    public static final String FOLDER_NAME = "AdminService";
    private static Typeface typeface;
    private Context context;

    public CommonMethod(Context context) {
        this.context = context;
    }


    public static Calendar ConvertStringToDate(String assignDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(formatter.parse(assignDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String finalString = formatter.format(date);
        return date;
    }

    public static Calendar ConvertStringToDateWithoutMillies(String assignDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Calendar date = Calendar.getInstance();
        try {
            date.setTime(formatter.parse(assignDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String finalString = formatter.format(date);
        return date;
    }

    public static String DiffBetweenDates(String assignDate) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD");
        Calendar.getInstance().getTime();
        Date date = null;
        try {
            date = (Date) formatter.parse(assignDate);
            String CurrentDate = formatter.format(Calendar.getInstance().getTime());
            date = (Date) formatter.parse(CurrentDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        String finalString = formatter.format(date);
        return finalString;
    }


    public static String getFormattedDateTime(String assigndate) {
        Calendar calendar = convertStringToDate(assigndate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.getDefault());
        String convertedDate = "";

        try {
            convertedDate = sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static Calendar convertStringToDate(String assignDate) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());


        Calendar date = Calendar.getInstance();
        try {
            date.setTime(formatter.parse(assignDate));

        } catch (ParseException | NullPointerException e) {
            e.printStackTrace();
        }


//        String finalString = formatter.format(date);
        return date;
    }
    public static String currentDate() {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss", Locale.getDefault());


      return    formatter.format(Calendar.getInstance().getTimeInMillis());



    }


    public static void showHideItem(View show, View hide) {
        show.setVisibility(View.VISIBLE);
        hide.setVisibility(View.GONE);

    }

    public static String checkEmpty(String s) {
        if (s != null)
            return s;
        return "NOT AVAILABLE";
    }

    public static String checkNull(String s) {
        if (s != null && !TextUtils.isEmpty(s))
            return s;
        return "";
    }

    public static String checkProfile(String s) {
        if (s != null)
            return s;
        return "UNKNOWN USER";
    }

    public static boolean checkNull(List product) {
        return !(product == null || product.size() <= 0);
    }

    public static void sendRegistrationToServer(final String refreshedToken, Context context) {
        if (Prefs.getString(CommonMethod.HEADER, null) != null) {
            JsonObject object = new JsonObject();
            object.setToken(refreshedToken);
            Call<StatusModel> statusModelCall;
            statusModelCall = AppApplication.getInstance().getRetroController().sendFirebaseTokenToServer(context.getString(R.string.url_firebase_token), object);
            statusModelCall.enqueue(new Callback<StatusModel>() {
                @Override
                public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                    if (response.isSuccessful()) {
                        if (response.body().isFlag()) {
                            Prefs.putString(FBASE_TOKEN,refreshedToken);
                            Logs.d("OnSuccess" + response.body().getStatus());
                        } else {
                            Logs.d("OnFailed" + response.body().getStatus());

                        }

                    } else {
                        Logs.d("OnFailed" + response.body().getStatus());

                    }
                }

                @Override
                public void onFailure(Call<StatusModel> call, Throwable t) {
                    t.printStackTrace();
                    Logs.d("OnFailed" + t.getMessage());

                }
            });

        }
    }

    public static float checkRating(int rating) {
        if(rating>0)
        return rating;
        else
            return 0;
    }

    public static class JsonObject {
        @SerializedName("Token")
        private String token;

        public JsonObject() {
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}

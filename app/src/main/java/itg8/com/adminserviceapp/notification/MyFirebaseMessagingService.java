package itg8.com.adminserviceapp.notification;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.enquiry.EnquiryActivity;
import itg8.com.adminserviceapp.enquiry.EnquiryDetailsActivity;
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;

import itg8.com.adminserviceapp.ticket.model.Cust;
import itg8.com.adminserviceapp.ticket.model.TicketModel;


/**
 * Created by itg_Android on 12/3/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private static final int NOTIFICATION_ID = 101;
    private static final String TITLE = "title";
    private static final String EnquiryMaster = "EnquiryMaster";
    private static final String TicketMaster = "TicketMaster";
    private static final String TenderMaster = "TicketMaster";
    private static final String CHANNEL_ID = "CHANNEL_ID" ;
    private static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    private String message = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
            getDataFromRemoteMessage(remoteMessage.getData());
        }

        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());


    }

    private void getDataFromRemoteMessage(Map<String, String> body) {
        List<Cust> modelList = new ArrayList<>();

//        {
//            "Contactno": "98765432190",
//                "Description": "Query this is",
//                "lastmodifieddate": "2018-01-06T12:49:03.653",
//                "mdate": null,
//                "mid": null,
//                "pkid": 41,
//                "product_fkid": 2,
//                "Product": [
//            {
//                "pkid": 2,
//                    "brandid": 1,
//                    "categoryid": 2,
//                    "ItemName": "Dome Alpha 154",
//                    "group_fkid": 2,
//                    "Unit": 1,
//                    "openStock": "145.02",
//                    "Salesprice": 12.2,
//                    "purchaseprice": 10.2,
//                    "MRP": 12.2,
//                    "min_salesprice": 12.2,
//                    "self_price": null,
//                    "HSNCode": "AST54564646546",
//                    "lastModifieddate": null,
//                    "prodctImage": null,
//                    "WarrantyInMonth": null,
//                    "mid": null,
//                    "mdate": null
//            }
//        ],
//            "Query": "Query this is ",
//                "Status": null,
//                "user_fkid": "f9271169-8f22-40c5-bce4-1e7487970639",
//                "Cust": [
//            {
//                "pkid": 32,
//                    "User_fkid": "f9271169-8f22-40c5-bce4-1e7487970639",
//                    "Company_fkid": 1,
//                    "customer_Name": "Itech Admin",
//                    "AddressLine1": null,
//                    "addressLine2": null,
//                    "AddressLine3": null,
//                    "PrintName": null,
//                    "Group_fkid": null,
//                    "openBalance": null,
//                    "previousYearBalance": null,
//                    "State": null,
//                    "Fax": null,
//                    "Telephoneno": null,
//                    "mobileno": null,
//                    "email": "admin@admin.com",
//                    "contactPersonName": null,
//                    "AdharNumber": null,
//                    "LastModifiedDate": null,
//                    "mid": null,
//                    "mdate": null,
//                    "cid": null,
//                    "cdate": null,
//                    "GSTNumber": null,
//                    "UserRole": "SuperAdmin",
//                    "ActualEmail": null,
//                    "Firstlogin": 1,
//                    "UserPIC": null
//            }
//        ]

//        product_fkid:2
//        Contactno:65354634
//        Query:dfgvdfcgfbdf
//        },

        if (body.get("type").equalsIgnoreCase(EnquiryMaster)) {
            try {
                JSONObject jsonObject = new JSONObject(body);
                if (jsonObject.has("values")) {
                    String values = jsonObject.getString("values");
                    Logs.d("Values:" + new Gson().toJson(values));

//                    JSONArray jsonArray = new JSONArray(values);
//                    EnquiryModel model = new EnquiryModel();
//                    JSONObject jsonObjectM = jsonArray.getJSONObject(0);
//                    model.setStatus(jsonObjectM.getString("Status"));
//                    model.setContactno(jsonObjectM.getString("Contactno"));
//                    model.setProductFkid(jsonObjectM.getInt("product_fkid"));
//
//                    model.setDescription(jsonObjectM.getString("Description"));
//                    model.setQuery(jsonObjectM.getString("Query"));
//                    Cust custModel = new Cust();
//                    custModel.setMobileno(jsonObjectM.getString("Contactno"));
//                    custModel.setCustomerName(jsonObjectM.getString("customer_Name"));
//                    custModel.setAddressLine1(jsonObjectM.getString("AddressLine1"));
//                    custModel.setAddressLine2(jsonObjectM.getString("addressLine2"));
//                    custModel.setAddressLine3(jsonObjectM.getString("AddressLine3"));
//
//
//                    custModel.setPrintName(jsonObjectM.getString("PrintName"));
//                    custModel.setGroupFkid(jsonObjectM.getString("Group_fkid"));
//                    custModel.setOpenBalance(jsonObjectM.getString("openBalance"));
//                    custModel.setPreviousYearBalance(jsonObjectM.getString("previousYearBalance"));
//                    custModel.setState(jsonObjectM.getString("State"));
//                    custModel.setFax(jsonObjectM.getString("Fax"));
//                    custModel.setTelephoneno(jsonObjectM.getString("Telephoneno"));
//                    custModel.setTelephoneno(jsonObjectM.getString("Telephoneno"));
//
//                    custModel.setMobileno(jsonObjectM.getString("mobileno"));
//                    custModel.setEmail(jsonObjectM.getString("email"));
//                    custModel.setContactPersonName(jsonObjectM.getString("contactPersonName"));
//                    custModel.setAdharNumber(jsonObjectM.getString("AdharNumber"));
//                    custModel.setLastModifiedDate(jsonObjectM.getString("LastModifiedDate"));
//                    custModel.setTelephoneno(jsonObjectM.getString("Telephoneno"));

//                    modelList.add(custModel);
//                    model.setCust(modelList);


                    List<EnquiryModel> enquiryModel = new Gson().fromJson(values, new TypeToken<List<EnquiryModel>>() {
                    }.getType());
//
                    Logs.d(TAG, "Enquiry Model:" + new Gson().toJson(enquiryModel));

                  //  sendNotifications(enquiryModel.get(0));

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(body.get("type").equalsIgnoreCase(TicketMaster))
        {
            JSONObject jsonObject = new JSONObject(body);
            if (jsonObject.has("values")) {
                String values = null;
                try {
                    values = jsonObject.getString("values");
                    List<TicketModel> ticketyModel = new Gson().fromJson(values, new TypeToken<List<TicketModel>>() {
                    }.getType());
                    TicketModel model = ticketyModel.get(0);
                    TicketModel models = new TicketModel();
                    models.setProblem(model.getProblem());
                    models.setProduct(model.getProduct());
                    models.setDescription(model.getDescription());
                    models.setCust(model.getCust());
                    models.setAssignedContactno(model.getAssignedContactno());
                    models.setAssignedpersonname(model.getAssignedpersonname());




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            }

        }





    private void sendNotifications(EnquiryModel model) {
        NotificationCompat.Builder mBuilder=null;
        NotificationChannel channel = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT);
           mBuilder =
                    new NotificationCompat.Builder(this,CHANNEL_ID)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Title")
                            .setChannelId(CHANNEL_ID)
                            .setContentText("Message");
        }else{
             mBuilder =
                    new NotificationCompat.Builder(this)
                            .setAutoCancel(true)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("Title")
                            .setContentText("Message");
        }

        Intent intent = new Intent(this, EnquiryDetailsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(CommonMethod.FROM_NOTIFICATION, model);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(EnquiryActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (mNotificationManager != null) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                if (channel != null) {
                    mNotificationManager.createNotificationChannel(channel);
                }
            }
            mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
        }
    }



//    private void sendEnquiryNotification(EnquiryModel model)
//    {
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
//
//            // Configure the notification channel.
//            notificationChannel.setDescription("Channel description");
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//            notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
//
//        notificationBuilder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker("Hearty365")
//                //     .setPriority(Notification.PRIORITY_MAX)
//                .setContentTitle("Default notification")
//                .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
//                .setContentInfo("Info");
//
//        if (notificationManager != null) {
//            notificationManager.notify(/*notification id*/NOTIFICATION_ID, notificationBuilder.build());
//        }
//    }

}


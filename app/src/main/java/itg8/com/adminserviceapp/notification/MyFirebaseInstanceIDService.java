package itg8.com.adminserviceapp.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Prefs;


/**
 * Created by itg_Android on 12/3/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    public static final String REGISTRATION_SUCCESS = "RegistrationSuccess";
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed :" + refreshedToken);
        Log.d(TAG, "Refreshed :" + refreshedToken);
        CommonMethod.sendRegistrationToServer(refreshedToken,getApplicationContext());
    }


}

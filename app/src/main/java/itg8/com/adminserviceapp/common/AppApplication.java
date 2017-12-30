package itg8.com.adminserviceapp.common;

import android.app.Application;

import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;


@ReportsCrashes(formUri = "", mailTo = "app.itechgalaxy@gmail.com", mode = ReportingInteractionMode.SILENT)
public class AppApplication extends Application {

    private static final String PROFILE_MODEL = "PROFILE_MODEL";
    private static final String SHARED = "SHARED";
    private static final String TAG = AppApplication.class.getSimpleName();
    private static AppApplication mInstance;
    private RetroController retroController;


    public static synchronized AppApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //  ACRA.init(this);
        mInstance.initPreference();
        retroController = mInstance.buildRetrofit();

    }

    private void initPreference() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(MODE_PRIVATE)
                .setPrefsName(SHARED)
                .setUseDefaultSharedPreference(false)
                .build();
    }

    private RetroController buildRetrofit() {
//         HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//         interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//         OkHttpClient.Builder builder = new OkHttpClient.Builder();
//         builder.connectTimeout(5, TimeUnit.MINUTES);
//         builder.addInterceptor(interceptor);
//         builder.readTimeout(5, TimeUnit.MINUTES);
//         if(header!=null)
//             builder.addInterceptor(getHeader(header));
//
//         OkHttpClient client=builder.build();
//         Gson gson = new GsonBuilder().setLenient().create();
//
//         Retrofit retrofit = new Retrofit.Builder()
//                 .baseUrl(CommonMethod.BASE_URL)
//                 .addConverterFactory(GsonConverterFactory.create(gson))
//                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                 .client(client)
//                 .build();

        return Retro.getInstance().getController(Prefs.getString(CommonMethod.HEADER, null), getApplicationContext());
    }


    public RetroController getRetroController() {
        if (retroController == null)
            retroController = buildRetrofit();

        return retroController;

    }

    public void resetRetroAfterLogin() {
        retroController = null;
    }


}

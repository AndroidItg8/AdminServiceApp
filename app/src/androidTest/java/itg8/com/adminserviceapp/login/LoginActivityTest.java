package itg8.com.adminserviceapp.login;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.Prefs;
import itg8.com.adminserviceapp.login.mvp.LoginMvp;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

/**
 * Created by Android itg 8 on 12/16/2017.
 */
@RunWith(AndroidJUnit4.class)

public class LoginActivityTest {

     LoginMvp.LoginView view ;
     LoginMvp.LoginPresenter presenter;
     AppApplication appApplication;


    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);


    @Before
    public void setUp(){
     appApplication.getRetroController();

    }
    @Test
    public void checkEmailEmpty()
    {
        onView(withId(R.id.edt_userName)).check(matches(not(isDisplayed())));

    }




}
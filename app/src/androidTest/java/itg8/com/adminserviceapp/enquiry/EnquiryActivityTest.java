package itg8.com.adminserviceapp.enquiry;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onView;
import static org.junit.Assert.assertEquals;

import android.support.test.espresso.contrib.RecyclerViewActions;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import itg8.com.adminserviceapp.R;


/**
 * Created by Android itg 8 on 12/16/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EnquiryActivityTest {
    @Rule
    public ActivityTestRule<EnquiryActivity> mActivityTestRule = new ActivityTestRule<EnquiryActivity>(EnquiryActivity.class);


    @Test
    public void onRecyclerView(){
//        onView(ViewMatchers.withId(R.id.recyclerView))

//                .perform(RecyclerViewActions.scrollToPosition(5));
//        onView(ViewMatchers.withId(R.id.recyclerView)).check()

        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("itg8.com.adminserviceapp", appContext.getPackageName());


    }





}
package com.itexico.instrumentationtesting.junit4rules;

/**
 * Created by iTexico Developer on 8/31/2016.
 */


import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.MediumTest;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.BoundServiceActivity;
import com.itexico.instrumentationtesting.espresso.service.BoundService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertTrue;

/**
 * JUnit4 test that uses a {@link ServiceTestRule} to interact with a bound service.
 * <p/>
 * {@link ServiceTestRule} is a JUnit rule that provides a
 * simplified mechanism to start and shutdown your service before
 * and after the duration of your test. It also guarantees that the service is successfully
 * connected when starting (or binding to) a service. The service can be started
 * (or bound) using one of the helper methods. It will automatically be stopped (or unbound) after
 * the test completes and any methods annotated with
 * <a href="http://junit.sourceforge.net/javadoc/org/junit/After.html"><code>After</code></a> are
 * finished.
 * <p/>
 * Note: This rule doesn't support {@link android.app.IntentService} because it's automatically
 * destroyed when {@link android.app.IntentService#onHandleIntent(Intent)} finishes
 * all outstanding commands. So there is no guarantee to establish a successful connection
 * in a timely manner.
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class BoundServiceTest {

    @Rule
    public ActivityTestRule<BoundServiceActivity> mActivityRule = new ActivityTestRule<>(
            BoundServiceActivity.class);

    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void bindService() {
        // Create the service Intent.
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(), BoundService.class);
        BoundService.BoundServiceBinder boundServiceBinder =null;
        try {
            boundServiceBinder = (BoundService.BoundServiceBinder) mServiceRule.bindService(serviceIntent);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //get service instance.

        onView(withId(R.id.action_started_service_update_list_button)).perform(click());

        final BoundService boundService = boundServiceBinder.getService();
        List<String> list = boundService.getUpdatedList();
        assertTrue("ServiceTest is failed !!!",list.size() > 0);
    }

}

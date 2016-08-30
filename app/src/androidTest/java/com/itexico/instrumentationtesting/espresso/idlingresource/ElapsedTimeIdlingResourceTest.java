package com.itexico.instrumentationtesting.espresso.idlingresource;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.format.DateUtils;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.IdlingElapsedTime;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ElapsedTimeIdlingResourceTest {

    private static final long EIGHT_MILLS = 8;
    private static final long SEVENTY_FIVE_MILLIS = 75;


    @Rule
    public ActivityTestRule<IdlingElapsedTime> mActivityRule = new ActivityTestRule<>(
            IdlingElapsedTime.class);


    @Test
    public void waitFor8Seconds() {
        waitFor(DateUtils.SECOND_IN_MILLIS * EIGHT_MILLS, false);
    }

    @Test
    public void waitFor75Seconds() {
        waitFor(DateUtils.SECOND_IN_MILLIS * SEVENTY_FIVE_MILLIS, true);
    }


    private void waitFor(long waitingTime, boolean success) {
        onView(withId(R.id.toggle_button)).check(matches(withText(R.string.toggle_button_start))).perform(click());

        //Make sure Espresso does not time out.
        IdlingPolicies.setMasterPolicyTimeout(2 * waitingTime, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(2 * waitingTime, TimeUnit.MILLISECONDS);

        //Now we wait..
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(waitingTime);
        Espresso.registerIdlingResources(idlingResource);

        //Stop and verify.
        onView(withId(R.id.toggle_button)).check(matches(withText(R.string.toggle_button_stop))).perform(click());

        onView(withId(R.id.result)).check(matches(withText(success ? R.string.success : R.string.failure)));
    }

}

package com.itexico.instrumentationtesting.espresso.failurehandler;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.CustomFailureHandler;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class CustomFailureHandlerTest {

    @Rule
    public ActivityTestRule<CustomFailureHandler> mActivityRule = new ActivityTestRule<>(
            CustomFailureHandler.class);

    @Before
    public void setUp() {
        Espresso.setFailureHandler(new ScreenShotFailureHandler(mActivityRule.getActivity()));
    }

    @Test
    public void validateText() {
        //It will fail and generate screenshot in cache.
        onView(withId(R.id.custom_failure_handler_status)).check(doesNotExist());
    }
}

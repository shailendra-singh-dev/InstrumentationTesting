package com.itexico.instrumentationtesting.espresso;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.IdlingIntentServiceActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/29/2016.
 */
public class IntentServiceTest {

    private static final String TYPED_TEXT = "Repeat";
    private static final String MATCHED_TEXT = TYPED_TEXT + " " + TYPED_TEXT + " " + TYPED_TEXT + " " + TYPED_TEXT + " " + TYPED_TEXT;

    private IntentServiceIdlingResource mIntentServiceIdlingResource;

    @Rule
    public ActivityTestRule<IdlingIntentServiceActivity> mActivityRule = new ActivityTestRule<>(
            IdlingIntentServiceActivity.class);


    @Before
    public void registerIntentServiceIdlingResources() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        mIntentServiceIdlingResource = new IntentServiceIdlingResource(instrumentation.getTargetContext());
        Espresso.registerIdlingResources(mIntentServiceIdlingResource);
    }

    @Test
    public void repeatService() {
        onView(withId(R.id.repeat_service_edit_text)).perform(typeText(TYPED_TEXT));
        onView(withId(R.id.repeat_service_buttton)).perform(click());
        onView(withId(R.id.repeat_service_status)).check(matches(withText(MATCHED_TEXT)));
    }

    @After
    public void unRegisterIntentServiceIdlingResources() {
        Espresso.unregisterIdlingResources(mIntentServiceIdlingResource);
    }
}


package com.itexico.instrumentationtesting.espresso;

import android.content.pm.ActivityInfo;
import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.ChangeText;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class DeviceRotationTest {

    private static final String TYPE_TEXT = "Hello";

    @Rule
    public ActivityTestRule<ChangeText> mActivityRule = new ActivityTestRule<>(
            ChangeText.class);

    @Test
    public void checkTextWhenDeviceRotates() {
        onView(withId(R.id.text_user_input)).perform(typeText(TYPE_TEXT));
        mActivityRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        onView(withId(R.id.text_user_input)).check(matches(withText(TYPE_TEXT)));
    }
}

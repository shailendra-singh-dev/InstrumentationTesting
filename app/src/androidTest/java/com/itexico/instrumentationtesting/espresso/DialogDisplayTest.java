package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.DialogDisplay;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class DialogDisplayTest {

    @Rule
    public ActivityTestRule<DialogDisplay> mActivityRule = new ActivityTestRule<>(
            DialogDisplay.class);

    @Test
    public void openDialog() {
        onView(withId(R.id.open_dialog)).perform(click());
        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()));
    }

    @Test
    public void clickDialogPositiveButton() {
        //Open dialog
        onView(withId(R.id.open_dialog)).perform(click());
        //Click on "OK" button android.R.id.button1
        onView(withId(android.R.id.button1)).perform(click());
        //Check whether Textview with Id open_dialog_button_click_status is updated or not.
        onView(withId(R.id.open_dialog_button_click_status)).check(matches(withText(R.string.dialog_positive_button_title)));
    }

    @Test
    public void clickDialogNegativeButton() {
        //Open dialog
        onView(withId(R.id.open_dialog)).perform(click());
        //Click on "OK" button android.R.id.button1
        onView(withId(android.R.id.button2)).perform(click());
        //Check whether Textview with Id open_dialog_button_click_status is updated or not.
        onView(withId(R.id.open_dialog_button_click_status)).check(matches(withText(R.string.dialog_negative_button_title)));
    }

}

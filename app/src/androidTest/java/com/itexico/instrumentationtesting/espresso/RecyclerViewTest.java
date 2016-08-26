package com.itexico.instrumentationtesting.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.RecyclerViewActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class RecyclerViewTest {

    public static final String EMPLOYEE_ID_STR = "22222";
    public static final int EMPLOYEE_ID_INT = 22222;
    public static final String EMPLOYEE_NAME = "John";

    @Rule
    public ActivityTestRule<RecyclerViewActivity> mActivityRule = new ActivityTestRule<>(
            RecyclerViewActivity.class);

    @Test
    public void clickAtPosition() {
        //Locate RecyclerView and click on 0th item
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Check for TextView with ID activity_employee_details_employee_id , activity_employee_details_employee_name
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }

    @Test
    public void clickOnItem(){
        //Locate RecyclerView and click on 0th item
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText(EMPLOYEE_NAME)), click()));
        //Check for TextView with ID activity_employee_details_employee_id , activity_employee_details_employee_name
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }

}

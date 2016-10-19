package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.CustomList;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withEmployeeID;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withEmployeeName;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class CustomListTest {

    public static final String EMPLOYEE_ID_STR = "22222";
    public static final int EMPLOYEE_ID_INT = 22222;
    public static final String EMPLOYEE_NAME = "John";

    @Rule
    public ActivityTestRule<CustomList> mActivityRule = new ActivityTestRule<>(
            CustomList.class);


    @Test
    public void employeeInfoByID() {
        onData(withEmployeeID(EMPLOYEE_ID_INT)).perform(click());
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }

    @Test
    public void employeeInfoByIDAndName() {
        onData(allOf(withEmployeeID(EMPLOYEE_ID_INT), withEmployeeName(EMPLOYEE_NAME))).perform(click());
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }


    @Test
    public void employeeInfoByPosition() {
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }


}

package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;
import android.widget.AdapterView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.FragmentViewPager;
import com.itexico.instrumentationtesting.espresso.adapter.ViewPagerFragmentAdapter;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withEmployeeID;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withEmployeeName;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class ViewPagerTest {

    public static final String EMPLOYEE_ID_STR = "22222";
    public static final int EMPLOYEE_ID_INT = 22222;
    public static final String EMPLOYEE_NAME = "John";

    @Rule
    public ActivityTestRule<FragmentViewPager> mActivityRule = new ActivityTestRule<>(
            FragmentViewPager.class);

    @Test
    public void allHumanResourcesAreShown() {
        //find View pager and Perform Swipe left.
        onView(withId(R.id.view_pager)).perform(swipeLeft());
        //Check whether "Human Resources" is shown in status Text..
        onView(allOf(withId(R.id.view_pager_status), isDisplayed())).check(matches(withText(ViewPagerFragmentAdapter.PAGER_TABS[1])));
    }

    @Test
    public void clickOnOneItem() {
        // The below commented out line will fail with AmbiguousViewMatcherException because the same ListView is used in both pages of ViewPager.
        // onData(allOf(withEmployeeID(EMPLOYEE_ID_INT), withEmployeeName(EMPLOYEE_NAME))).perform(click());
        onData(allOf(withEmployeeID(EMPLOYEE_ID_INT), withEmployeeName(EMPLOYEE_NAME))).inAdapterView(allOf(isAssignableFrom(AdapterView.class),isDisplayed())).perform(click());
        //Check whether "John" and "22222" are shown Or not.
        onView(withId(R.id.activity_employee_details_employee_id)).check(matches(withText(EMPLOYEE_ID_STR)));
        onView(withId(R.id.activity_employee_details_employee_name)).check(matches(withText(EMPLOYEE_NAME)));
    }


}

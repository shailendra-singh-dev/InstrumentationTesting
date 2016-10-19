package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.SpinnerSelection;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withAdapterData;
import static com.itexico.instrumentationtesting.espresso.custom_matcher.CustomMatchers.withItemContent;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by iTexico Developer on 8/23/2016.
 */
public class SpinnerSelectionTest {

    private static final String NO_SELECTION = "Nothing Selected";

    private static final String SPINNER_FIRST_ITEM = "Text_1";

    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p/>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p/>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<SpinnerSelection> mActivityRule = new ActivityTestRule<>(
            SpinnerSelection.class);

    @Test
    public void noSelectionFound() {
        onView(withId(R.id.activity_spinner_id)).check(matches(not(withAdapterData(withItemContent(NO_SELECTION)))));
    }

    @Test
    public void labelUpdateDoesnotChangeIfFirstSelected() {
        // Click on the Spinner
        onView(withId(R.id.activity_spinner_id)).perform(click());
        //Click on First item.
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        //It should not update label.
        onView(withId(R.id.activity_spinner_selection_text)).check(matches(not(withText(SPINNER_FIRST_ITEM))));
    }


    @Test
    public void labelUpdateWhenValidTextIsSelected() {
        // Click on the Spinner
        onView(withId(R.id.activity_spinner_id)).perform(click());
        //Click on any item except first "None".
        onData(CoreMatchers.allOf(is(instanceOf(String.class)), is(SPINNER_FIRST_ITEM))).perform(click());
//        onData(allOf(is(instanceOf(String.class)), Matchers.is(SPINNER_FIRST_ITEM)))).atPosition(0).perform(click());
        //It should not update label.
        onView(withId(R.id.activity_spinner_selection_text)).check(matches(withText(SPINNER_FIRST_ITEM)));
    }

}

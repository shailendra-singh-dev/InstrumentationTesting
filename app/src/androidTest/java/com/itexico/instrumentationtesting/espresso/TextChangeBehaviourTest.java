package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.ChangeText;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/22/2016.
 */

@RunWith(AndroidJUnit4.class)
//@LargeTest
public class TextChangeBehaviourTest {

    private static final String STRING_TO_BE_TYPED = "Espresso Sample";
    /**
     * A JUnit {@link Rule @Rule} to launch your activity under test. This is a replacement
     * for {@link ActivityInstrumentationTestCase2}.
     * <p>
     * Rules are interceptors which are executed for each test method and will run before
     * any of your setup code in the {@link Before @Before} method.
     * <p>
     * {@link ActivityTestRule} will create and launch of the activity for you and also expose
     * the activity under test. To get a reference to the activity you can use
     * the {@link ActivityTestRule#getActivity()} method.
     */
    @Rule
    public ActivityTestRule<ChangeText> mActivityRule = new ActivityTestRule<>(
            ChangeText.class);

    @Test
    public void textChangeSameActivity() {
        //Type the text..
        onView(withId(R.id.text_user_input)).perform(typeText(STRING_TO_BE_TYPED),closeSoftKeyboard());
        //click on chage button
        onView(withId(R.id.text_change_button_same_activity)).perform(click());
        //check for text that is changed..
        onView(withId(R.id.text_to_be_changed)).check(matches(withText(STRING_TO_BE_TYPED)));
    }

    @Test
    public void textChangeNewActivity(){
        //Type the text..
        onView(withId(R.id.text_user_input)).perform(typeText(STRING_TO_BE_TYPED),closeSoftKeyboard());
        //click on change button
        onView(withId(R.id.text_change_new_activity)).perform(click());
        //display the changed text.
        onView(withId(R.id.show_changed_text_view)).check(matches(withText(STRING_TO_BE_TYPED)));
    }
}

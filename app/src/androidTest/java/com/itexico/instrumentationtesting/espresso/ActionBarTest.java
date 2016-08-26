package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.ActionBar;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/25/2016.
 */
public class ActionBarTest {

    @Rule
    public ActivityTestRule<ActionBar> mActivityRule = new ActivityTestRule<>(
            ActionBar.class);

    @Test
    public void optionMenuItemClick(){
        //Open Action bar contextual menu item "Notification".
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        //Click on it.
        onView(withText(R.string.action_bar_item_notification)).perform(click());
        //check for update status text
        onView(withId(R.id.update_status)).check(matches(withText(R.string.action_bar_item_notification)));
    }

    @Test
    public void actionMode() {
        //Click on Button which has ID switch_to_action_mode
        onView(withId(R.id.switch_to_action_mode)).perform(click());
        //Perform click on it..
        onView(withId(R.id.action_mode_search)).perform(click());
        //Check for update Status..
        onView(withId(R.id.update_status)).check(matches(withText(R.string.action_mode_search)));
    }
    @Test
    public void clickOnActionBarItem() {
        //Click on an item from Action bar
        onView(withId(R.id.action_bar_item_share)).perform(click());
        //check for text update..
        onView(withId(R.id.update_status)).check(matches(withText(R.string.action_bar_item_share)));
    }

}

package com.itexico.instrumentationtesting.espresso;

import android.support.test.rule.ActivityTestRule;
import android.widget.EditText;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.SearchView;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.not;


/**
 * Created by iTexico Developer on 8/25/2016.
 */
public class SearchViewTest {

    private static final String ITEM_NOT_FOUND = "Item not found";
    private static final String ITEM_FOUND = "china";
    private static final String ITEM_FOUND_SUGGESTION = "chi";

    @Rule
    public ActivityTestRule<SearchView> mActivityRule = new ActivityTestRule<>(
            SearchView.class);


    @Test
    public void itemNotFound() {
        onView(withId(R.id.action_search)).perform(click());
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_NOT_FOUND), pressImeActionButton());
        onView(withId(R.id.search_empty_view)).check(matches(isDisplayed()));
    }

    @Test
    public void itemFound() {
        //click on search
        onView(withId(R.id.action_search)).perform(click());
        //type text china in search box.
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_FOUND), pressImeActionButton());
        //check whether empty view is displayed.
        onView(withId(R.id.search_empty_view)).check(matches(not(isDisplayed())));
        //check whether android textview is displayed or not.
        onView(withId(android.R.id.text1)).check(matches(isDisplayed()));
    }

    @Test
    public void searchSuggestionDisplayed() {
        //click on search
        onView(withId(R.id.action_search)).perform(click());
        //type text china in search box.
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_FOUND), pressImeActionButton());
        //Press back..
        pressBack();
        //Clear text
        onView(isAssignableFrom(EditText.class)).perform(clearText());
        //type text and press search ime action
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_FOUND_SUGGESTION));
        //check whether Search suggestions are shown Or not.
        onView(withText(ITEM_FOUND)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

    }

    @Test
    public void clickOnSearchSuggestion() {
        //click on search
        onView(withId(R.id.action_search)).perform(click());
        //type text china in search box.
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_FOUND), pressImeActionButton());
        //Press back..
        pressBack();
        //Clear text
        onView(isAssignableFrom(EditText.class)).perform(clearText());
        //type text and press search ime action
        onView(isAssignableFrom(EditText.class)).perform(typeText(ITEM_FOUND_SUGGESTION));
        //check whether Search suggestions are shown Or not.
        onView(withText(ITEM_FOUND)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).perform(click());
        //check item is in Search suggestion list.
        onView(withId(android.R.id.text1)).check(matches(isDisplayed()));
    }


}

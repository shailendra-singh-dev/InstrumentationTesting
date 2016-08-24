package com.itexico.instrumentationtesting.utils;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by iTexico Developer on 8/23/2016.
 */
public class CustomMatchers {

    public static Matcher<View> withAdapterData(final Matcher<Object> objectMatcher) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    objectMatcher.matches(adapter.getItem(i));
                }
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with Adapter Data");
                objectMatcher.describeTo(description);
            }
        };
    }

    public static Matcher<Object> withItemContent(String expectedText) {
        if (null == expectedText) {
            throw new NullPointerException();
        }
        return withItemContent(equalTo(expectedText));

    }

    public static Matcher<Object> withItemContent(final Matcher<String> objectMatcher) {
        return new BoundedMatcher<Object, String>(String.class) {

            /**
             * Generates a description of the object.  The description may be part of a
             * a description of a larger object of which this is just a component, so it
             * should be worded appropriately.
             *
             * @param description The description to be built or appended to.
             */
            @Override
            public void describeTo(Description description) {
                description.appendText("with item content");
                objectMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(String item) {
                objectMatcher.matches(item);
                return false;
            }
        };
    }
}

package com.itexico.instrumentationtesting.espresso.web;

import android.content.Intent;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.test.ActivityInstrumentationTestCase2;

import com.itexico.instrumentationtesting.espresso.activities.WebViewActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.clearElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.getText;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class WebViewActivityTest {

    private static final String SUBMIT_FORM_TEXT = "Welcome";
    private static final String CHANGE_TEXT = "Hello !!!";

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
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<WebViewActivity>(
            WebViewActivity.class, false, false) {
        @Override
        protected void afterActivityLaunched() {
            // Technically we do not need to do this - WebViewActivity has javascript turned on.
            // Other WebViews in your app may have javascript turned off, however since the only way
            // to automate WebViews is through javascript, it must be enabled.
            onWebView().forceJavascriptEnabled();
        }
    };

    @Test
    public void typeTextAndSubmit() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityRule.launchActivity(withWebFormIntent());

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                        // Clear previous input
                .perform(clearElement())
                        // Enter text into the input element
                .perform(DriverAtoms.webKeys(SUBMIT_FORM_TEXT))
                        // Find the submit button
                .withElement(findElement(Locator.ID, "submitBtn"))
                        // Simulate a click via javascript
                .perform(webClick())
                        // Find the response element by ID
                .withElement(findElement(Locator.ID, "response"))
                        // Verify that the response page contains the entered text
                .check(webMatches(getText(), containsString(SUBMIT_FORM_TEXT)));
    }

    @Test
    public void typeTextAndUpdateMessage() {
        // Lazily launch the Activity with a custom start Intent per test
        mActivityRule.launchActivity(withWebFormIntent());

        // Selects the WebView in your layout. If you have multiple WebViews you can also use a
        // matcher to select a given WebView, onWebView(withId(R.id.web_view)).
        onWebView()
                // Find the input element by ID
                .withElement(findElement(Locator.ID, "text_input"))
                        // Clear previous input
                .perform(clearElement())
                        // Enter text into the input element
                .perform(DriverAtoms.webKeys(CHANGE_TEXT))
                        // Find the change text button.
                .withElement(findElement(Locator.ID, "changeTextBtn"))
                        // Click on it.
                .perform(webClick())
                        // Find the message element by ID
                .withElement(findElement(Locator.ID, "message"))
                        // Verify that the text is displayed
                .check(webMatches(getText(), containsString(CHANGE_TEXT)));
    }

    /**
     * @return start {@link Intent} for the simple web form URL.
     */
    private static Intent withWebFormIntent() {
        Intent basicFormIntent = new Intent();
        basicFormIntent.putExtra(WebViewActivity.KEY_URL_TO_LOAD, WebViewActivity.WEB_FORM_URL);
        return basicFormIntent;
    }
}

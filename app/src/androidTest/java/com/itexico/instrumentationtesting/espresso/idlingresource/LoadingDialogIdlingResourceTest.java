package com.itexico.instrumentationtesting.espresso.idlingresource;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.IdlingLoadingDialog;
import com.itexico.instrumentationtesting.espresso.fragments.LoadingDialogFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
@RunWith(AndroidJUnit4.class)
public class LoadingDialogIdlingResourceTest {

    @Rule
    public ActivityTestRule<IdlingLoadingDialog> mActivityRule = new ActivityTestRule<>(
            IdlingLoadingDialog.class);

    @Test
    public void waitForSecond() {
        //Make sure Espresso does not time out.
        IdlingPolicies.setMasterPolicyTimeout(5 * LoadingDialogFragment.DISMISS_DIALOG_DELAY, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(2 * LoadingDialogFragment.DISMISS_DIALOG_DELAY, TimeUnit.MILLISECONDS);

        //Now we wait..
        IdlingResource idlingResource = new LoadingDialogIdlingResource();
        Espresso.registerIdlingResources(idlingResource);

        //check for Done text..
        onView(withId(R.id.loading_dialog_text)).check(matches(withText(R.string.loading_dialog_text)));
    }
}

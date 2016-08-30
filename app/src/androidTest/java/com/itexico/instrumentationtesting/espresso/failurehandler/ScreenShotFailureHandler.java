package com.itexico.instrumentationtesting.espresso.failurehandler;

import android.net.Uri;
import android.support.test.espresso.EspressoException;
import android.support.test.espresso.FailureHandler;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.core.deps.guava.base.Throwables;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import junit.framework.AssertionFailedError;

import org.hamcrest.Matcher;

import java.lang.ref.WeakReference;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class ScreenShotFailureHandler implements FailureHandler {
    private final WeakReference<AppCompatActivity> mAppCompatActivityWeakReference;
    private final ScreenShotManager mScreenShotManager;

    public ScreenShotFailureHandler(AppCompatActivity appCompatActivity) {
        mAppCompatActivityWeakReference = new WeakReference<AppCompatActivity>(appCompatActivity);
        mScreenShotManager = new ScreenShotManager(appCompatActivity);
    }

    /**
     * Handle the given error in a manner that makes sense to the environment in which the test is
     * executed (e.g. take a screenshot, output extra debug info, etc). Upon handling, most handlers
     * will choose to propagate the error.
     *
     * @param error
     * @param viewMatcher
     */
    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        if (error instanceof EspressoException || error instanceof AssertionError || error instanceof AssertionFailedError) {
            Uri location = Uri.EMPTY;
            if (null != mAppCompatActivityWeakReference.get()) {
                location = mScreenShotManager.getScreenShotURI(mAppCompatActivityWeakReference.get().getWindow().getDecorView());
            }
            throw Throwables.propagate(getUserFriendlyError(error, viewMatcher, location.toString()));
        } else {
            throw Throwables.propagate(error);
        }
    }

    /**
     * When the error is coming from espresso, it is more user friendly to:
     * 1. propagate assertions as assertions
     * 2. swap the stack trace of the error to that of current thread (which will show
     * directly where the actual problem is)
     */
    private Throwable getUserFriendlyError(Throwable error, Matcher<View> viewMatcher, String screenShotLocation) {
        String formattedScreenLocation = "";
        if (screenShotLocation != null) {
            formattedScreenLocation = " \n. Location of Screenshot:  \n " + screenShotLocation;
        }
        if (error instanceof PerformException) {
            // Re-throw the exception with the viewMatcher (used to locate the view) as the view
            // description (makes the error more readable). The reason we do this here: not all creators
            // of PerformException have access to the viewMatcher.
            throw new PerformException.Builder()
                    .from((PerformException) error)
                    .withViewDescription(viewMatcher.toString() + formattedScreenLocation)
                    .build();
        }

        if (error instanceof AssertionError) {
            // reports Failure instead of Error.
            // assertThat(...) throws an AssertionFailedError.
            error = new AssertionFailedWithCauseError(error.getMessage() + formattedScreenLocation, error);
        }

        error.setStackTrace(Thread.currentThread().getStackTrace());
        return error;
    }

    private static final class AssertionFailedWithCauseError extends AssertionFailedError {
        /* junit hides the cause constructor. */
        public AssertionFailedWithCauseError(String message, Throwable cause) {
            super(message);
            initCause(cause);
        }
    }
}

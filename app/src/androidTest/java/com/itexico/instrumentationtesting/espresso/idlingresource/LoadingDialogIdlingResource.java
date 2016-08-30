package com.itexico.instrumentationtesting.espresso.idlingresource;

import android.support.test.espresso.IdlingResource;

import com.itexico.instrumentationtesting.espresso.fragments.LoadingDialogFragment;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class LoadingDialogIdlingResource implements IdlingResource {

    private final long mStartTime;
    private ResourceCallback mResourceCallback;

    public LoadingDialogIdlingResource() {
        mStartTime = System.currentTimeMillis();
    }

    /**
     * Returns the name of the resources (used for logging and idempotency  of registration).
     */
    @Override
    public String getName() {
        return LoadingDialogIdlingResource.class.getName();
    }

    /**
     * Returns {@code true} if resource is currently idle. Espresso will <b>always</b> call this
     * method from the main thread, therefore it should be non-blocking and return immediately.
     */
    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - mStartTime;
        boolean isIdle = (elapsed >= LoadingDialogFragment.DISMISS_DIALOG_DELAY);
        if (isIdle) {
            mResourceCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }
}

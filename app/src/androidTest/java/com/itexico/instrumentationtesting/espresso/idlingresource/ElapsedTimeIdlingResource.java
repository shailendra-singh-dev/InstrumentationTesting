package com.itexico.instrumentationtesting.espresso.idlingresource;

import android.support.test.espresso.IdlingResource;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class ElapsedTimeIdlingResource implements IdlingResource {


    private final long mStartTime;
    private final long mWaitingTime;
    private ResourceCallback mResourceCallback;

    public ElapsedTimeIdlingResource(long elapsedTime) {
        mStartTime = System.currentTimeMillis();
        mWaitingTime = elapsedTime;
    }

    /**
     * Returns the name of the resources (used for logging and idempotency  of registration).
     */
    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName() + ":" + mWaitingTime;
    }

    /**
     * Returns {@code true} if resource is currently idle. Espresso will <b>always</b> call this
     * method from the main thread, therefore it should be non-blocking and return immediately.
     */
    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - mStartTime;
        boolean isIdle = (elapsed >= mWaitingTime);
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

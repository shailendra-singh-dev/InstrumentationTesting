package com.itexico.instrumentationtesting.espresso;

import android.app.ActivityManager;
import android.content.Context;
import android.support.test.espresso.IdlingResource;

import com.itexico.instrumentationtesting.espresso.service.RepeatIntentService;

/**
 * Created by iTexico Developer on 8/29/2016.
 */
public class IntentServiceIdlingResource implements IdlingResource {

    private final Context mContext;
    private ResourceCallback mResourceCallback;

    public IntentServiceIdlingResource(final Context context) {
        mContext = context;
    }


    /**
     * Returns the name of the resources (used for logging and idempotency  of registration).
     */
    @Override
    public String getName() {
        return IntentServiceIdlingResource.class.getName();
    }

    /**
     * Returns {@code true} if resource is currently idle. Espresso will <b>always</b> call this
     * method from the main thread, therefore it should be non-blocking and return immediately.
     */
    @Override
    public boolean isIdleNow() {
        boolean isIdle = !isIntentServiceRunning();
        if (isIdle && null != mResourceCallback) {
            mResourceCallback.onTransitionToIdle();
        }
        return isIdle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mResourceCallback = callback;
    }

    private boolean isIntentServiceRunning() {
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (final ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (RepeatIntentService.class.getName().equals(runningServiceInfo.service.getClassName()))
                return true;

        }
        return false;
    }
}

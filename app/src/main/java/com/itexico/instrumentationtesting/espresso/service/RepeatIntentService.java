package com.itexico.instrumentationtesting.espresso.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by iTexico Developer on 8/29/2016.
 */
public class RepeatIntentService extends IntentService {

    public static final String TAG = RepeatIntentService.class.getSimpleName();
    public static final String KEY_INPUT = "KEY_INPUT";
    public static final String KEY_OUTPUT = "KEY_OUTPUT";
    public static final String ACTION = "com.itexico.instrumentationtesting.espresso.service.action.ACTION";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RepeatIntentService(String name) {
        super(name);
    }

    public RepeatIntentService(){
        super(TAG);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SystemClock.sleep(1000);
        String input = intent.getStringExtra(KEY_INPUT);
        Intent repeatIntent = new Intent(ACTION);
        repeatIntent.putExtra(KEY_OUTPUT, input + " " + input + " " + input + " " + input + " " + input);
        LocalBroadcastManager.getInstance(this).sendBroadcast(repeatIntent);
    }
}

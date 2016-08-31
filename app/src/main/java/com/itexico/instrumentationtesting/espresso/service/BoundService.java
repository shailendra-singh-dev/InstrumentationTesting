package com.itexico.instrumentationtesting.espresso.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.itexico.instrumentationtesting.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class BoundService extends Service {

    // Determine the number of cores on the device
    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    public static final String ACTION = "com.itexico.instrumentationtesting.espresso.service.action.ACTION";
    public static final String INTENT_EXTRAS = "intent_extras";
    // Construct thread pool passing in configuration options
    // int minPoolSize, int maxPoolSize, long keepAliveTime, TimeUnit unit,
    // BlockingQueue<Runnable> workQueue
    final private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()
    );

    private LocalBroadcastManager mLocalBroadcastManager;
    private final List<String> mList = new ArrayList<String>();
    private final BoundServiceBinder mBoundServiceBinder =new BoundServiceBinder();

    public final class BoundServiceBinder extends Binder {
        public BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Get access to local broadcast manager
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    public void updateList() {
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                String[] array = BoundService.this.getResources().getStringArray(R.array.countries);
                List<String> list = Arrays.asList(array);
                mList.addAll(list);
                Intent intent = new Intent(ACTION);
                intent.putStringArrayListExtra(INTENT_EXTRAS, (ArrayList<String>) mList);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateList();
        return START_NOT_STICKY;
    }

    public List<String> getUpdatedList() {
        return mList;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBoundServiceBinder;
    }
}

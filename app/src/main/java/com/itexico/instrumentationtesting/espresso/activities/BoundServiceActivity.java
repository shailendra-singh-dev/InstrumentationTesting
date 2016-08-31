package com.itexico.instrumentationtesting.espresso.activities;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.service.BoundService;

import java.util.ArrayList;


/**
 * Created by iTexico Developer on 8/31/2016.
 */
public class BoundServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;

    // Flag indicating whether we have called bind on the service.
    boolean mBound;

    private BoundService mBoundService;

    // Class for interacting with the main interface of the service.
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder iBinder) {
            // This is called when the connection with the iBinder has been established, giving us the object we can use
            // to interact with the iBinder.  We are communicating with the iBinder using a Messenger, so here we get a
            // client-side representation of that from the raw IBinder object.
            BoundService.BoundServiceBinder boundServiceBinder = (BoundService.BoundServiceBinder) iBinder;
            mBoundService = boundServiceBinder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            // This is called when the connection with the service has been unexpectedly disconnected -- that is,
            // its process crashed.
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);
        mListView = (ListView) findViewById(R.id.activity_started_service_list);
        Button updateButton = (Button) findViewById(R.id.action_started_service_update_list_button);
        updateButton.setOnClickListener(this);
        TextView emptyView = (TextView) findViewById(R.id.activity_started_service_list_empty_view);
        mListView.setEmptyView(emptyView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register for the particular broadcast based on ACTION string
        IntentFilter filter = new IntentFilter(BoundService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, filter);
        // or `registerReceiver(mBroadcastReceiver, filter)` for a normal broadcast
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener when the application is paused
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        // or `unregisterReceiver(mBroadcastReceiver)` for a normal broadcast
    }

    // Define the callback for what to do when message is received
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == BoundService.ACTION) {
                final ArrayList<String> list = intent.getStringArrayListExtra(BoundService.INTENT_EXTRAS);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BoundServiceActivity.this, android.R.layout.simple_list_item_1, list);
                mListView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.action_started_service_update_list_button) {
            performBackgroundOperation();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to the service
        bindService(new Intent(this, BoundService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void performBackgroundOperation() {
        if (mBound) {
            // Call a method from the ThreadPoolExecutorService.
            // However, if this call were something that might hang, then this request should
            // occur in a separate thread to avoid slowing down the activity performance.
            mBoundService.updateList();
        }
    }

}

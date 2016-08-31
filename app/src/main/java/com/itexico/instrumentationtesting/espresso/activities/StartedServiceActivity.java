package com.itexico.instrumentationtesting.espresso.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.service.StartedService;

import java.util.ArrayList;

/**
 * Created by iTexico Developer on 8/31/2016.
 */
public class StartedServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String INTENT_EXTRAS_KEY = "intent_extras_key";
    private ListView mListView;

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
        IntentFilter filter = new IntentFilter(StartedService.ACTION);
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
            if (intent.getAction() == StartedService.ACTION) {
                final ArrayList<String> list = intent.getStringArrayListExtra(StartedService.INTENT_EXTRAS);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartedServiceActivity.this, android.R.layout.simple_list_item_1, list);
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
            Intent intent = new Intent(this, StartedService.class);
            startService(intent);
        }
    }
}

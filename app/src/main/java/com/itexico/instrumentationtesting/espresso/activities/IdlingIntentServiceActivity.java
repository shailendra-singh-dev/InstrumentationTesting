package com.itexico.instrumentationtesting.espresso.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.service.RepeatIntentService;

/**
 * Created by iTexico Developer on 8/29/2016.
 */
public class IdlingIntentServiceActivity extends AppCompatActivity {

    private static final IntentFilter intentFilter = new IntentFilter(RepeatIntentService.ACTION);
    private TextView mStatusView;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);
        final EditText mStatusEditText = (EditText) findViewById(R.id.repeat_service_edit_text);
        mStatusView = (TextView) findViewById(R.id.repeat_service_status);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);

        ((Button) findViewById(R.id.repeat_service_buttton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(IdlingIntentServiceActivity.this, RepeatIntentService.class);
                String status = mStatusEditText.getText().toString();
                intent.putExtra(RepeatIntentService.KEY_INPUT, status);
                IdlingIntentServiceActivity.this.startService(intent);
            }
        });
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(RepeatIntentService.KEY_OUTPUT);
            mStatusView.setText(text);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mLocalBroadcastManager.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalBroadcastManager.unregisterReceiver(mBroadcastReceiver);
    }
}

package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/29/2016.
 */
public class IdlingElapsedTime extends AppCompatActivity implements View.OnClickListener {

    private Button mToggleButton;
    private TextView mElapsedTime;
    private TextView mStatusView;
    private long mStartTime = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling_elapsed_time);
        mToggleButton = (Button) findViewById(R.id.toggle_button);
        mElapsedTime = (TextView) findViewById(R.id.elapsed_Time);
        mStatusView = (TextView) findViewById(R.id.result);
        mToggleButton.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toggle_button) {
            if (mStartTime == -1) {
                mStartTime = System.currentTimeMillis();
                mToggleButton.setText(R.string.toggle_button_stop);
                mElapsedTime.setText(null);
                mStatusView.setText(null);
            } else {
                long elapsed = System.currentTimeMillis() - mStartTime;
                mToggleButton.setText(R.string.toggle_button_start);
                mElapsedTime.setText(getString(R.string.elapsed_time, elapsed / 1000f));
                mStatusView.setText(elapsed > DateUtils.MINUTE_IN_MILLIS ? R.string.success : R.string.failure);
                mStartTime = -1;
            }
        }
    }
}

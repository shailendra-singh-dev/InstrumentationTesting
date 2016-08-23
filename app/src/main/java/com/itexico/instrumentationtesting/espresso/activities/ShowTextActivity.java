package com.itexico.instrumentationtesting.espresso.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/22/2016.
 */
public class ShowTextActivity extends AppCompatActivity {

    // The name of the extra data sent through an {@link Intent}.
    public final static String KEY_EXTRA_MESSAGE =
            "com.itexico.instrumentationtesting.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        // Get the message from the Intent.
        Intent intent = getIntent();
        String message = intent.getStringExtra(KEY_EXTRA_MESSAGE);

        // Show message.
        ((TextView) findViewById(R.id.show_changed_text_view)).setText(message);
    }

    /**
     * Creates an {@link Intent} for {@link ShowTextActivity} with the message to be displayed.
     *
     * @param context the {@link Context} where the {@link Intent} will be used
     * @param message a {@link String} with text to be displayed
     * @return an {@link Intent} used to start {@link ShowTextActivity}
     */
    static protected Intent newStartIntent(Context context, String message) {
        Intent newIntent = new Intent(context, ShowTextActivity.class);
        newIntent.putExtra(KEY_EXTRA_MESSAGE, message);
        return newIntent;
    }
}

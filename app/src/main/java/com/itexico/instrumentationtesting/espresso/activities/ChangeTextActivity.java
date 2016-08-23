package com.itexico.instrumentationtesting.espresso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/22/2016.
 */
public class ChangeTextActivity extends AppCompatActivity implements View.OnClickListener {

    // The TextView used to display the message inside the Activity.
    private TextView mTextView;

    // The EditText where the user types the message.
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text);
        // Set the listeners for the buttons.
        findViewById(R.id.text_change_button_same_activity).setOnClickListener(this);
        findViewById(R.id.text_change_new_activity).setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_to_be_changed);
        mEditText = (EditText) findViewById(R.id.text_user_input);
    }

    @Override
    public void onClick(View view) {
        // Get the text from the EditText view.
        final String text = mEditText.getText().toString();

        switch (view.getId()) {
            case R.id.text_change_button_same_activity:
                // First button's interaction: set a text in a text view.
                mTextView.setText(text);
                break;
            case R.id.text_change_new_activity:
                // Second button's interaction: start an activity and send a message to it.
                Intent intent = ShowTextActivity.newStartIntent(this, text);
                startActivity(intent);
                break;
        }
    }
}

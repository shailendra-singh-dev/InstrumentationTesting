package com.itexico.instrumentationtesting.espresso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 9/1/2016.
 */
public class ViewNumber extends AppCompatActivity {

    public static final String EXTRAS_KEY = "NUMBER";

    private static final String NUMBER = "3322516102";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_display);
        setResult(AppCompatActivity.RESULT_OK, getResultIntent());
        finish();
    }

    @VisibleForTesting
    public static Intent getResultIntent() {
        Intent result = new Intent();
        result.putExtra(EXTRAS_KEY, NUMBER);
        return result;
    }
}

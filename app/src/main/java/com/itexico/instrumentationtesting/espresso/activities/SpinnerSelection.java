package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/23/2016.
 */
public class SpinnerSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_selection);

        final Spinner spinner = (Spinner) findViewById(R.id.activity_spinner_id);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.spinner_array)));

        final TextView spinnerSelection = (TextView) findViewById(R.id.activity_spinner_selection_text);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    spinnerSelection.setText((String) parent.getSelectedItem());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}

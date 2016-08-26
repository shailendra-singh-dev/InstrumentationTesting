package com.itexico.instrumentationtesting.espresso.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.itexico.instrumentationtesting.R;

import java.util.Calendar;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class DateAndTimePicker extends AppCompatActivity implements View.OnClickListener {

    private TextView mStatusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time_picker);
        mStatusView = (TextView) findViewById(R.id.date_time_picker_update_status);

        final Button datePickerDialog = (Button) findViewById(R.id.open_date_picker_dialog);
        datePickerDialog.setOnClickListener(this);
        final Button timePickerDialog = (Button) findViewById(R.id.open_time_picker_dialog);
        timePickerDialog.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.open_date_picker_dialog:
                openDatePicker();
                break;

            case R.id.open_time_picker_dialog:
                openTimePicker();
                break;
        }
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                updateStatusView(year + "/" + monthOfYear + "/" + dayOfMonth);
            }
        }, year, monthOfYear, dayOfMonth);
        datePickerDialog.setTitle("Pick a Date");
        datePickerDialog.show();
    }


    private void openTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                updateStatusView(hourOfDay+":"+minute);
            }
        }, hourOfDay, minute, true);
        timePickerDialog.setTitle("Pick a Time");
        timePickerDialog.show();
    }


    private void updateStatusView(String text) {
        mStatusView.setText(text);
    }
}

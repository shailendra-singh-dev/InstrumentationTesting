package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class EmployeeDetails extends AppCompatActivity {

    public static final String EMPLOYEE_ID = "employee_id";
    public static final String EMPLOYEE_NAME = "employee_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);

        int id = getIntent().getIntExtra(EMPLOYEE_ID, 0);
        String name = getIntent().getStringExtra(EMPLOYEE_NAME);

        final TextView employeeIDTextView = (TextView) findViewById(R.id.activity_employee_details_employee_id);
        employeeIDTextView.setText(String.valueOf(id));

        final TextView employeeNameTextView = (TextView) findViewById(R.id.activity_employee_details_employee_name);
        employeeNameTextView.setText(name);
    }
}

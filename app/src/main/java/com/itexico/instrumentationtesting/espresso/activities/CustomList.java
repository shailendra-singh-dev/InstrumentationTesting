package com.itexico.instrumentationtesting.espresso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.adapter.EmployeeAdapter;
import com.itexico.instrumentationtesting.espresso.database.Data;
import com.itexico.instrumentationtesting.espresso.utils.Employee;

/**
 * Created by iTexico Developer on 8/23/2016.
 */
public class CustomList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customlist);

        final ListView listView = (ListView) findViewById(R.id.activity_list_id);
        listView.setAdapter(new EmployeeAdapter(this, Data.EMPLOYEE_LIST));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomList.this, EmployeeDetails.class);
                final Employee employee = (Employee) parent.getItemAtPosition(position);
                intent.putExtra(EmployeeDetails.EMPLOYEE_ID, employee.getId());
                intent.putExtra(EmployeeDetails.EMPLOYEE_NAME, employee.getName());
                CustomList.this.startActivity(intent);
            }
        });

    }
}

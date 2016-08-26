package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.adapter.RecyclerEmployeeAdapter;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class RecyclerViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        final RecyclerView recyclerView =(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerEmployeeAdapter(this));
    }
}

package com.itexico.instrumentationtesting.espresso.activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class SearchView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_view, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.action_search);

        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(menuItem); ;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(new ComponentName(this, Searchable.class)));

        return true;
    }
}

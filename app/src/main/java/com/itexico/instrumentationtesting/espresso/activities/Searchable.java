package com.itexico.instrumentationtesting.espresso.activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.content_provider.AppRecentSearchProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class Searchable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        final ListView listView = (ListView) findViewById(R.id.searchable_list);
        listView.setEmptyView(findViewById(R.id.search_empty_view));
        final Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())) {
            String searchString = intent.getStringExtra(SearchManager.QUERY);
            final List<String> results = getSearchResults(searchString);
            listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, results));
            saveRecentHistory(searchString);
        }
    }

    private void saveRecentHistory(String searchString) {
        SearchRecentSuggestions searchRecentSuggestions = new SearchRecentSuggestions(this, AppRecentSearchProvider.AUTHORITY, AppRecentSearchProvider.MODE);
        searchRecentSuggestions.saveRecentQuery(searchString, null);
    }

    private List<String> getSearchResults(final String query) {
        final List<String> countries = Arrays.asList(getResources().getStringArray(R.array.countries));
        final List<String> results = new ArrayList<>();
        for (final String country : countries) {
            if (country.toLowerCase().startsWith(query.toLowerCase())) {
                results.add(country);
            }
        }
        return results;
    }
}

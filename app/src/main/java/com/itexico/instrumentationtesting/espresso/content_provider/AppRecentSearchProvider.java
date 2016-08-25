package com.itexico.instrumentationtesting.espresso.content_provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * Created by iTexico Developer on 8/24/2016.
 */
public class AppRecentSearchProvider extends SearchRecentSuggestionsProvider {

    public static final String AUTHORITY = "com.itexico.instrumentationtesting.espresso.content_provider.authority";

    public static int MODE = DATABASE_MODE_QUERIES;

    public AppRecentSearchProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}

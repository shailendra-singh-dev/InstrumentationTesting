package com.itexico.instrumentationtesting.espresso.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/23/2016.
 */
public class EspresoHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_espresso_home);
        launchActivity(R.id.espresso_home_type_text_button, ChangeText.class);
        launchActivity(R.id.espresso_home_spinner_selection_button, SpinnerSelection.class);
        launchActivity(R.id.espresso_home_custom_list_adapter_button,CustomList.class);
        launchActivity(R.id.espresso_home_search_view_button,SearchView.class);
        launchActivity(R.id.espresso_home_action_bar_button,ActionBar.class);
        launchActivity(R.id.espresso_home_viewpager_button,FragmentViewPager.class);

    }

    private void launchActivity(final int buttonId, final Class cls) {
        findViewById(buttonId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EspresoHome.this.startActivity(new Intent(EspresoHome.this, cls));
            }
        });
    }
}

package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/25/2016.
 */
public class ActionBar extends AppCompatActivity {

    private TextView mActionModeStatusText;
    private ActionMode mActionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        Button actionModeButton = (Button) findViewById(R.id.switch_to_action_mode);
        mActionModeStatusText = (TextView) findViewById(R.id.update_status);

        actionModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActionMode();
            }
        });
    }

    private void startActionMode() {
        if (null == mActionMode) {
            mActionMode = startSupportActionMode(mActionModeCallback);
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.menu_context, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_mode_search:
                    updateStatusText(R.string.action_mode_search);
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            ActionBar.this.mActionMode = null;
        }
    };

    private void updateStatusText(int text) {
        mActionModeStatusText.setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_item_share:
                updateStatusText(R.string.action_bar_item_share);
                break;

            case R.id.action_bar_item_notification:
                updateStatusText(R.string.action_bar_item_notification);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

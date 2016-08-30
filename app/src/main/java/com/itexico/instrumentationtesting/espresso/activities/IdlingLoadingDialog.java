package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.fragments.LoadingDialogFragment;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class IdlingLoadingDialog extends AppCompatActivity {

    private TextView mLoadingDialogStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling_loading_dialog);

        mLoadingDialogStatus =(TextView)findViewById(R.id.loading_dialog_text);

        final LoadingDialogFragment loadingDialogFragment = new LoadingDialogFragment();
        loadingDialogFragment.setCancelable(false);
        loadingDialogFragment.show(getSupportFragmentManager(),LoadingDialogFragment.TAG);
    }

    public void onLoadingFinished() {
        mLoadingDialogStatus.setText(R.string.loading_dialog_text);
    }
}

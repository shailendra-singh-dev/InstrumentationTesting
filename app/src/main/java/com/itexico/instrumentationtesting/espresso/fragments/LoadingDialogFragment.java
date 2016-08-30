package com.itexico.instrumentationtesting.espresso.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.itexico.instrumentationtesting.espresso.activities.IdlingDialogActivity;

import java.lang.ref.WeakReference;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class LoadingDialogFragment extends DialogFragment {


    public static final String TAG = LoadingDialogFragment.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    private static final class LoadingDalogHandler extends Handler {

        private final WeakReference<LoadingDialogFragment> mLoadingDialogFragment;

        public LoadingDalogHandler(final LoadingDialogFragment loadingDialogFragment) {
            mLoadingDialogFragment = new WeakReference<>(loadingDialogFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            LoadingDialogFragment loadingDialogFragment = (LoadingDialogFragment) mLoadingDialogFragment.get();
            IdlingDialogActivity idlingDialogActivity = (IdlingDialogActivity) loadingDialogFragment.getActivity();
            idlingDialogActivity.onLoadingFinished();
        }
    }

}

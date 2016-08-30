package com.itexico.instrumentationtesting.espresso.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.IdlingLoadingDialog;

import java.lang.ref.WeakReference;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class LoadingDialogFragment extends DialogFragment {

    public static final String TAG = LoadingDialogFragment.class.getSimpleName();
    private static final int DISMISS_DIALOG = 1000;
    public static final long DISMISS_DIALOG_DELAY = 1000L;

    private final LoadingDialogHandler mLoadingDialogHandler;

    public LoadingDialogFragment() {
        mLoadingDialogHandler = new LoadingDialogHandler(this);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mLoadingDialogHandler.sendEmptyMessageDelayed(DISMISS_DIALOG, DISMISS_DIALOG_DELAY);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle(R.string.loading_dialog_title);
        alertDialog.setMessage(R.string.loading_dialog_message);
        return alertDialog.create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mLoadingDialogHandler.removeMessages(DISMISS_DIALOG);
    }

    private static final class LoadingDialogHandler extends Handler {

        private final WeakReference<LoadingDialogFragment> mLoadingDialogFragment;

        public LoadingDialogHandler(final LoadingDialogFragment loadingDialogFragment) {
            mLoadingDialogFragment = new WeakReference<>(loadingDialogFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            LoadingDialogFragment loadingDialogFragment = mLoadingDialogFragment.get();
            loadingDialogFragment.dismiss();
            IdlingLoadingDialog idlingDialogActivity = (IdlingLoadingDialog) loadingDialogFragment.getActivity();
            idlingDialogActivity.onLoadingFinished();
        }
    }

}

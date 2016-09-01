package com.itexico.instrumentationtesting.espresso.activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 9/1/2016.
 */
public class PickAndCallNumber extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_NUMBER_INTENT_REQUET_CODE = 1000;
    private static final int REQUEST_CODE_CALL_NUMBER = 10;
    private EditText mPickNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_and_call_number);
        mPickNumberEditText = (EditText) findViewById(R.id.pick_number_hint);
        Button pickNumberButton = (Button) findViewById(R.id.pick_number);
        pickNumberButton.setOnClickListener(this);
        Button callNumber = (Button) findViewById(R.id.call_number);
        callNumber.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pick_number:
                Intent pickNumberIntent = new Intent(this, ViewNumber.class);
                startActivityForResult(pickNumberIntent, PICK_NUMBER_INTENT_REQUET_CODE);
                break;

            case R.id.call_number:
                openPhoneDialer();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (PICK_NUMBER_INTENT_REQUET_CODE == requestCode) {
            String number = data.getStringExtra(ViewNumber.EXTRAS_KEY);
            mPickNumberEditText.setText(number);
        }
    }

    private void setCallNumberIntent() {
        Intent callNumberIntent = new Intent(Intent.ACTION_CALL);
        String number = mPickNumberEditText.getText().toString();
        callNumberIntent.setData(Uri.parse("tel:" + number));
        startActivity(callNumberIntent);
    }

    public void openPhoneDialer() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle(R.string.call_number_dialog_title);
                    builder.setMessage(R.string.call_number_dialog__message);
                    builder.setPositiveButton(R.string.call_number_dialog_positive_button_text, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            ActivityCompat.requestPermissions(PickAndCallNumber.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL_NUMBER);
                        }
                    });
                    builder.show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL_NUMBER);
                }
            } else {
                setCallNumberIntent();
            }
        } else {
            setCallNumberIntent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CALL_NUMBER:
                setCallNumberIntent();
                break;
        }
    }
}

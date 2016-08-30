package com.itexico.instrumentationtesting.espresso.activities;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.itexico.instrumentationtesting.R;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class WebViewActivity extends AppCompatActivity {

    @VisibleForTesting
    public static final String WEB_FORM_URL = "file:///android_asset/web_form.html";
    public static final String KEY_URL_TO_LOAD = "KEY_WEB_FORM_URL";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(WEB_FORM_URL);
        mWebView.requestFocus();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }
}

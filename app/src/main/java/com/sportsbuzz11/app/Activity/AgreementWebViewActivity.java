package com.sportsbuzz11.app.Activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.sportsbuzz11.app.R;


public class AgreementWebViewActivity extends AppCompatActivity {

    WebView myWebView;
    private ProgressBar progressBar;
    String[] keys = {"Privacy Policy", "Disclosure", "Terms And Conditions"};

    String[] urls = {
            "https://sportsbuzz11.com/privacy-policy/",
            "https://sportsbuzz11.com/disclosure/",
            "https://sportsbuzz11.com/terms-conditions/"
    };


    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_web_view);
        String key = getIntent().getStringExtra("key");


        Toolbar toolbar = findViewById(R.id.toolbar_view);
        setSupportActionBar(toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));

        getSupportActionBar().setTitle(key);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressBar = findViewById(R.id.pro);
        myWebView = findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

        loadURL(key);

        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Loading...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                setTitle(null);
            }

        });
    }

    private void loadURL(String key) {

        if (key.equals(keys[0]))
            myWebView.loadUrl(urls[0]);
        else if (key.equals(keys[1]))
            myWebView.loadUrl(urls[1]);
        else if (key.equals(keys[2]))
            myWebView.loadUrl(urls[2]);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}

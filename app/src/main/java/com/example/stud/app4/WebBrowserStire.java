package com.example.stud.app4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebBrowserStire extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_browser_stire);

        webview = (WebView) findViewById(R.id.webViewStire);
        webview.loadUrl(getIntent().getStringExtra("URL_SURSA"));
        //webview.loadData(getIntent().getStringExtra("TEST_ARTICOL"), "text/html; charset=UTF-8", null);
    }
}

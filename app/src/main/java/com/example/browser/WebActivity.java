package com.example.browser;

import static com.example.browser.MainActivity.SEARCH_QUERY;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private String searchAddress = "http://www.xvideos.com";
    private TextView tvQuery;
    private ImageButton btForward;
    private ImageButton btBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        init();
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                tvQuery.setText(url);
            }
        });
        webView.loadUrl(searchAddress);

        btForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goForward();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goBack();
            }
        });






    }

    private void init(){
        searchAddress = getIntent().getStringExtra(SEARCH_QUERY);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        tvQuery = findViewById(R.id.tv_query);
        btBack = findViewById(R.id.bt_back);
        btForward = findViewById(R.id.bt_forward);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }
}
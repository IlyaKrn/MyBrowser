package com.example.browser;

import static com.example.browser.MainActivity.SEARCH_QUERY;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class WebActivity extends AppCompatActivity {

    // разметка и текущий адрес поиска
    private WebView webView;
    private TextView tvQuery;
    private ImageButton btForward;
    private ImageButton btBack;

    private String searchAddress = "http://www.google.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        // инициализация разметки
        init();
        // диалог загрузки страницы
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Загрузка...");



        webView.setWebViewClient(new WebViewClient(){

            // после загрузки
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                searchAddress = url;
                tvQuery.setText(searchAddress);
                progressDialog.dismiss();
            }
            // ошибка загрузки
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                // диалог ошибки
                AlertDialog.Builder builder = new AlertDialog.Builder(WebActivity.this);
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.has_not_connection_message);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            // во время загрузки
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }

                return true;
            }


        });
        webView.loadUrl(searchAddress);

        // кнгопки "вперед" и "назад" (верхние)
        btForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.goForward();
            }
        });
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()){
                    webView.goBack();
                }
                else {
                    finish();
                }
            }
        });






    }

    // инициализация разметки и начальных данных
    private void init(){
        searchAddress = getIntent().getStringExtra(SEARCH_QUERY);
        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        tvQuery = findViewById(R.id.tv_query);
        btBack = findViewById(R.id.bt_back);
        btForward = findViewById(R.id.bt_forward);
    }

    // кнопка "назад"
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
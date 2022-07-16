package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById(R.id.webView);
        String gyUrl = "http://imyo.deu.edu.tr/tr/";
        openWebPage(gyUrl);

    }

    public void openWebPage(String url) {

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        final ProgressDialog progress = ProgressDialog.show(this, "IMYO", "Yükleniyor....", true);
        progress.show();
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(), "Sayfa yüklendi", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

    }

}

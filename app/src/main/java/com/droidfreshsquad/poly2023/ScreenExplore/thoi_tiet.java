package com.droidfreshsquad.poly2023.ScreenExplore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.droidfreshsquad.poly2023.R;

public class thoi_tiet extends AppCompatActivity {
    LinearLayout lnVetrangchu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_tiet);

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.tomorrow.io/weather/vi/VN/DN/Da_Nang/130195/");

        lnVetrangchu = findViewById(R.id.lnVetrangchu);
        lnVetrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
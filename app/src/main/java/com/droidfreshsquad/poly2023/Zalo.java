package com.droidfreshsquad.poly2023;

import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class Zalo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_zalo);

        WebView webView = findViewById(R.id.zalo);

        webView.loadUrl("https://zalo.me/0766581707");

    }
}

package com.droidfreshsquad.poly2023;

import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_mess);

        WebView webView = findViewById(R.id.mess);

        webView.loadUrl("https://m.me/144504032087667");

    }
}

package com.droidfreshsquad.poly2023;

import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_mess);

        WebView webView = findViewById(R.id.mess);

        webView.loadUrl("https://m.me/144504032087667");

    }

}
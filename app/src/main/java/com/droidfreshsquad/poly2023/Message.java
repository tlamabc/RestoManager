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

    public static class CallingActivity extends AppCompatActivity {
        EditText userIdEditText;
        TextView HeyUserTextView;
        ZegoSendCallInvitationButton voiceCallBtn,videoCallBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_calling);
            userIdEditText = findViewById(R.id.user_id_edit_text);
            HeyUserTextView = findViewById(R.id.hey_user_text_view);
            voiceCallBtn = findViewById(R.id.voice_call_btn);
            videoCallBtn = findViewById(R.id.video_call_btn);

            String userID = getIntent().getStringExtra("username");
            HeyUserTextView.setText("Wellcom! "+ userID);

            userIdEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String targetUserID = userIdEditText.getText().toString().trim();
                    setVoiceCall(targetUserID);
                    setVideoCall(targetUserID);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        void setVoiceCall(String CallingUser){
            voiceCallBtn.setIsVideoCall(false);
            voiceCallBtn.setResourceID("zego_uikit_call");
            voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(CallingUser,CallingUser)));
        }
        void setVideoCall(String CallingUser){
            videoCallBtn.setIsVideoCall(true);
            videoCallBtn.setResourceID("zego_uikit_call");
            videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(CallingUser,CallingUser)));
        }
    }
}

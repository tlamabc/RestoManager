package com.droidfreshsquad.poly2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collection;
import java.util.Collections;

import retrofit2.Call;

public class CallingActivity extends AppCompatActivity {
    Spinner userIdEditText;

    ZegoSendCallInvitationButton voiceCallBtn,videoCallBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        userIdEditText = findViewById(R.id.spinnercall);
     //   HeyUserTextView = findViewById(R.id.hey_user_text_view);
        voiceCallBtn = findViewById(R.id.voice_call_btn);
        videoCallBtn = findViewById(R.id.video_call_btn);

      //  String getEmail = getIntent().getStringExtra("username");
      //  HeyUserTextView.setText("Wellcom! "+ getEmail);


        userIdEditText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedUserID = userIdEditText.getSelectedItem().toString();
                setVoiceCall(selectedUserID);
                setVideoCall(selectedUserID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
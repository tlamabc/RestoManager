package com.droidfreshsquad.poly2023;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.droidfreshsquad.poly2023.CallingActivity;
import com.droidfreshsquad.poly2023.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class VdCallActivity extends AppCompatActivity {
    EditText userIdEditText;
    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vd_call);

        // Retrieve the current user's email address from Firebase Authentication
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getEmail();

        // Set the user's email address as the text of the EditText
        userIdEditText = findViewById(R.id.user_id_edit_text);
        userIdEditText.setText(userID);
        startBtn = findViewById(R.id.start_btn);

        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String userID = userIdEditText.getText().toString().trim();
                if(userID.isEmpty()){
                    return;
                }
                //start the server
                startService(userID);
                Intent intent = new Intent(VdCallActivity.this, CallingActivity.class);
                intent.putExtra("userID",userID);
                startActivity(intent);
                }

        });
    }
    void startService(String userID) {
        Application application = getApplication(); // Android's application context
        long appID = 6048798;   // yourAppID
        String appSign = "90a0091a88689fdd12d76ecd7f710a8bedc4733527823f0eda00b936c839f205";  // yourAppSign
        String userName = userID;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zero_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName, callInvitationConfig);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
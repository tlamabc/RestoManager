package com.droidfreshsquad.poly2023.Fragment;
import androidx.lifecycle.ViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatViewModel extends ViewModel {
//    public MessageAdapter<Message> getMessages() { };

    public DatabaseReference databaseReference;

    public ChatViewModel(String s) {
        databaseReference = FirebaseDatabase.getInstance().getReference("messages");
    }

    public void sendMessage(String message) {
        // Sử dụng DatabaseReference để thêm dữ liệu vào Realtime Database
        DatabaseReference newMessageRef = databaseReference.push();
        newMessageRef.setValue(new Message(message));
    }

    public void setDatabaseReference(DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
    }
}
package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import com.droidfreshsquad.poly2023.Fragment.MessageAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.droidfreshsquad.poly2023.R;

public class LienHeFragment extends Fragment {

    private ChatViewModel chatViewModel;
    private DatabaseReference databaseReference;

    private MessageAdapter adapter;
    private EditText editTextMessage;
    private Button buttonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lien_he, container, false);

        // Khởi tạo ViewModelStore
        ViewModelStore viewModelStore = new ViewModelStore();

        // Khởi tạo ChatViewModel
        MyType myType = new MyType(); // Tạo một đối tượng MyType hoặc cung cấp giá trị cần thiết
        ChatViewModelFactory factory = new ChatViewModelFactory(myType);

        chatViewModel = new ViewModelProvider(this, factory).get(ChatViewModel.class);

        editTextMessage = view.findViewById(R.id.edit_text_message);
        buttonSend = view.findViewById(R.id.button_send);
        RecyclerView recyclerView = view.findViewById(R.id.messaged);

        // Khởi tạo adapter
       // adapter = new MessageAdapter(getContext(), chatViewModel.getMessages());

        // Đặt Adapter cho RecyclerView
//        recyclerView.getAdapter() = adapter;

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editTextMessage.getText().toString();

                if (chatViewModel != null) {
                    chatViewModel.sendMessage(message);
                } else {
                    // Handle the case where chatViewModel is not properly initialized
                    // Log an error or display a toast message
                }
                editTextMessage.setText("");
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Khởi tạo databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReference("messages");
    }
}
package com.droidfreshsquad.poly2023.datve;


import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;
import com.droidfreshsquad.poly2023.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class DanhSachBay extends AppCompatActivity {
    ListView listViewDanhSachBay;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_bay);

        listViewDanhSachBay = findViewById(R.id.listViewDanhSachBay);

        // Kết nối đến Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Lắng nghe dữ liệu từ Firebase Realtime Database
        mDatabase.child("list_ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Ticket> tickets = new ArrayList<>();

                    for (DataSnapshot ticketSnapshot : dataSnapshot.getChildren()) {
                        Ticket ticket = ticketSnapshot.getValue(Ticket.class);
                        if (ticket != null) {
                            tickets.add(ticket);
                        }
                    }

                    // Tạo một CustomAdapter để hiển thị danh sách vé máy bay
                    TicketAdapter adapter = new TicketAdapter(DanhSachBay.this, tickets);
                    listViewDanhSachBay.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
    }
}
package com.droidfreshsquad.poly2023.datve;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_bay);
        //thanh tiêu đề
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.tieu_de, toolbar, false);
        ImageButton backButton = customView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView title = customView.findViewById(R.id.toolbar_title);
        title.setText("Danh sách bay");
        toolbar.addView(customView);
        //thanh tiêu đề


        listViewDanhSachBay = findViewById(R.id.listViewDanhSachBay);

        // Kết nối đến Firebase Realtime Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Lắng nghe dữ liệu từ Firebase Realtime Database
        mDatabase.child("list_ticket").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Ticket> ticketsToShow = null;
                    if (getIntent().hasExtra("filteredTickets")) {
                        ticketsToShow = getIntent().getParcelableArrayListExtra("filteredTickets");
                    } else if (getIntent().hasExtra("allTickets")) {
                        ticketsToShow = getIntent().getParcelableArrayListExtra("allTickets");
                    }
                    TicketAdapter adapter = new TicketAdapter(DanhSachBay.this, ticketsToShow);
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
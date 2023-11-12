package com.droidfreshsquad.poly2023.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.Adapter.ExploreAdapter;
import com.droidfreshsquad.poly2023.Domain.ExploreDomain;
import com.droidfreshsquad.poly2023.HoSo.Danh_Gia;
import com.droidfreshsquad.poly2023.HoSo.danhgia;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.ScreenExplore.thoi_tiet;
import com.droidfreshsquad.poly2023.ScreenMainActivity;
import com.droidfreshsquad.poly2023.datve.BookingActivity;
import com.droidfreshsquad.poly2023.datve.DanhSachBay;
import com.droidfreshsquad.poly2023.datve.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewExploreList;
    private RecyclerView.Adapter adapter;
    private LinearLayout lnlSearch;
    private ImageView danhgia,vemaybay,thoitiet;
    DatabaseReference mDatabase;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_screen_main, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewExploreList = view.findViewById(R.id.recyclerview2);
        recyclerViewExploreList.setLayoutManager(linearLayoutManager);
        ArrayList<ExploreDomain> explore = new ArrayList<>();
        explore.add(new ExploreDomain("imgexplore1", "Đà Nẵng", "Tham Quan Địa Điểm Du Lịch Đà Nẵng Nổi Tiếng"));
        explore.add(new ExploreDomain("imgexplore2", "Sài Gòn", "Tham Quan Địa Điểm Du Lịch Sài Gòn Nổi Tiếng"));
        explore.add(new ExploreDomain("imgexplore3", "Hà Nội", "Tham Quan Địa Điểm Du Lịch Hà Nội Nổi Tiếng"));
        adapter = new ExploreAdapter(explore);
        recyclerViewExploreList.setAdapter(adapter);

        lnlSearch = view.findViewById(R.id.lnlSearch);
        danhgia = (ImageView) view.findViewById(R.id.danhgia);
        vemaybay = (ImageView) view.findViewById(R.id.vemaybay);
        thoitiet = (ImageView) view.findViewById(R.id.thoitiet);
//----------------------------------------------
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), com.droidfreshsquad.poly2023.HoSo.danhgia.class));
            }
        });
//------------------------------------------
        mDatabase = FirebaseDatabase.getInstance().getReference().child("list_ticket");
        vemaybay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            ArrayList<Ticket> allTickets = new ArrayList<>();
                            for (DataSnapshot ticketSnapshot : dataSnapshot.getChildren()) {
                                Ticket ticket = ticketSnapshot.getValue(Ticket.class);
                                if (ticket != null) {
                                    allTickets.add(ticket);
                                }
                            }
                            // Kiểm tra xem có vé nào trong danh sách không
                            if (!allTickets.isEmpty()) {
                                // Hiển thị danh sách vé tất cả
                                Intent intent = new Intent(getActivity(), DanhSachBay.class);
                                intent.putParcelableArrayListExtra("allTickets", allTickets);
                                startActivity(intent);
                            } else {
                                // Hiển thị thông báo nếu không có vé nào
                                Toast.makeText(getActivity(), "Không có vé máy bay nào", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Hiển thị thông báo nếu không có dữ liệu
                            Toast.makeText(getActivity(), "Không có dữ liệu vé máy bay", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Xử lý lỗi nếu có
                    }
                });
            }
        });
////-------------------------------------------------
        lnlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BookingActivity.class));
            }
        });
        //=----------------------------------
        thoitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), thoi_tiet.class));
            }
        });

        return view;
    }

}



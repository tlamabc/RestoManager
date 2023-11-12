package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.datve.ThongTinKhach;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatveFragment extends Fragment {

    private RecyclerView recyclerViewProducts;
    private TextView textViewTotal;
    private Button buttonCheckout;
    private List<ThongTinKhach> gioHangItemList;
    private GioHangAdapter gioHangAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datve, container, false);

        // Khởi tạo RecyclerView, TextView và Button
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        textViewTotal = view.findViewById(R.id.textViewTotal);
        buttonCheckout = view.findViewById(R.id.buttonCheckout);

        // Khởi tạo danh sách để lưu trữ dữ liệu từ Firebase
        gioHangItemList = new ArrayList<>();
        gioHangAdapter = new GioHangAdapter(gioHangItemList); // FIX GIÚP

        // Thiết lập LinearLayoutManager cho RecyclerView
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Gán Adapter cho RecyclerView
        recyclerViewProducts.setAdapter(gioHangAdapter);

        // Thực hiện truy vấn đến Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("gio_hang");

        // Lắng nghe sự kiện khi có thay đổi trong dữ liệu
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Xóa dữ liệu cũ khi có sự thay đổi
                gioHangItemList.clear();

                // Lặp qua tất cả các nút con trong "gio_hang"
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    // Đọc dữ liệu từ Firebase và thêm vào danh sách
                    ThongTinKhach gioHangItem = postSnapshot.getValue(ThongTinKhach.class);
                    gioHangItemList.add(gioHangItem);
                }

                // Cập nhật Adapter khi có dữ liệu thay đổi
                gioHangAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra trong quá trình truy vấn
            }
        });

        return view;
    }
}

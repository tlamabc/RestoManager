package com.droidfreshsquad.poly2023.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.datve.ThongTinKhach;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private List<ThongTinKhach> gioHangItemList;



    public GioHangAdapter(List<ThongTinKhach> gioHangItemList) {
        this.gioHangItemList = gioHangItemList;
    }
    public GioHangAdapter(String json) {
        Gson gson = new Gson();
        Map<String, ThongTinKhach> gioHangItemsList = gson.fromJson(json, Map.class);
        gioHangItemList = new ArrayList<>(gioHangItemsList.values());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThongTinKhach gioHangItem = gioHangItemList.get(position);
            holder.textViewProductName.setText(gioHangItem.ten);
        holder.email.setText(gioHangItem.email);
        holder.diemdi.setText(gioHangItem.diemDi);
        holder.diemden.setText(gioHangItem.diemDen);
        holder.tongtien.setText(String.valueOf(gioHangItem.tien));

    }



    @Override
   public int getItemCount() {
        return gioHangItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewProductName, email,tongtien,diemdi,diemden;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            email = itemView.findViewById(R.id.email);
          tongtien = itemView.findViewById(R.id.tongtien) ;
            diemdi = itemView.findViewById(R.id.diemdi) ;
            diemden = itemView.findViewById(R.id.diemden) ;  // Kết nối các thành phần khác tùy thuộc vào thông tin bạn muốn hiển thị
        }
    }
}

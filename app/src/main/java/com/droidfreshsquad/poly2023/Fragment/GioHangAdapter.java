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
        holder.ngaysinhh.setText(gioHangItem.ngaySinh);
        holder.gio11.setText(gioHangItem.gio1);
        holder.gio22.setText(gioHangItem.gio2);
        holder.ngaydii.setText(gioHangItem.ngay);
        holder.sdtt.setText(gioHangItem.soDienThoai);
        holder.hangbayy.setText(gioHangItem.ari1);




    }



    @Override
   public int getItemCount() {
        return gioHangItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewProductName, email,tongtien,diemdi,diemden,ngaysinhh, gio11,gio22, ngaydii, sdtt,hangbayy;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewProductName = itemView.findViewById(R.id.textViewProductName);
            email = itemView.findViewById(R.id.email);
          tongtien = itemView.findViewById(R.id.tongtien) ;
            diemdi = itemView.findViewById(R.id.diemdi) ;
            diemden = itemView.findViewById(R.id.diemden) ;
            ngaysinhh = itemView.findViewById(R.id.ngaysinhh) ;
            gio11 = itemView.findViewById(R.id.gio11) ;
            gio22 = itemView.findViewById(R.id.gio22) ;
            ngaydii = itemView.findViewById(R.id.ngaydii) ;
            sdtt = itemView.findViewById(R.id.sdtt) ;
            hangbayy = itemView.findViewById(R.id.hangbayy) ;
            // Kết nối các thành phần khác tùy thuộc vào thông tin bạn muốn hiển thị
        }
    }
}

package com.droidfreshsquad.poly2023.datve;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.droidfreshsquad.poly2023.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TicketAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Ticket> ticketList;

    public TicketAdapter(Context context, ArrayList<Ticket> ticketList) {
        this.context = context;
        this.ticketList = ticketList;
    }

    @Override
    public int getCount() {
        return ticketList.size();
    }

    @Override
    public Object getItem(int position) {
        return ticketList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ticket_item, parent, false);
        }

        TextView scheduledTextView = convertView.findViewById(R.id.scheduledTextView);
        TextView scheduledTextView2 = convertView.findViewById(R.id.scheduledTextView2);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView dateTextView2 = convertView.findViewById(R.id.dateTextView2);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);
        TextView Airlines = convertView.findViewById(R.id.Airlines);
        TextView sanbay1 = convertView.findViewById(R.id.sanbay1);
        TextView sanbay2 = convertView.findViewById(R.id.sanbay2);
        TextView DiemDi = convertView.findViewById(R.id.DiemDi);
        TextView DiemDen = convertView.findViewById(R.id.DienDen);
        TextView timebay = convertView.findViewById(R.id.thoigianbay);

        Ticket ticket = (Ticket) getItem(position);

        if (ticket != null) {
            scheduledTextView.setText(ticket.getScheduled());
            scheduledTextView2.setText(ticket.getScheduled2());
            dateTextView.setText(ticket.getDate());
            dateTextView2.setText(ticket.getDate());

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
            String formattedPrice = numberFormat.format(ticket.getPrice());
            priceTextView.setText(formattedPrice);

            Airlines.setText(ticket.getAirlines());
            sanbay1.setText(ticket.getSanbayden());
            sanbay2.setText(ticket.getSanbaydi());
            DiemDi.setText(ticket.getDiemDi());
            DiemDen.setText(ticket.getDiemDen());
            timebay.setText(ticket.getTimebay());

            LinearLayout maGiamGia = convertView.findViewById(R.id.maGiamGia);
            maGiamGia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Bạn không có mã giảm giá nào", Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }
}

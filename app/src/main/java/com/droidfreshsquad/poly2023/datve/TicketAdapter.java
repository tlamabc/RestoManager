package com.droidfreshsquad.poly2023.datve;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.R;

import java.util.ArrayList;

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

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView scheduledTextView = convertView.findViewById(R.id.scheduledTextView);
        TextView dateTextView = convertView.findViewById(R.id.dateTextView);
        TextView priceTextView = convertView.findViewById(R.id.priceTextView);

        Ticket ticket = (Ticket) getItem(position);

        if (ticket != null) {
            nameTextView.setText(ticket.getName_ticket());
            scheduledTextView.setText("Scheduled: " + ticket.getScheduled());
            dateTextView.setText("Date: " + ticket.getDate());
            priceTextView.setText("Price: " + ticket.getPrice());
        }

        return convertView;
    }
}

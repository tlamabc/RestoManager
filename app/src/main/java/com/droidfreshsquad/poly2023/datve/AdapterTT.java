package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.R;

import java.util.ArrayList;

public class AdapterTT extends ArrayAdapter<Integer> {
    private final Context context;
    private final ArrayList<Integer> values;

    public AdapterTT(Context context, ArrayList<Integer> values) {
        super(context, R.layout.item_tt, values);
        this.context = context;
        this.values = values;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_tt, parent, false);
        TextView textView = rowView.findViewById(R.id.textTien);
        textView.setText("Giá Tiền: " + values.get(position));
        return rowView;
    }
}

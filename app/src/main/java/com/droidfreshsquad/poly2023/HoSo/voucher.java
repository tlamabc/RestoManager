package com.droidfreshsquad.poly2023.HoSo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.R;

import java.util.ArrayList;

public class voucher extends AppCompatActivity {

    private ListView listViewCodes;
    private SharedPreferences preferences;
    private ArrayList<String> codeList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voucher);
        listViewCodes = findViewById(R.id.listViewCodes);
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
        title.setText("Ví voucher");
        toolbar.addView(customView);
        //thanh tiêu đề


        listViewCodes = findViewById(R.id.listViewCodes);
        preferences = getSharedPreferences("MyPrefss", MODE_PRIVATE);

        // Lấy danh sách mã từ SharedPreferences
        codeList = getCodeListFromPreferences();

        // Tạo Adapter và thiết lập cho ListView
        CodeListAdapter adapter = new CodeListAdapter(this, R.layout.item_voucher, codeList);
        listViewCodes.setAdapter(adapter);
    }

    private ArrayList<String> getCodeListFromPreferences() {
        ArrayList<String> codeList = new ArrayList<>();
        int listSize = preferences.getInt("code_list_size", 0);

        for (int i = 0; i < listSize; i++) {
            String code = preferences.getString("code_" + i, "");
            codeList.add(code);
        }

        return codeList;
    }

    private class CodeListAdapter extends ArrayAdapter<String> {
        private Context context;
        private int resource;
        private ArrayList<String> codes;

        public CodeListAdapter(Context context, int resource, ArrayList<String> codes) {
            super(context, resource, codes);
            this.context = context;
            this.resource = resource;
            this.codes = codes;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItem = convertView;

            if (listItem == null) {
                LayoutInflater inflater = LayoutInflater.from(context);
                listItem = inflater.inflate(resource, null);
            }

            String code = codes.get(position);

            // Ánh xạ TextView trong item layout
            TextView textViewItem = listItem.findViewById(R.id.textViewItem);
            textViewItem.setText(code);

            return listItem;
        }
    }
}
package com.droidfreshsquad.poly2023.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.droidfreshsquad.poly2023.Message;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.Zalo;

import androidx.fragment.app.Fragment;

public class LienHeFragment extends Fragment {

    private LinearLayout lnl_Msg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lien_he, container, false);

        lnl_Msg = view.findViewById(R.id.lnl_send);

        lnl_Msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một Intent mới
                Intent intent = new Intent(getActivity(), Message.class);

                startActivity(intent);


                // Khởi chạy Activity
                startActivity(intent);
            }
        });
        lnl_Msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một Intent mới
                Intent intent = new Intent(getActivity(), Zalo.class);

                startActivity(intent);


                // Khởi chạy Activity
                startActivity(intent);
            }
        });

        return view;
    }
}

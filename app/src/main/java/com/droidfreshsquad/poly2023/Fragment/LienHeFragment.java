package com.droidfreshsquad.poly2023.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.droidfreshsquad.poly2023.Message;
import com.droidfreshsquad.poly2023.R;
import androidx.fragment.app.Fragment;

public class LienHeFragment extends Fragment {

    private Button openmess;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lien_he, container, false);

        openmess = view.findViewById(R.id.button_send);

        openmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một Intent mới
                Intent intent = new Intent(getActivity(), Message.class);

                startActivity(intent);


                // Khởi chạy Activity
                startActivity(intent);
            }
        });

        return view;
    }
}

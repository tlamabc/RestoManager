package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidfreshsquad.poly2023.LoginActivity;
import com.droidfreshsquad.poly2023.R;

import android.content.Intent;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.MainActivity;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;

public class HosoFragment extends Fragment {
    private ConstraintLayout ctlcaidat, sign_out_button;
    private FirebaseAuth auth;
    private TextView versionTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso, container, false);

// chức năng đăng xuất
        auth = FirebaseAuth.getInstance();  // Khởi tạo Firebase Authentication
        ConstraintLayout signOutButton = view.findViewById(R.id.sign_out_button);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                // Chuyển người dùng trở lại màn hình đăng nhập
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

//mật khẩu và bảo mật
        ConstraintLayout ctlcaidat = view.findViewById(R.id.ctlcaidat);
        ctlcaidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

// Lấy phiên bản từ BuildConfig và hiển thị nó trên TextView
        String versionName = BuildConfig.VERSION_NAME;
        versionTextView = view.findViewById(R.id.versionTextView);
        versionTextView.setText("Phiên bản: " + versionName);
        return view;
    }
}







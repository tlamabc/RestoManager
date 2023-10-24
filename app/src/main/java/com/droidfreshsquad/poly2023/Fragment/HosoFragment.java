package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidfreshsquad.poly2023.LoginActivity;
import com.droidfreshsquad.poly2023.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.droidfreshsquad.poly2023.MainActivity;
import com.droidfreshsquad.poly2023.SignupActivity;
import com.droidfreshsquad.poly2023.datve.BookingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


    public class HosoFragment extends Fragment {

        ConstraintLayout ctlcaidat;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_hoso, container, false);

            ConstraintLayout ctlcaidat = view.findViewById(R.id.ctlcaidat);
            ctlcaidat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Xử lý sự kiện chuyển trang ở đây
                    // Ở đây, tôi sẽ mở BookingActivity
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });

            return view;
        }
}

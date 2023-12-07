package com.droidfreshsquad.poly2023.HoSo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout; // Import thêm dòng này
import com.droidfreshsquad.poly2023.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BaoCao extends AppCompatActivity {
    private DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_cao);
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
        title.setText("Báo cáo");
        toolbar.addView(customView);
        //thanh tiêu đề

        ConstraintLayout baocao1 = findViewById(R.id.baocao1);
        ConstraintLayout baocao2 = findViewById(R.id.baocao2);
        ConstraintLayout baocao3 = findViewById(R.id.baocao3);
        ConstraintLayout baocao4 = findViewById(R.id.baocao4);
        ConstraintLayout baocao5 = findViewById(R.id.baocao5);
        ConstraintLayout baocao6 = findViewById(R.id.baocao6);
        ConstraintLayout baocao7 = findViewById(R.id.baocao7);

        databaseReference = FirebaseDatabase.getInstance().getReference("bao_cao");
        baocao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Ứng dụng giật lag";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Bản cập nhật mới bị lỗi";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Ứng dụng tắt đột ngột";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Đề nghị tính năng mới";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Nút không hoạt động";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String issueType = "Giao diện không phản ánh đúng";
                showReportConfirmation(issueType);
                uploadToFirebase(issueType);
            }
        });

        baocao7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Baocao = "Màu sắc không đúng";
                showReportConfirmation(Baocao);
                uploadToFirebase(Baocao);
            }
        });
    }
    private void uploadToFirebase(String Baocao) {
        String emailValue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String key = databaseReference.push().getKey();
        // Create a Report object with relevant data
        Report report = new Report(Baocao, emailValue);
        databaseReference.child(key).setValue(report);
    }
    private void showReportConfirmation(String issueType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cảm ơn bạn đã báo cáo");
        builder.setMessage("Chúng tôi sẽ khắc phục vấn đề " + issueType + " trong thời gian sớm nhất.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng thông báo
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}

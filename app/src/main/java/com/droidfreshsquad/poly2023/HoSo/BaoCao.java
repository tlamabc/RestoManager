package com.droidfreshsquad.poly2023.HoSo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout; // Import thêm dòng này
import com.droidfreshsquad.poly2023.R;

public class BaoCao extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bao_cao);
        //thanh tiêu đề
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("Báo cáo");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ConstraintLayout baocao1 = findViewById(R.id.baocao1);
        ConstraintLayout baocao2 = findViewById(R.id.baocao2);
        ConstraintLayout baocao3 = findViewById(R.id.baocao3);
        ConstraintLayout baocao4 = findViewById(R.id.baocao4);
        ConstraintLayout baocao5 = findViewById(R.id.baocao5);
        ConstraintLayout baocao6 = findViewById(R.id.baocao6);
        ConstraintLayout baocao7 = findViewById(R.id.baocao7);

        baocao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Ứng dụng giật lag");
            }
        });

        baocao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Bản cập nhật mới bị lỗi");
            }
        });

        baocao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Lỗi cụ thể: Ứng dụng tắt đột ngột");
            }
        });

        baocao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Đề nghị tính năng mới");
            }
        });

        baocao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Nút không hoạt động");
            }
        });

        baocao6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Giao diện không phản ánh đúng");
            }
        });

        baocao7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReportConfirmation("Màu sắc không đúng");
            }
        });
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

package com.droidfreshsquad.poly2023.datve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.droidfreshsquad.poly2023.R;

public class Ngay_thang_nam extends AppCompatActivity {

    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ngay_thang_nam);

        datePicker = findViewById(R.id.datePicker);
        // Tạo sự kiện cho button
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày tháng năm đã chọn
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int dayOfMonth = datePicker.getDayOfMonth();

                // Tạo intent để trả về màn hình A
                Intent intent = new Intent();
                intent.putExtra("date", dayOfMonth + "/" + (month + 1) + "/" + year);

                // Đặt kết quả cho intent
                setResult(RESULT_OK, intent);

                // Kết thúc màn hình B
                finish();
            }
        });
    }
    }

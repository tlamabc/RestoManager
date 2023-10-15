package com.droidfreshsquad.poly2023.datve;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton;
import com.droidfreshsquad.poly2023.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    private TextView edtthoigian, tvNgayVe, ngayve01;
    private Switch swKhuhoi;
    private boolean isDatePickerVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        swKhuhoi = (Switch) findViewById(R.id.swKhuhoi);
        tvNgayVe = (TextView) findViewById(R.id.tvNgayVe);
        ngayve01 = (TextView) findViewById(R.id.ngayve01);
        edtthoigian = (TextView) findViewById(R.id.edtthoigian);


//bấm vào textview chuyển sang màng hình chọn ngày
        edtthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, Ngay_thang_nam.class);
                startActivityForResult(intent, 1);
            }
        });

//click swich hiển thị ngày khứ hồi
        swKhuhoi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    // Switch is on
                    tvNgayVe.setVisibility(View.VISIBLE);
                    ngayve01.setVisibility(View.VISIBLE);
                } else {
                    // Switch is off
                    tvNgayVe.setVisibility(View.GONE);
                    ngayve01.setVisibility(View.GONE);
                }
            }
        });
        //------//
    }

    //lấy ngày tháng năm hiển thị ra textview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Lấy ngày tháng năm đã chọn
            String date = data.getStringExtra("date");
            edtthoigian.setText(date);

            // Cộng thêm 5 ngày vào ngày tháng năm đã chọn
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(date.split("/")[2]),
                    Integer.parseInt(date.split("/")[1]) - 1,
                    Integer.parseInt(date.split("/")[0]));
            calendar.add(Calendar.DATE, 5);

            // Lấy ngày tháng năm cộng thêm 5 ngày
            int newYear = calendar.get(Calendar.YEAR);
            int newMonth = calendar.get(Calendar.MONTH);
            int newDay = calendar.get(Calendar.DAY_OF_MONTH);

            // Hiển thị ngày tháng năm cộng thêm 5 ngày lên textview `tvNgayDi`
            tvNgayVe.setText(newDay + "/" + (newMonth + 1) + "/" + newYear);

        }
    }
}
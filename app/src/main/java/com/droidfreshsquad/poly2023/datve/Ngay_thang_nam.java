package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;

public class Ngay_thang_nam extends AppCompatActivity {

    private DatePicker datePicker;
    private TextView alo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(null);
        setContentView(R.layout.ngay_thang_nam);

        datePicker = findViewById(R.id.datePicker);
        alo =(TextView) findViewById(R.id.alo) ;

        // Ẩn thanh tiêu đề
        ((ViewGroup) datePicker.getChildAt(0)).getChildAt(0).setVisibility(View.GONE);

// Tạo sự kiện cho DatePicker khi ngày tháng năm thay đổi
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Lấy thứ trong tuần
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                String dayName;
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        dayName = "Chủ Nhật";
                        break;
                    case Calendar.MONDAY:
                        dayName = "Thứ Hai";
                        break;
                    case Calendar.TUESDAY:
                        dayName = "Thứ Ba";
                        break;
                    case Calendar.WEDNESDAY:
                        dayName = "Thứ Tư";
                        break;
                    case Calendar.THURSDAY:
                        dayName = "Thứ Năm";
                        break;
                    case Calendar.FRIDAY:
                        dayName = "Thứ Sáu";
                        break;
                    case Calendar.SATURDAY:
                        dayName = "Thứ Bảy";
                        break;
                    default:
                        dayName = "Không xác định";
                        break;
                }
                // Hiển thị thứ, ngày, tháng và năm đã chọn lên TextView
                String selectedDateInfo = dayName + ", " + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                alo.setText(selectedDateInfo);
            }
        });

// Tạo sự kiện cho button cập nhập lấy ngày tháng sang BookingActivity
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày tháng năm đã chọn
                int dayOfMonth = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();

                // Tạo đối tượng Calendar và đặt ngày, tháng, năm vào đó
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                // Lấy thứ trong tuần
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                String dayName;
                switch (dayOfWeek) {
                    case Calendar.SUNDAY:
                        dayName = "Chủ Nhật";
                        break;
                    case Calendar.MONDAY:
                        dayName = "Thứ Hai";
                        break;
                    case Calendar.TUESDAY:
                        dayName = "Thứ Ba";
                        break;
                    case Calendar.WEDNESDAY:
                        dayName = "Thứ Tư";
                        break;
                    case Calendar.THURSDAY:
                        dayName = "Thứ Năm";
                        break;
                    case Calendar.FRIDAY:
                        dayName = "Thứ Sáu";
                        break;
                    case Calendar.SATURDAY:
                        dayName = "Thứ Bảy";
                        break;
                    default:
                        dayName = "Không xác định";
                        break;
                }

                // Tạo intent để trả về màn hình booking
                Intent intent = new Intent();
                intent.putExtra("date", dayOfMonth + "/" + (month + 1) + "/" + year);
                intent.putExtra("dayOfWeek", dayName);

                // Đặt kết quả cho intent
                setResult(RESULT_OK, intent);

                // Kết thúc màn hình ngay thang nam
                finish();
            }
        });
    }
}

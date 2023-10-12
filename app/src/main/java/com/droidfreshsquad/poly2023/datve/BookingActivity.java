package com.droidfreshsquad.poly2023.datve;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.R;

public class BookingActivity extends AppCompatActivity {
//    ViewFlipper viewFlipper;// loading ảnh quảng cáo

    TextView edtthoigian;

    private boolean isDatePickerVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        //----------------------------------------------
//        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper); // loading ảnh quảng cáo
//        ActionViewFlipper();
        //----------------------------------------------
        edtthoigian = (TextView) findViewById(R.id.edtthoigian);
        edtthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi chạy màn hình B
                Intent intent = new Intent(BookingActivity.this, Ngay_thang_nam.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Lấy ngày tháng năm đã chọn từ màn hình B
            String date = data.getStringExtra("date");

            // Hiển thị ngày tháng năm đã chọn lên textView
            edtthoigian.setText(date);
        }
    }
//------------------------------------------------------
//    private void ActionViewFlipper() {
//        List<String> mangquancao = new ArrayList<>();
//        mangquancao.add("drawable/logo.png");
//        mangquancao.add("drawable/logo.png");
//        mangquancao.add("drawable/logo.png");
//        for (int i = 0; i<mangquancao.size(); i++){
//            ImageView imageView = new ImageView(getApplicationContext());
//            Glide.with(getApplicationContext()).load(mangquancao.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipper.addView(imageView);
//        }
//        viewFlipper.setFlipInterval(3000);
//        viewFlipper.setAutoStart(true);
//        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
//        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
//        viewFlipper.setInAnimation(slide_in);
//        viewFlipper.setOutAnimation(slide_out);
//    }
    //---------------------------------------------------
}
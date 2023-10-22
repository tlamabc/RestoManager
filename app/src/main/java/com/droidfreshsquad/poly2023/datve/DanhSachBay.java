package com.droidfreshsquad.poly2023.datve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.droidfreshsquad.poly2023.R;

public class DanhSachBay extends AppCompatActivity {
    TextView tvmuave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_danhsachve);//bỏ thử item sau có dữ liệu thì bỏ danhsachbay.xml (cho item hiển thị lên listview danhssachbay.xml)
        tvmuave = (TextView) findViewById(R.id.tvmuave);

        tvmuave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layNumbe();
            }
        });
    }


    //lấy number sang màng hình ThongTinThanhToan
    private void layNumbe() {
        Intent intent = getIntent();
        int numberLon = intent.getIntExtra("numberLon", 0);
        int numberTreEm = intent.getIntExtra("numberTreEm", 0);
        int numberEmBe = intent.getIntExtra("numberEmBe", 0);
        int tongNumber = intent.getIntExtra("tongNumber", 0);

        Intent intent1 = new Intent(DanhSachBay.this, ThongTinThanhToan.class);
        intent1.putExtra("numberLon", numberLon);
        intent1.putExtra("numberTreEm", numberTreEm);
        intent1.putExtra("numberEmBe", numberEmBe);
        intent1.putExtra("tongNumber", tongNumber);
        startActivityForResult(intent1, 2);
    }
}
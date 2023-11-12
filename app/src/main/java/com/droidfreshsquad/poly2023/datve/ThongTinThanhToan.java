package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.Fragment.DatveFragment;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.datve.SaveNumber.Number;
import com.droidfreshsquad.poly2023.datve.SaveNumber.NumberData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ThongTinThanhToan extends AppCompatActivity {
    EditText ngaysinh, phone, email, name;
    TextView  ErrorPhone, tieptuc, ErrorNgay, ErrorMail, ErrorName, nameView, emailView, phoneView, viewSokhach;
    BottomSheetDialog dialog;
    LinearLayout LnThongtin;
    ListView listViewDanhSach;
    DatabaseReference mDatabase;
    private int tongGiaTien = 0;
    private int TongSoNguoi = 0;
    private int totalTicketPrice = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_thanh_toan);
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
        title.setText("Tìm chuyến bay");
        toolbar.addView(customView);
//thanh tiêu đề
        viewSokhach = (TextView) findViewById(R.id.viewSokhach);
        phoneView = (TextView) findViewById(R.id.phoneView);
        nameView = (TextView) findViewById(R.id.nameView);
        LnThongtin = (LinearLayout) findViewById(R.id.LnThongtin);
        tieptuc = findViewById(R.id.tieptuc);


// nút Tiếp Tục
        mDatabase = FirebaseDatabase.getInstance().getReference();

//lấy Các giá trị Number số lượng khách
        Number numberObject = NumberData.getInstance().getNumberObject();// lấy ở SaveNumber
        int numberLon = 0;
        int numberTreEm = 0;
        int numberEmBe = 0;
        if (numberObject != null) {
            numberLon = numberObject.getNumberLon();
            numberTreEm = numberObject.getNumberTreEm();
            numberEmBe = numberObject.getNumberEmBe();
        }

        StringBuilder numbersText = new StringBuilder();
        if (numberLon > 0) {
            numbersText.append("  ").append(numberLon).append(" người lớn");
        }
        if (numberTreEm > 0) {
            numbersText.append(", ").append(numberTreEm).append(" trẻ em");
        }
        if (numberEmBe > 0) {
            numbersText.append(", ").append(numberEmBe).append(" em bé");
        }
        viewSokhach.setText(numbersText.toString());//in ra textView

        int TongSoNguoi = (numberTreEm + numberEmBe + numberLon);


//lấy dữ liệu item từ danhsachbay
        // Đọc dữ liệu từ Intent
        viewSokhach.setText(numbersText.toString());
        int tien = getIntent().getIntExtra("PRICE", 0);
        String diemDi = getIntent().getStringExtra("DEPARTURE");
        String diemDen = getIntent().getStringExtra("DESTINATION");
        String gio1 = getIntent().getStringExtra("SCHEDULED");
        String gio2 = getIntent().getStringExtra("SCHEDULED2");
        String ngay = getIntent().getStringExtra("DATE");
        String san1 = getIntent().getStringExtra("SANBAYDI");
        String san2 = getIntent().getStringExtra("SANBAYDEN");
        String ari1 = getIntent().getStringExtra("AIRLINES");
        String timebay = getIntent().getStringExtra("TIMEBAY");
        // Hiển thị dữ liệu vào các TextView tương ứng
        TextView tongGiaTienTextView = findViewById(R.id.tonggiatien);
        TextView di = findViewById(R.id.di);
        TextView den = findViewById(R.id.den);
        TextView giomot = findViewById(R.id.gio1);
        TextView giohai = findViewById(R.id.gio2);
        TextView ngaymot = findViewById(R.id.ngay1);
        TextView ngayhai = findViewById(R.id.ngay2);
        TextView sanmot = findViewById(R.id.san1);
        TextView sanhai = findViewById(R.id.san2);
        TextView arimot = findViewById(R.id.ari1);
        TextView thoigianbay = findViewById(R.id.thoigianbay);
        // Đặt dữ liệu vào các TextView
        tongGiaTienTextView.setText(String.valueOf(tien));
        di.setText(String.valueOf(diemDi));
        den.setText(String.valueOf(diemDen));
        giomot.setText(String.valueOf(gio1));
        giohai.setText(String.valueOf(gio2));
        ngaymot.setText(String.valueOf(ngay));
        ngayhai.setText(String.valueOf(ngay));
        sanmot.setText(String.valueOf(san1));
        sanhai.setText(String.valueOf(san2));
        arimot.setText(String.valueOf(ari1));
        thoigianbay.setText(String.valueOf(timebay));

        int tongGiaTien = tien * TongSoNguoi;
        // Định dạng số theo 1,200,000  /vnd
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPrice = numberFormat.format(tongGiaTien);
        tongGiaTienTextView.setText(formattedPrice);//in ra textview
        // Định dạng số theo 1,200,000  /ve
        NumberFormat numberForma = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPric = numberForma.format(tien);
        TextView TongSoNguoiTextView = findViewById(R.id.TongSoNguoi);
        TongSoNguoiTextView.setText(formattedPric + "/vé");
        //in ra textview
// Tạo giao diện cho bottom sheet dialog
        dialog = new BottomSheetDialog(this);
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_thongtinkhach, null);
        dialog.setContentView(viewDialog);
//-----------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("gio_hang");
        tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy tổng tiền
                String tongGiaTienString = tongGiaTienTextView.getText().toString();
                int tongGiaTien = Integer.parseInt(tongGiaTienString.replaceAll("[^0-9]", ""));

                String diemDi = getIntent().getStringExtra("DIEM_DI");
                String diemDen = getIntent().getStringExtra("DIEM_DEN");
                String gio1 = getIntent().getStringExtra("GIO1");
                String gio2 = getIntent().getStringExtra("GIO2");
                String ngay = getIntent().getStringExtra("NGAY");
                String san1 = getIntent().getStringExtra("SAN1");
                String san2 = getIntent().getStringExtra("SAN2");
                String ari1 = getIntent().getStringExtra("ARI1");
                // thông tin khách hàng
                String ten = name.getText().toString();
                String ngaySinh = ngaysinh.getText().toString();
                String emailValue = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                //String emailValue = email.getText().toString();
                // String emailValue = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();


                String phoneValue = phone.getText().toString();

                ThongTinKhach thongTinKhach = new ThongTinKhach(ten, ngaySinh, emailValue, phoneValue,tongGiaTien,  diemDi, diemDen, gio1, gio2, ngay, san1, san2, ari1);
                // Đẩy dữ liệu lên Realtime Database
                String key = myRef.push().getKey();
                myRef.child(key).setValue(thongTinKhach);


                // Thông báo khi dữ liệu đã được đẩy thành công (hoặc xử lý thêm logic tùy vào yêu cầu của bạn)
                Toast.makeText(ThongTinThanhToan.this, "Dữ liệu đã được lưu trữ thành công", Toast.LENGTH_SHORT).show();

            }

        });

        // Hiển thị bottom sheet dialog
        LnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        ErrorName = (TextView) viewDialog.findViewById(R.id.ErrorName);
        ErrorPhone = (TextView) viewDialog.findViewById(R.id.ErrorPhone);
        ErrorNgay = (TextView) viewDialog.findViewById(R.id.ErrorNgay);
        ErrorMail = (TextView) viewDialog.findViewById(R.id.ErrorMail);
        phone = (EditText) viewDialog.findViewById(R.id.phone);
        ngaysinh = (EditText) viewDialog.findViewById(R.id.ngaysinh);
        email = (EditText) viewDialog.findViewById(R.id.email);
        name = (EditText) viewDialog.findViewById(R.id.name);

//định dạng Họ và Tên
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidName(s.toString())) {
                    ErrorName.setVisibility(View.VISIBLE);
                } else {
                    ErrorName.setVisibility(View.INVISIBLE);
                    //in ra view
                    String nameValue = name.getText().toString();
                    nameView.setText(nameValue);
                }
            }
        });

//định dạng ngày Sinh
        ngaysinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidDateDDMMYYYY(s.toString())) {
                    ErrorNgay.setVisibility(View.VISIBLE);
                } else {
                    ErrorNgay.setVisibility(View.INVISIBLE);
                }
            }
        });
//định dạng eamil
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidEmail(s.toString())) {
                    ErrorMail.setVisibility(View.VISIBLE);
                } else {
                    ErrorMail.setVisibility(View.INVISIBLE);

                    String nameValue = email.getText().toString();
                    emailView.setText(nameValue);

                }
            }
        });
//định dạng Số điện thoại
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!isValidPhoneNumber(s.toString())) {
                    ErrorPhone.setVisibility(View.VISIBLE);
                } else {
                    ErrorPhone.setVisibility(View.INVISIBLE);
                    String nameValue = phone.getText().toString();
                    phoneView.setText(nameValue);
                }
            }
        });
    }




    //kiểm tra định dạng ngày tháng
    private boolean isValidDateDDMMYYYY(String input) {
        if (input == null || input.length() != 10) {
            return false;
        }
        try {
            String[] parts = input.split("/");
            if (parts.length != 3) {
                return false;
            }
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return (day >= 1 && day <= 31) && (month >= 1 && month <= 12) && (year >= 1900 && year <= 2099);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //kiểm tra định dạng số điện thoại
    private boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^[0-9]{10}$"; // Kiểm tra xem số điện thoại có 10 chữ số không
        return phoneNumber.matches(regex);
    }

    // Hàm kiểm tra định dạng email
    private boolean isValidEmail(String email) {
        // Sử dụng một biểu thức chính quy (regular expression) để kiểm tra định dạng email
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+com";
        return email.matches(emailPattern);
    }

    // Hàm kiểm tra định dạng Họ và Tên
    private boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (name.matches(".*\\d.*")) {
            return false;
        }
        if (name.trim().isEmpty()) {
            return false;
        }
        if (name.indexOf(" ") < 0) {
            return false;
        }
        return true;
    }



}

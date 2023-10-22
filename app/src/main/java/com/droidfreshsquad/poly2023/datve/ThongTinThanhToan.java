package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class ThongTinThanhToan extends AppCompatActivity {
    EditText ngaysinh,phone,email,name;
    TextView ErrorPhone,ErrorNgay,ErrorMail,ErrorName,nameView,emailView,phoneView,viewSokhach;
    BottomSheetDialog dialog;
    LinearLayout LnThongtin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thong_tin_thanh_toan);

        viewSokhach = (TextView) findViewById(R.id.viewSokhach);
        emailView = (TextView) findViewById(R.id.emailView);
        phoneView = (TextView) findViewById(R.id.phoneView);
        nameView = (TextView) findViewById(R.id.nameView);
        LnThongtin = (LinearLayout) findViewById(R.id.LnThongtin);
//lấy Các giá trị Number số lượng khách
        Intent intent = getIntent();
        int numberLon = intent.getIntExtra("numberLon", 0);
        int numberTreEm = intent.getIntExtra("numberTreEm", 0);
        int numberEmBe = intent.getIntExtra("numberEmBe", 0);
        int tongNumber = intent.getIntExtra("tongNumber", 0);
        StringBuilder numbersText = new StringBuilder();
        if (numberLon > 0) {
            numbersText.append(numberLon).append(" người lớn");
        }
        if (numberTreEm > 0) {
            numbersText.append(", ").append(numberTreEm).append(" trẻ em");
        }
        if (numberEmBe > 0) {
            numbersText.append(", ").append(numberEmBe).append(" em bé");
        }
        viewSokhach.setText(numbersText.toString());//in ra textView

// Tạo giao diện cho bottom sheet dialog
        dialog = new BottomSheetDialog(this);
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_thongtinkhach, null);
        dialog.setContentView(viewDialog);
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


package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.ScreenExplore.ChuyentrangActivity;
import com.droidfreshsquad.poly2023.datve.SaveNumber.DestinationData;
import com.droidfreshsquad.poly2023.datve.SaveNumber.Number;
import com.droidfreshsquad.poly2023.datve.SaveNumber.NumberData;
import com.droidfreshsquad.poly2023.datve.SaveNumber.CountData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.Locale;

public class ThongTinThanhToan extends AppCompatActivity {
    EditText ngaysinh, phone, email, name;
    NumberFormat numberFormat;
    TextView ngaysinhView,discountCodeEditText,tongGiaTienTextView, TongSoNguoiTextView, ErrorPhone, tieptuc, ErrorNgay, ErrorMail, ErrorName, nameView, emailView, phoneView, viewSokhach;
    BottomSheetDialog dialog;
    LinearLayout LnThongtin;
Button buttonOpenSpinWheel;
    int tien;



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
        viewSokhach = findViewById(R.id.viewSokhach);
        phoneView = findViewById(R.id.phoneView);
        nameView = findViewById(R.id.nameView);
        ngaysinhView = findViewById(R.id.ngaysinhView);
        LnThongtin = findViewById(R.id.LnThongtin);
        tieptuc = findViewById(R.id.tieptuc);



        discountCodeEditText = findViewById(R.id.discountCodeEditText);

        buttonOpenSpinWheel = findViewById(R.id.buttonOpenSpinWheel);



//lấy giá trị số lượng item trong giỏ hàng lưu ở saveSoVe
        int retrievedItemCount = CountData.getInstance().getCount();
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
        if (numberLon == 0) {
            numbersText.append("  ").append("1").append(" người lớn");
        }
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
        tien = getIntent().getIntExtra("PRICE", 0);
        String diemDi = getIntent().getStringExtra("DEPARTURE");
        String diemDen = getIntent().getStringExtra("DESTINATION");
        String gio1 = getIntent().getStringExtra("SCHEDULED");
        String gio2 = getIntent().getStringExtra("SCHEDULED2");
        String ngay = getIntent().getStringExtra("DATE");
        String san1 = getIntent().getStringExtra("SANBAYDEN");
        String san2 = getIntent().getStringExtra("SANBAYDI");
        String ari1 = getIntent().getStringExtra("AIRLINES");
        String timebay = getIntent().getStringExtra("TIMEBAY");


        // Hiển thị dữ liệu vào các TextView tương ứng
        tongGiaTienTextView = findViewById(R.id.tonggiatien);
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
//        tongGiaTienTextView.setText(String.valueOf(tien));
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


        final int[] tongGiaTien = new int[1];
        if (TongSoNguoi > 0) {
            tongGiaTien[0] = tien * TongSoNguoi;  //bấm vào tìm kiếm này có thêm được số người
        } else {
            tongGiaTien[0] = tien * 1; //bấm vào vé có sẵn thì tổng số người = 0 nên cho nó nhân 1
        }

        // Định dạng số theo 1,200,000  /vnd
        numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPrice = numberFormat.format(tongGiaTien[0]);
        tongGiaTienTextView.setText(formattedPrice);//in ra textview
        // Định dạng số theo 1,200,000  /ve
        NumberFormat numberForma = NumberFormat.getNumberInstance(Locale.getDefault());
        String formattedPric = numberForma.format(tien);
        TongSoNguoiTextView = findViewById(R.id.TongSoNguoi);
        TongSoNguoiTextView.setText(formattedPric + "/vé");//in ra textview


        buttonOpenSpinWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.discountCodeEditText);
                // Hiển thị danh sách mã giảm giá khi người dùng bấm vào ô EditText
                editText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAllDiscountCodesDialog();
                    }
                });
                String inputCode = editText.getText().toString();

                if (inputCode.isEmpty()) {
                    Toast.makeText(ThongTinThanhToan.this, "Vui lòng nhập mã để kiểm tra.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences preferences = getSharedPreferences("MyPrefss", MODE_PRIVATE);
                int listSize = preferences.getInt("code_list_size", 0);
                boolean found = false;

                for (int i = 0; i < listSize; i++) {
                    String savedCode = preferences.getString("code_" + i, "");
                    if (savedCode.equals(inputCode)) {
                        found = true;
                        removeCodeFromList(preferences, i, listSize);
                        Toast.makeText(ThongTinThanhToan.this, "Mã đã được xác nhận", Toast.LENGTH_SHORT).show();

                        double discountAmount = 0.1 * tongGiaTien[0];
                        int discountedPrice = (int) (tongGiaTien[0] - discountAmount);

                        String formattedNewPrice = numberFormat.format(discountedPrice);
                        tongGiaTienTextView.setText(formattedNewPrice);

                        tongGiaTien[0] = discountedPrice;

                        break;
                    }
                }

                if (!found) {
                    Toast.makeText(ThongTinThanhToan.this, "Mã không hợp lệ.", Toast.LENGTH_SHORT).show();
                }
            }

            private void removeCodeFromList(SharedPreferences preferences, int positionToRemove, int listSize) {
                SharedPreferences.Editor editor = preferences.edit();

                for (int i = positionToRemove + 1; i < listSize; i++) {
                    String codeToMove = preferences.getString("code_" + i, "");
                    editor.putString("code_" + (i - 1), codeToMove);
                }

                editor.putInt("code_list_size", listSize - 1);
                editor.remove("code_" + (listSize - 1));

                editor.apply();
            }

            private void showAllDiscountCodesDialog() {
                SharedPreferences preferences = getSharedPreferences("MyPrefss", MODE_PRIVATE);
                int listSize = preferences.getInt("code_list_size", 0);
                String[] discountCodesArray = new String[listSize];
                for (int i = 0; i < listSize; i++) {
                    discountCodesArray[i] = preferences.getString("code_" + i, "");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(ThongTinThanhToan.this);
                builder.setTitle("Chọn mã giảm giá");
                builder.setItems(discountCodesArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedCode = discountCodesArray[which];
                        applyDiscountCode(selectedCode);
                    }
                });

                builder.show();
            }

            private void applyDiscountCode(String selectedCode) {
                EditText editText = findViewById(R.id.discountCodeEditText);
                editText.setText(selectedCode);
                // Gọi phương thức xử lý mã giảm giá ở đây (nếu cần)
            }
        });

//nút them vào giỏ hàng
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("gio_hang");
        tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retrievedItemCount < 2){
                    Intent intent = new Intent(ThongTinThanhToan.this, ChuyentrangActivity.class); // Assuming MainActivity hosts DatveFragment
                    intent.putExtra("FRAGMENT_TO_LOAD", "DATVE_FRAGMENT"); // Add an extra to indicate which fragment to load
                    startActivity(intent);
                    finish();
                    // lấy tổng tiền
                    String tongGiaTienString = tongGiaTienTextView.getText().toString();
                    int tongGiaTien = Integer.parseInt(tongGiaTienString.replaceAll("[^0-9]", ""));


                    int tien = getIntent().getIntExtra("PRICE", 0);
                    String diemDi = getIntent().getStringExtra("DEPARTURE");
                    String diemDen = getIntent().getStringExtra("DESTINATION");
                    String gio1 = getIntent().getStringExtra("SCHEDULED");
                    String gio2 = getIntent().getStringExtra("SCHEDULED2");
                    String ngay = getIntent().getStringExtra("DATE");
                    String san1 = getIntent().getStringExtra("SANBAYDEN");
                    String san2 = getIntent().getStringExtra("SANBAYDI");
                    String ari1 = getIntent().getStringExtra("AIRLINES");
                    String timebay = getIntent().getStringExtra("TIMEBAY");
                    // thông tin khách hàng
                    String ten = name.getText().toString();
                    String ngaySinh = ngaysinh.getText().toString();
                    String emailValue = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                    String phoneValue = phone.getText().toString();

                    ThongTinKhach thongTinKhach = new ThongTinKhach(ten, ngaySinh, emailValue, phoneValue,tongGiaTien,  diemDi, diemDen, gio1, gio2, ngay, san1, san2, ari1,timebay);
                    // Đẩy dữ liệu lên Realtime Database
                    String key = myRef.push().getKey();
                    myRef.child(key).setValue(thongTinKhach);

                    // Thông báo khi dữ liệu đã được đẩy thành công (hoặc xử lý thêm logic tùy vào yêu cầu của bạn)
                    Toast.makeText(ThongTinThanhToan.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ThongTinThanhToan.this, "Bạn còn 2 vé chưa thanh toán", Toast.LENGTH_SHORT).show();
                }
            }

        });
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
                    String nameValue = ngaysinh.getText().toString();
                    ngaysinhView.setText(nameValue);
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
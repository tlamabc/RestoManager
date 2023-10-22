package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity {
    private TextView edtthoigian, tvNgayVe, ngayve01, tvsohanhkhach;
    private Switch swKhuhoi;
    BottomSheetDialog dialog;
    private TextView tvNumberLon, tvNumberTreEm, tvNumberEmBe;
    private Button btnPlusLon, btnPlusTreEm, btnPlusEmBe, btnMinusLon, btnMinusTreEm, btnMinusEmBe, btnSoKhach, btndatve;
    private int numberLon = 1, numberTreEm = 0, numberEmBe = 0, tongNumber = numberEmBe + numberLon + numberTreEm;
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
        tvsohanhkhach = (TextView) findViewById(R.id.tvsohanhkhach);
        btndatve = (Button) findViewById(R.id.btndatve);

//Bấm Tìm kiếm chuyển sang màng hình Danh Sách chuyến bay
        btndatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayNumBer();
            }
        });
// Tạo bottom sheet dialog chọn số lượng người
        dialog = new BottomSheetDialog(this);
        // Tạo giao diện cho bottom sheet dialog
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_so_nguoi, null);
        dialog.setContentView(viewDialog);
        // Hiển thị bottom sheet dialog
        tvsohanhkhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
        // id người lớn
        tvNumberLon = (TextView) viewDialog.findViewById(R.id.tvNumberLon);
        btnPlusLon = (Button) viewDialog.findViewById(R.id.btnPlusLon);
        btnMinusLon = (Button) viewDialog.findViewById(R.id.btnMinusLon);
        //id trẻ em
        tvNumberTreEm = (TextView) viewDialog.findViewById(R.id.tvNumberTreEm);
        btnPlusTreEm = (Button) viewDialog.findViewById(R.id.btnPlusTreEm);
        btnMinusTreEm = (Button) viewDialog.findViewById(R.id.btnMinusTreEm);
        // id em bé
        tvNumberEmBe = (TextView) viewDialog.findViewById(R.id.tvNumberEmBe);
        btnPlusEmBe = (Button) viewDialog.findViewById(R.id.btnPlusEmBe);
        btnMinusEmBe = (Button) viewDialog.findViewById(R.id.btnMinusEmBe);
        //id button xác nhận
        btnSoKhach = (Button) viewDialog.findViewById(R.id.btnSoKhach);


        //tăng biến người lớn
        btnPlusLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberLon++;
                tvNumberLon.setText(String.valueOf(numberLon));
            }
        });
        //giảm biến người lớn
        btnMinusLon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberLon > 1) {
                    numberLon--; // Giảm biến số chỉ khi nó lớn hơn 0
                    tvNumberLon.setText(String.valueOf(numberLon));
                }
            }
        });
        //tăng biến trẻ em
        btnPlusTreEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberTreEm++;
                tvNumberTreEm.setText(String.valueOf(numberTreEm));
            }
        });
        //giảm biến trẻ em
        btnMinusTreEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberTreEm > 0) {
                    numberTreEm--;
                    tvNumberTreEm.setText(String.valueOf(numberTreEm));
                }
            }
        });
        //tăng biến em bé
        btnPlusEmBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberEmBe++;
                tvNumberEmBe.setText(String.valueOf(numberEmBe));
            }
        });
        //giảm biến em bé
        btnMinusEmBe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberEmBe > 0) {
                    numberEmBe--;
                    tvNumberEmBe.setText(String.valueOf(numberEmBe));
                }
            }
        });

        // button xác nhận, đóng dialog
        btnSoKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //Thêm sự kiện  khi dialog đóng sẽ hiển thị ra view
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                StringBuilder numbersText = new StringBuilder();
                if (numberLon > 0) {
                    numbersText.append(numberLon).append(" người lớn").append("\n");
                }
                if (numberTreEm > 0) {
                    numbersText.append(", ").append(numberTreEm).append(" trẻ em").append("\n");
                }
                if (numberEmBe > 0) {
                    numbersText.append(", ").append(numberEmBe).append(" em bé").append("\n");
                }
                tvsohanhkhach.setText(numbersText.toString());//in ra textView
            }
        });

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
            String dayOfWeek = data.getStringExtra("dayOfWeek");
            edtthoigian.setText(dayOfWeek + ", " + date);

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
            // Lấy thứ trong tuần cho ngày sau khi cộng thêm 5 ngày
            String dayOfWeekAfter5Days = getDayOfWeek(calendar);
            tvNgayVe.setText(dayOfWeekAfter5Days + ", " + newDay + "/" + (newMonth + 1) + "/" + newYear);
        }
    }
    // Lấy thứ trong tuần cho ngày sau khi cộng thêm 5 ngày
    private String getDayOfWeek(Calendar calendar) {
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
        return dayName;
    }

//lấy number và chuyển sang màng hình DanhSachBay
    public void LayNumBer() {
        Intent intent = new Intent(BookingActivity.this, DanhSachBay.class);
        intent.putExtra("numberLon", numberLon);
        intent.putExtra("numberTreEm", numberTreEm);
        intent.putExtra("numberEmBe", numberEmBe);
        intent.putExtra("tongNumber", tongNumber);
        startActivityForResult(intent, 1);
    }
}
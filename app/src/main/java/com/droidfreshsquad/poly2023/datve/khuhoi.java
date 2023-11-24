package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.droidfreshsquad.poly2023.datve.SaveNumber.DestinationData;
import com.droidfreshsquad.poly2023.datve.SaveNumber.Number;
import com.droidfreshsquad.poly2023.datve.SaveNumber.NumberData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Calendar;


public class khuhoi extends AppCompatActivity {
    private String airlines;
    private String scheduled;
    private String date;
    private String nameTicket;
    private String price;
    private DatabaseReference mDatabase;
    private TextView edtthoigian, tvNgayVe, ngayve01, tvsohanhkhach;

    private BottomSheetDialog dialog;
    private EditText edtdiemdi,edtdiemden;
    private LinearLayout searchButton;
    private TextView spinnerDiemDi,spinnerDiemDen;
    private TextView tvNumberLon, tvNumberTreEm, tvNumberEmBe, tvTien;
    private Button btnPlusLon, btnPlusTreEm, btnPlusEmBe, btnMinusLon, btnMinusTreEm, btnMinusEmBe, btnSoKhach, btndatve;
    private int numberLon = 1, numberTreEm = 0, numberEmBe = 0, tongNumber = numberEmBe + numberLon + numberTreEm;
    private boolean isDatePickerVisible = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.khuhoi);
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

        edtthoigian = (TextView) findViewById(R.id.edtthoigian);
        tvsohanhkhach = (TextView) findViewById(R.id.tvsohanhkhach);
        tvTien = (TextView) findViewById(R.id.tvtien);
        LinearLayout btndatve = findViewById(R.id.btndatve);
        LinearLayout btnTimKiem = findViewById(R.id.searchButton);
        spinnerDiemDen = findViewById(R.id.tvDiemDen);
        spinnerDiemDi = findViewById(R.id.tvDiemDi);

        String diemDi = DestinationData.getInstance().getDiemDi();//lấy điểm đi từ class DestinationData
        String diemDen = DestinationData.getInstance().getDiemDen();
        spinnerDiemDi.setText(diemDen);// hiển thị ra textView // hiển thị ngược lại để tìm vé khứ hồi
        spinnerDiemDen.setText(diemDi);// hiển thị ra textView

        mDatabase = FirebaseDatabase.getInstance().getReference().child("list_ticket");

// Xử lý sự kiện nhấn nút tìm kiếm
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  diemDi = DestinationData.getInstance().getDiemDen();
                String  diemDen = DestinationData.getInstance().getDiemDi();
//        String diemDenn = DestinationData.getInstance().getDiemDen();
                String thoigian = edtthoigian.getText().toString().trim();

                if (!diemDi.isEmpty() && !diemDen.isEmpty()) {
                    mDatabase.orderByChild("diemDi").equalTo(diemDi)
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        ArrayList<Ticket> filteredTickets = new ArrayList<>();
                                        for (DataSnapshot ticketSnapshot : dataSnapshot.getChildren()) {
                                            Ticket ticket = ticketSnapshot.getValue(Ticket.class);
                                            if (ticket != null) {
                                                if (diemDen.equals(ticket.getDiemDen()) && diemDi.equals(ticket.getDiemDi())) {
                                                    if (thoigian.isEmpty() || ticket.getDate() == null) {
                                                        filteredTickets.add(ticket);
                                                    } else if (ticket.getDate().equals(thoigian)) {
                                                        filteredTickets.add(ticket);
                                                    }
                                                }
                                            }
                                        }

                                        if (!filteredTickets.isEmpty()) {
                                            Intent intent = new Intent(khuhoi.this, DanhSachBay.class);
                                            intent.putParcelableArrayListExtra("filteredTickets", filteredTickets);
                                            Number numberObject = new Number(numberLon, numberTreEm, numberEmBe);
                                            NumberData.getInstance().setNumberObject(numberObject);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(khuhoi.this, "Không tìm thấy vé phù hợp", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(khuhoi.this, "Không có dữ liệu vé máy bay", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Xử lý lỗi nếu có
                                }
                            });
                } else {
                    Toast.makeText(khuhoi.this, "Nhập thông tin để tìm kiếm", Toast.LENGTH_SHORT).show();
                }
            }
        });


//Bấm Tìm kiếm chuyển sang màng hình Danh Sách chuyến bay
        btndatve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            ArrayList<Ticket> allTickets = new ArrayList<>();
                            for (DataSnapshot ticketSnapshot : dataSnapshot.getChildren()) {
                                Ticket ticket = ticketSnapshot.getValue(Ticket.class);
                                if (ticket != null) {
                                    allTickets.add(ticket);
                                }
                            }
                            // Kiểm tra xem có vé nào trong danh sách không
                            if (!allTickets.isEmpty()) {
                                // Hiển thị danh sách vé tất cả
                                Intent intent = new Intent(khuhoi.this, DanhSachBay.class);
                                intent.putParcelableArrayListExtra("allTickets", allTickets);
                                Number numberObject = new  Number(numberLon,numberTreEm,numberEmBe );
                                NumberData.getInstance().setNumberObject(numberObject);
                                startActivity(intent);
                            } else {
                                // Hiển thị thông báo nếu không có vé nào
                                Toast.makeText(khuhoi.this, "Không có vé máy bay nào", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Hiển thị thông báo nếu không có dữ liệu
                            Toast.makeText(khuhoi.this, "Không có dữ liệu vé máy bay", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Xử lý lỗi nếu có
                    }
                });
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
                    numbersText.append(" ").append(numberLon).append(" người lớn").append("\n");
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
                Intent intent = new Intent(khuhoi.this, Ngay_thang_nam.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    //lấy ngày tháng năm hiển thị ra textview
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Lấy ngày tháng năm đã chọn
            String date = data.getStringExtra("date");
            //  String dayOfWeek = data.getStringExtra("dayOfWeek");
            edtthoigian.setText(date);

//            // Cộng thêm 5 ngày vào ngày tháng năm đã chọn
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Integer.parseInt(date.split("/")[2]),
//                    Integer.parseInt(date.split("/")[1]) - 1,
//                    Integer.parseInt(date.split("/")[0]));
//            calendar.add(Calendar.DATE, 5);
//            // Lấy ngày tháng năm cộng thêm 5 ngày
//            int newYear = calendar.get(Calendar.YEAR);
//            int newMonth = calendar.get(Calendar.MONTH);
//            int newDay = calendar.get(Calendar.DAY_OF_MONTH);
//            // Lấy thứ trong tuần cho ngày sau khi cộng thêm 5 ngày
//            String dayOfWeekAfter5Days = getDayOfWeek(calendar);
//            tvNgayVe.setText(dayOfWeekAfter5Days + ", " + newDay + "/" + (newMonth + 1) + "/" + newYear);
        }
    }


    // Lấy thứ trong tuần cho ngày sau khi cộng thêm 5 ngày
//    private String getDayOfWeek(Calendar calendar) {
//        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//        String dayName;
//        switch (dayOfWeek) {
//            case Calendar.SUNDAY:
//                dayName = "Chủ Nhật";
//                break;
//            case Calendar.MONDAY:
//                dayName = "Thứ Hai";
//                break;
//            case Calendar.TUESDAY:
//                dayName = "Thứ Ba";
//                break;
//            case Calendar.WEDNESDAY:
//                dayName = "Thứ Tư";
//                break;
//            case Calendar.THURSDAY:
//                dayName = "Thứ Năm";
//                break;
//            case Calendar.FRIDAY:
//                dayName = "Thứ Sáu";
//                break;
//            case Calendar.SATURDAY:
//                dayName = "Thứ Bảy";
//                break;
//            default:
//                dayName = "Không xác định";
//                break;
//        }
//        return dayName;
//    }


    //lấy number và chuyển sang màng hình DanhSachBay
}



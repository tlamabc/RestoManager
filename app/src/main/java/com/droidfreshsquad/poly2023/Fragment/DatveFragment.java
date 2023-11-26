package com.droidfreshsquad.poly2023.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import javax.mail.internet.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.datve.SaveNumber.CountData;
import com.droidfreshsquad.poly2023.datve.ThongTinKhach;
import com.droidfreshsquad.poly2023.datve.khuhoi;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatveFragment extends Fragment {

    private RecyclerView recyclerViewProducts;
    private List<ThongTinKhach> gioHangItemList;
    private GioHangAdapter gioHangAdapter;
    private PaymentsClient paymentsClient;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datve, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        LinearLayout buttonCheckout = view.findViewById(R.id.buttonCheckout);
        LinearLayout tvHuydon = view.findViewById(R.id.tvHuydon);
        LinearLayout tvthanhtoan = view.findViewById(R.id.tvthanhtoan);
        LinearLayout tvkhuhoi = view.findViewById(R.id.tvkhuhoi);


        gioHangItemList = new ArrayList<>();
        gioHangAdapter = new GioHangAdapter(gioHangItemList);


        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewProducts.setAdapter(gioHangAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("gio_hang");

        // Initialize PaymentsClient
        paymentsClient = Wallet.getPaymentsClient(
                getActivity(),
                new Wallet.WalletOptions.Builder()
                        .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                        .build()
        );

// Lắng nghe sự kiện khi có thay đổi trong dữ liệu
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Lấy email của người dùng hiện tại
            String currentEmail = currentUser.getEmail();

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    gioHangItemList.clear();

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        ThongTinKhach gioHangItem = postSnapshot.getValue(ThongTinKhach.class);
                        if (gioHangItem != null && currentEmail.equals(gioHangItem.getEmail())) {
                            gioHangItemList.add(gioHangItem);
                        }
                    }
                    int itemCount = gioHangItemList.size(); // Đếm số lượng mục trong gioHangItemList
                    CountData.getInstance().setCount(itemCount);//cập nhật lưu vào class countData
                    gioHangAdapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle query errors
                }
            });
        }
//------------------------------
        tvHuydon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int retrievedItemCount = CountData.getInstance().getCount();//lấy từ class countData
                if (retrievedItemCount > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Xác nhận hủy đơn")
                            .setMessage("Bạn có muốn hủy tất cả đơn trong giỏ hàng này không?")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // Nhận người dùng hiện tại
                                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                                    if (currentUser != null) {
                                        // Nhận email của người dùng hiện tại
                                        String currentEmail = currentUser.getEmail();

                                        // Tham chiếu đến nút "gio_hang" trong Firebase
                                        DatabaseReference gioHangRef = FirebaseDatabase.getInstance().getReference("gio_hang");

                                        // Truy vấn để tìm các mặt hàng trong giỏ hàng cho người dùng hiện tại
                                        gioHangRef.orderByChild("email").equalTo(currentEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                // Lặp lại các mục trong giỏ hàng cho người dùng hiện tại
                                                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                                    ThongTinKhach gioHangItem = postSnapshot.getValue(ThongTinKhach.class);

                                                    //Xóa mặt hàng khỏi giỏ hàng
                                                    postSnapshot.getRef().removeValue();

                                                    // Xóa mục khỏi danh sách cục bộ
                                                    gioHangItemList.remove(gioHangItem);
                                                }

                                                // Cập nhật bộ chuyển đổi để phản ánh những thay đổi
                                                gioHangAdapter.notifyDataSetChanged();
                                                tvkhuhoi.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                // Xử lý lỗi truy vấn
                                            }
                                        });
                                    }
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel(); // Đóng AlertDialog
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    // Biến count bằng 0, hiển thị thông báo không có đơn hàng để hủy
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Thông báo")
                            .setMessage("Bạn không có đơn hàng để hủy.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel(); // Đóng AlertDialog
                                }
                            });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            }
        });

//-----------------------------------
        tvthanhtoan.setOnClickListener(new View.OnClickListener() {
            private int counter = 0;
            @Override
            public void onClick(View v) {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String currentEmail = currentUser.getEmail();
                    DatabaseReference thanhToanRef = FirebaseDatabase.getInstance().getReference("thanh_toan");
                    if (gioHangItemList.isEmpty()) {
                        AlertDialog.Builder emptyCartDialog = new AlertDialog.Builder(getActivity());
                        emptyCartDialog.setTitle("Thông báo")
                                .setMessage("Bạn chưa có đơn hàng để thanh toán.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alert = emptyCartDialog.create();
                        alert.show();
                    } else {
                        AlertDialog.Builder confirmPaymentDialog = new AlertDialog.Builder(getActivity());
                        confirmPaymentDialog.setTitle("Xác nhận thanh toán")
                                .setMessage("Bạn có chắc chắn muốn thanh toán cho đơn hàng này?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new AsyncTask<Void, Void, Void>() {
                                            @Override
                                            protected Void doInBackground(Void... voids) {
                                                for (int i = 0; i < gioHangItemList.size(); i++) {
                                                    ThongTinKhach gioHangItem = gioHangItemList.get(i);
                                                    gioHangItem.setEmail(currentEmail);
                                                    thanhToanRef.child(String.valueOf(++counter)).setValue(gioHangItem);


                                                    // Check if gioHangItemList has more than 1 item before accessing the second item
                                                    if (gioHangItemList.size() > 1 && i == 0) {
                                                        // Thêm thông tin vé khứ hồi
                                                        ThongTinKhach gioHangItem2 = gioHangItemList.get(1);
                                                        gioHangItem2.setEmail(currentEmail);
                                                        thanhToanRef.push().setValue(gioHangItem2);

                                                    }
                                                     }
                                                String subject = "Xác nhận thanh toán";
                                                String message = buildEmailMessage(gioHangItemList);

                                                String senderEmail = "easyflycskh1@gmail.com"; // Thay bằng địa chỉ email của bạn
                                                String senderPassword = "mggcqkabtauhoqde"; // Thay bằng mật khẩu email của bạn
                                                EmailSender.sendEmail(senderEmail, senderPassword, currentEmail, subject, message);
                                                return null;
                                            }

                                            private String buildEmailMessage(List<ThongTinKhach> gioHangItemList) {
                                                StringBuilder messageBuilder = new StringBuilder("Cảm ơn bạn đã đặt vé."
                                                        + "\n Vé máy bay của bạn đã được xác nhận.");

                                                for (ThongTinKhach gioHangItem : gioHangItemList) {
                                                    messageBuilder.append("\n _______________________________________")
                                                            .append("\n Hãng Bay : ").append(gioHangItem.getAri1())
                                                            .append("\n Tên : ").append(gioHangItem.getTen())
                                                            .append("\n Ngày Sinh : ").append(gioHangItem.getNgaySinh())
                                                            .append("\n Số Điện Thoại : ").append(gioHangItem.getSoDienThoai())
                                                            .append("\n Giờ bay : ").append(gioHangItem.getGio1())
                                                            .append("\n Giờ Đến : ").append(gioHangItem.getGio2())
                                                            .append("\n Email : ").append(gioHangItem.getEmail())
                                                            .append("\n Điểm Đi : ").append(gioHangItem.getDiemDi())
                                                            .append("\n Điểm Đến : ").append(gioHangItem.getDiemDen())
                                                            .append("\n Ngày Đi : ").append(gioHangItem.getNgay())
                                                            .append("\n Sân Bay Đi : ").append(gioHangItem.getSan1())
                                                            .append("\n Sân Bay Đến : ").append(gioHangItem.getSan2())
                                                            .append("\n Số Tiền : ").append(gioHangItem.getTien())
                                                            .append("\n Thời Gian Bay : ").append(gioHangItem.getTimebay());
                                                            }
                                                         messageBuilder.append("\n _______________________________________")
                                                        .append("\n Vui lòng đến địa chỉ sau để thanh toán vé:")
                                                        .append("\n 137 Nguyễn Thị Thập, Phường Hòa Minh, Quận Liên Chiểu, Thành Phố Đà Nẵng")
                                                        .append("\n Giờ làm việc: 8:00 - 17:00 ")
                                                        .append("\n Để biết thêm thông tin vui lòng liên hệ:")
                                                        .append("\n _______________________________________")
                                                        .append("\n Đăng Thanh Lâm")
                                                        .append("\n Số điện thoại: 0359001647");

                                                return messageBuilder.toString();
                                            }
                                            @Override
                                            protected void onPostExecute(Void aVoid) {
                                                super.onPostExecute(aVoid);

                                                gioHangItemList.clear();
                                                gioHangAdapter.notifyDataSetChanged();
                                                clearGioHangData(currentEmail);

                                                AlertDialog.Builder paymentSuccessDialog = new AlertDialog.Builder(getActivity());
                                                paymentSuccessDialog.setTitle("Thông báo")
                                                        .setMessage("Thông tin vé của bạn đã được gửi về email của bạn.")
                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog, int id) {
                                                                dialog.dismiss();
                                                            }
                                                        });

                                                AlertDialog paymentAlert = paymentSuccessDialog.create();
                                                paymentAlert.show();
                                            }
                                        }.execute();
                                    }
                                });

                        AlertDialog confirmAlert = confirmPaymentDialog.create();
                        confirmAlert.show();
                    }
                }
            }


//                                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        dialog.dismiss();
//                                    }
//                                });
//
//                        AlertDialog confirmAlert = confirmPaymentDialog.create();
//                        confirmAlert.show();
//                    }
//                }
//            }

            private void clearGioHangData(String currentEmail) {
                // Xóa dữ liệu từ nút "gio_hang" trong Firebase
                DatabaseReference gioHangRef = FirebaseDatabase.getInstance().getReference("gio_hang");
                gioHangRef.orderByChild("email").equalTo(currentEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ThongTinKhach gioHangItem = postSnapshot.getValue(ThongTinKhach.class);
                            postSnapshot.getRef().removeValue();
                            gioHangItemList.remove(gioHangItem);
                        }
                        gioHangAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Xử lý lỗi truy vấn
                    }
                });
            }
        });


//----lấy số lượng item để cho ẩn hoặc hiện khứ hồi
        int retrievedItemCount = CountData.getInstance().getCount();//lấy từ class countData
        if (retrievedItemCount == 0 || retrievedItemCount > 1) {
            tvkhuhoi.setVisibility(View.GONE);
        } else if (retrievedItemCount == 1) {
            tvkhuhoi.setVisibility(View.VISIBLE);
        }
        //chuyển sang màng hình khứ hồi
        tvkhuhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), khuhoi.class);
                startActivity(intent);
            }
        });
//-------------------

        //   Thay đổi phương thức
        //  buttonCheckout.setOnClickListener();
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start GPay payment flow
                PaymentDataRequest request = createPaymentDataRequest();
                if (request != null) {
                    // Trình xử lý dữ liệu thanh toán
                    AutoResolveHelper.resolveTask(
                            paymentsClient.loadPaymentData(request),
                            getActivity(),
                            1234
                    );
                }
            }
        });

        return view;
    }

    // Thay đổi phương thức `createPaymentDataRequest()`
    private PaymentDataRequest createPaymentDataRequest() {
        PaymentDataRequest request = null;
        // Tạo PaymentDataRequest object dựa trên yêu cầu thanh toán của bạn
        // Ví dụ:
        request = PaymentDataRequest.fromJson(
                "{\n" +
                        "  \"apiVersion\": 2,\n" +
                        "  \"apiVersionMinor\": 0,\n" +
                        "  \"allowedPaymentMethods\": [\n" +
                        "    {\n" +
                        "      \"type\": \"CARD\",\n" +
                        "      \"tokenizationSpecification\": {\n" +
                        "        \"type\": \"PAYMENT_GATEWAY\",\n" +
                        "        \"parameters\": {\n" +
                        "          \"gateway\": \"Thanh Lam\",\n" +
                        "          \"gatewayMerchantId\": \"BCR2DN4TY2CLEMA\"\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"parameters\": {\n" +
                        "        \"allowedCardNetworks\": [\"VISA\", \"MASTERCARD\"],\n" +
                        "        \"allowPrepaidCards\": true,\n" +
                        "        \"allowCreditCards\": true,\n" +
                        "        \"allowDebitCards\": true\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"merchantInfo\": {\n" +
                        "    \"merchantName\": \"Easy Fly\",\n" +
                        "    \"merchantId\": \"BCR2DN4TY3OLHPYJ\"\n" +
                        "  },\n" +
                        "  \"transactionInfo\": {\n" +
                        "    \"totalPriceStatus\": \"FINAL\",\n" +
                        "    \"totalPrice\": \"1.00\",\n" +
                        "    \"currencyCode\": \"USD\"\n" +
                        "  }\n" +
                        "}"
        );

        return request;
    }
}

package com.droidfreshsquad.poly2023.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.datve.ThongTinKhach;
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
    private TextView textViewTotal;
    private Button buttonCheckout;
    LinearLayout tvHuydon, thanhtoan;
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

                    gioHangAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle query errors
                }
            });


        }
        tvHuydon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            // Handle query errors
                        }
                    });
                }
            }
        });
        tvthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    // Get the email of the current user
                    String currentEmail = currentUser.getEmail();
                    // Reference to the "thanh_toan" node in Firebase
                    DatabaseReference thanhToanRef = FirebaseDatabase.getInstance().getReference("thanh_toan");
                    // Create a new ThongTinKhach object for payment status

                    // Iterate through the gioHangItemList and push each item to Firebase
                    for (ThongTinKhach gioHangItem : gioHangItemList) {
                        // Set the email of the user
                        gioHangItem.setEmail(currentEmail);

                        // Push the gioHangItem to Firebase
                        thanhToanRef.push().setValue(gioHangItem);
                    }
                    // Clear the local list
                    gioHangItemList.clear();
                    // Notify the adapter about the change
                    gioHangAdapter.notifyDataSetChanged();
                    // Remove data from Firebase "gio_hang" node
                    clearGioHangData(currentEmail);
                    // You can also perform other necessary actions
                }
            }
            private void clearGioHangData(String currentEmail) {
                // Reference to the "gio_hang" node in Firebase
                DatabaseReference gioHangRef = FirebaseDatabase.getInstance().getReference("gio_hang");
                // Truy vấn để tìm các mặt hàng trong giỏ hàng cho người dùng hiện tại
                gioHangRef.orderByChild("email").equalTo(currentEmail).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Lặp lại các mục trong giỏ hàng cho người dùng hiện tại
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            ThongTinKhach gioHangItem = postSnapshot.getValue(ThongTinKhach.class);
                            // Xóa mặt hàng khỏi giỏ hàng
                            postSnapshot.getRef().removeValue();
                            // Xóa mục khỏi danh sách cục bộ
                            gioHangItemList.remove(gioHangItem);
                        }
                        // Cập nhật bộ chuyển đổi để phản ánh những thay đổi
                        gioHangAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle query errors
                    }
                });
            }
        });


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

package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private List<ThongTinKhach> gioHangItemList;
    private GioHangAdapter gioHangAdapter;
    private PaymentsClient paymentsClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datve, container, false);

        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        textViewTotal = view.findViewById(R.id.textViewTotal);
        buttonCheckout = view.findViewById(R.id.buttonCheckout);

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
                        if (gioHangItem != null && gioHangItem.getEmail().equals(currentEmail)) {
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

        // Thay đổi phương thức `buttonCheckout.setOnClickListener()`
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

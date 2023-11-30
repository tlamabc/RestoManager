package com.droidfreshsquad.poly2023;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.Helper.CreateOrder;
import com.droidfreshsquad.poly2023.Helper.AppInfo;

import org.json.JSONObject;

import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class Zalo extends AppCompatActivity {
    Button btnPay;
    String amount = "1000"; // Default value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startzalopay);

        btnPay = findViewById(R.id.btnPayy);

        Intent intent = getIntent();
        if (intent != null) {
            int tongtienn = intent.getIntExtra("totalAmount", 0); // Get the int value from Intent
            amount = String.valueOf(tongtienn); // Convert int to String and assign it to 'amount'
        }

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ZaloPaySDK.init(AppInfo.APP_ID, Environment.SANDBOX);

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(amount); // Pass 'amount' as a String
                    String code = data.getString("returncode");

                    if (code.equals("1")) {

                        String token = data.getString("zptranstoken");

                        ZaloPaySDK.getInstance().payOrder(Zalo.this, token, "zpdk-release://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(final String transactionId, final String transToken, final String appTransID) {
                                Toast.makeText(Zalo.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentCanceled(String zpTransToken, String appTransID) {
                                Toast.makeText(Zalo.this, "Thanh toán bị hủy", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String zpTransToken, String appTransID) {
                                Toast.makeText(Zalo.this, "Thanh toán thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }
}

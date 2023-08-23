package com.droidfreshsquad.poly2023;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private EditText edTendn, edMkdn;

    Button button, button1;
    ProgressBar progressBar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edTendn = findViewById(R.id.edTendn);
        edMkdn = findViewById(R.id.edMkdn);
        button = findViewById(R.id.button2);
        button1 = findViewById(R.id.buttondn);
        progressBar = findViewById(R.id.progressBar2);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edTendn.getText().toString();
                String password = edMkdn.getText().toString();
                registerProcess(name, password);

            }
        });
    }
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void registerProcess(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.LOGIN_OPERATION);
        serverRequest.setUser(user);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> responseCall) {
//                hideProgressBar(); // Ẩn ProgressBar khi nhận phản hồi từ máy chủ
                ServerResponse resp = responseCall.body();
                if (resp.getResult().equals(Constants.SUCCESS)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                hideProgressBar(); // Ẩn ProgressBar nếu có lỗi
                Log.d(Constants.TAG, "Lỗi");
            }
        });
    }
}
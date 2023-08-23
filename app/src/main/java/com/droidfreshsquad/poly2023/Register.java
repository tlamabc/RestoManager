package com.droidfreshsquad.poly2023;

import static com.droidfreshsquad.poly2023.R.id.progressBar;

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



public class Register extends AppCompatActivity {

    EditText edTen, edMkdk, edEmail;
    Button btnDangki, button , button2;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edTen = findViewById(R.id.edTen);
        edEmail = findViewById(R.id.edEmail);
        edMkdk = findViewById(R.id.edMk);
        btnDangki = findViewById(R.id.btnDangki);
        progressBar = findViewById(R.id.progressBar);
        button = findViewById(R.id.btnDangki);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });
        button2 = findViewById(R.id.btndangnhap);
        button2.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

        btnDangki.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edTen.getText().toString();
                String password = edMkdk.getText().toString();
                String email = edEmail.getText().toString();


                // Hiển thị ProgressBar khi bấm nút "Load dữ liệu"
                progressBar.setVisibility(View.VISIBLE);


                registerProcess(name, password, email);

            }
        });
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void registerProcess(String name, String email, String password ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        ServerRequest serverRequest = new ServerRequest();
        serverRequest.setOperation(Constants.REGISTER_OPERATION);
        serverRequest.setUser(user);
        Call<ServerResponse> responseCall = requestInterface.operation(serverRequest);
        responseCall.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> responseCall) {
                hideProgressBar(); // Ẩn ProgressBar khi nhận phản hồi từ máy chủ
                ServerResponse resp = responseCall.body();
                if (resp.getResult().equals(Constants.SUCCESS)) {
                    Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                hideProgressBar(); // Ẩn ProgressBar nếu có lỗi
                Log.d(Constants.TAG, "Lỗi");
            }
        });
    }
}
//est push

package com.droidfreshsquad.poly2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderResult;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



import com.bumptech.glide.Glide;

import com.droidfreshsquad.poly2023.Fragment.FragmentMain;
import com.droidfreshsquad.poly2023.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin;
    private TextView btnReset;
    private Switch check_save;
    private String thongtinluu = "tk_mk login";



@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginapp);
        /////////////////////////////////////////////////////////////////////////////

//        ImageView gifImageView = findViewById(R.id.imageView_done);
//
//        // Đường dẫn của file GIF trong thư mục "res/drawable"
//        String gifUrl = "android.resource://" + getPackageName() + "/" + R.drawable.logo;
//
//        // Sử dụng Glide để tải và hiển thị file GIF vào ImageView
//        Glide.with(this).asGif().load(gifUrl).into(gifImageView);

////////////////////////////////////////////////////////////////////////////////////////
//Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        /*if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this,
                    MainActivity.class));
            finish();
        }*/
// set the view now
        setContentView(R.layout.loginapp);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);

        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (TextView) findViewById(R.id.btn_reset_password);
        check_save=(Switch) findViewById(R.id.check_save);
//Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResetPasswordActivity.class));

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password !", Toast.LENGTH_SHORT).show();
                    return;
                }
                //lưu thông tin người dùng
                SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
//lưu theo dạng phân rã
                editor.putString("email",inputEmail.getText().toString());
                editor.putString("password",inputPassword.getText().toString());
                editor.putBoolean("Save",check_save.isChecked());
                editor.commit();







//authenticate user

                auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new
                  OnCompleteListener<AuthResult>() {

                    @Override

                 public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (!task.isSuccessful()) {
// there was an error
                                            if (password.length() < 6) {
                                                inputPassword.setError(getString(R.string.minimum_password));
                                            } else {
                                                Toast.makeText(LoginActivity.this,
                                                        getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Intent intent = new
                                                    Intent(LoginActivity.this, FragmentMain.class);
                                            startActivity(intent);

                                            finish();

                                        }
                                    }
                                });
            }

        });
    }

    protected void onResume() {
        super.onResume();
        //hiển thị thông tin đã được lưu
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu, MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", ""); // Sửa "passworld" thành "password"
        boolean save = sharedPreferences.getBoolean("Save", false); // Đổi "save" thành "Save"
        if (save) { // Nếu save == true
            inputEmail.setText(email);
            inputPassword.setText(password);
        }
    }

}
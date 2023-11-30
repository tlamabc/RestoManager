package com.droidfreshsquad.poly2023.datve;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.R;

import java.util.Random;
import java.util.UUID;

public class SpinWheelActivity extends AppCompatActivity {
    private ImageView imgBack;

    private Button buttonSpin;
    private View imageSpinWheel;
    SharedPreferences preferences;
    SharedPreferences preferences2;

    private int degrees = 0;
    private static final int ROTATE_FULL_CIRCLE = 360;
    private static final int MAX_SPINS_BEFORE_COOLDOWN = 2;

    private static final String SPIN_COUNT = "spin_count";
    private static final String LAST_SPIN_TIMESTAMP = "last_spin_timestamp";
    private static final long SPIN_COOLDOWN_DURATION = 24 * 60 * 60 * 1000; // 24 giờ trong milliseconds

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spin_wheel_layout);

        preferences = getSharedPreferences("MyPrefss", MODE_PRIVATE); // Khởi tạo preferences ở onCreate
        preferences2 = getSharedPreferences("MyPrefss", MODE_PRIVATE); // Khởi tạo preferences ở onCreate

        buttonSpin = findViewById(R.id.buttonSpin);
        imageSpinWheel = findViewById(R.id.imageSpinWheel);
        imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinWheel();
            }
        });
    }

    private void spinWheel() {
        SharedPreferences preferences = getSharedPreferences("MyPrefss", MODE_PRIVATE);
        int spinCount = preferences.getInt(SPIN_COUNT, 0);

        if (spinCount < MAX_SPINS_BEFORE_COOLDOWN) {
            // Nếu chưa quay đủ 2 lần, thực hiện quay vòng và tăng biến đếm
            performSpin();
            spinCount++;
            saveSpinCount(preferences, spinCount);
        } else {
            // Đã quay đủ 2 lần, kiểm tra thời gian chờ
            long currentTime = System.currentTimeMillis();
            long lastSpinTime = preferences.getLong(LAST_SPIN_TIMESTAMP, 0);

            if (currentTime - lastSpinTime >= SPIN_COOLDOWN_DURATION) {
                // Đã qua thời gian chờ, thực hiện quay vòng và đặt lại biến đếm và thời gian
                performSpin();
                saveSpinCount(preferences, 1);
                saveLastSpinTime(preferences, currentTime);
            } else {
                // Chưa qua thời gian chờ
                long remainingTime = SPIN_COOLDOWN_DURATION - (currentTime - lastSpinTime);
                long hours = remainingTime / (60 * 60 * 1000);
                long minutes = (remainingTime % (60 * 60 * 1000)) / (60 * 1000);

                String message = "Vui lòng đợi " + hours + " giờ " + minutes + " phút trước khi quay tiếp.";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void performSpin() {
        Random random = new Random();
        int degreesToRotate = random.nextInt(ROTATE_FULL_CIRCLE) + ROTATE_FULL_CIRCLE * 5;

        RotateAnimation rotateAnimation = new RotateAnimation(
                degrees, degrees + degreesToRotate,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(2000);
        rotateAnimation.setFillAfter(true);

        degrees += degreesToRotate;

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                buttonSpin.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                buttonSpin.setEnabled(true);

                int selectedSector = (degrees % ROTATE_FULL_CIRCLE) / 90;

                switch (selectedSector) {
                    case 0:
                        String code1 = generateRandomCode();
                        saveCodeToList(preferences, code1);
                        Toast.makeText(SpinWheelActivity.this, "Chúc mừng bạn đã trúng mã giảm giá 10%: " + code1, Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(SpinWheelActivity.this, "chúc bạn may mắn lần sau", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        String code2 = generateRandomCode();
                        saveCodeToList(preferences, code2);
                        Toast.makeText(SpinWheelActivity.this, "Chúc mừng bạn đã trúng mã giảm giá 10%: " + code2, Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(SpinWheelActivity.this, "chúc bạn may mắn lần sau", Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            private String generateRandomCode() {
                String uuid = UUID.randomUUID().toString();
                return uuid.substring(0, 5);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        imageSpinWheel.startAnimation(rotateAnimation);
    }
    private void saveCodeToList(SharedPreferences preferences, String code) {
        SharedPreferences.Editor editor = preferences.edit();
        int listSize = preferences.getInt("code_list_size", 0);

        editor.putString("code_" + listSize, code);
        editor.putInt("code_list_size", listSize + 1);
        editor.apply();
    }

    private void saveSpinCount(SharedPreferences preferences, int spinCount) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(SPIN_COUNT, spinCount);
        editor.apply();
    }

    private void saveLastSpinTime(SharedPreferences preferences, long currentTime) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(LAST_SPIN_TIMESTAMP, currentTime);
        editor.apply();
    }
}
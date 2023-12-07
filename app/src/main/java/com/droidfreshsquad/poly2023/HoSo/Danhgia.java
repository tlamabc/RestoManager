package com.droidfreshsquad.poly2023.HoSo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.droidfreshsquad.poly2023.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class Danhgia extends AppCompatActivity {
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhgia);
        //thanh tiêu đề
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setTitle("Đánh giá");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ImageView image1 = findViewById(R.id.image1);
        ImageView image11 = findViewById(R.id.image11);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image22 = findViewById(R.id.image22);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image33 = findViewById(R.id.image33);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image44 = findViewById(R.id.image44);
        ImageView image5 = findViewById(R.id.image5);
        ImageView image55 = findViewById(R.id.image55);
        ImageView sao0 = findViewById(R.id.sao0);
        ImageView sao1 = findViewById(R.id.sao1);
        ImageView sao2 = findViewById(R.id.sao2);
        ImageView sao3 = findViewById(R.id.sao3);
        ImageView sao4 = findViewById(R.id.sao4);
        ImageView sao5 = findViewById(R.id.sao5);
        TextView giu = findViewById(R.id.giu);
        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = calculateRating();
                updateUIWithRating(rating);
                if (image1.getVisibility() == View.INVISIBLE || image1.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.INVISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                    image4.setVisibility(View.INVISIBLE);
                    image5.setVisibility(View.INVISIBLE);
                    sao1.setVisibility(View.VISIBLE);
                    sao0.setVisibility(View.INVISIBLE);
                    sao2.setVisibility(View.INVISIBLE);
                    sao3.setVisibility(View.INVISIBLE);
                    sao4.setVisibility(View.INVISIBLE);
                    sao5.setVisibility(View.INVISIBLE);
                }
            }
        });
        image22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = calculateRating();
                updateUIWithRating(rating);
                if (image2.getVisibility() == View.INVISIBLE || image2.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.INVISIBLE);
                    image4.setVisibility(View.INVISIBLE);
                    image5.setVisibility(View.INVISIBLE);
                    sao0.setVisibility(View.INVISIBLE);
                    sao1.setVisibility(View.INVISIBLE);
                    sao2.setVisibility(View.VISIBLE);
                    sao3.setVisibility(View.INVISIBLE);
                    sao4.setVisibility(View.INVISIBLE);
                    sao5.setVisibility(View.INVISIBLE);
                }
            }
        });
        image33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = calculateRating();
                updateUIWithRating(rating);
                if (image3.getVisibility() == View.INVISIBLE || image3.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.INVISIBLE);
                    image5.setVisibility(View.INVISIBLE);
                    sao0.setVisibility(View.INVISIBLE);
                    sao1.setVisibility(View.INVISIBLE);
                    sao2.setVisibility(View.INVISIBLE);
                    sao3.setVisibility(View.VISIBLE);
                    sao4.setVisibility(View.INVISIBLE);
                    sao5.setVisibility(View.INVISIBLE);
                }
            }
        });
        image44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = calculateRating();
                updateUIWithRating(rating);
                if (image4.getVisibility() == View.INVISIBLE || image4.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    image5.setVisibility(View.INVISIBLE);
                    sao0.setVisibility(View.INVISIBLE);
                    sao1.setVisibility(View.INVISIBLE);
                    sao2.setVisibility(View.INVISIBLE);
                    sao3.setVisibility(View.INVISIBLE);
                    sao4.setVisibility(View.VISIBLE);
                    sao5.setVisibility(View.INVISIBLE);
                }
            }
        });
        image55.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rating = calculateRating();
                updateUIWithRating(rating);
                if (image5.getVisibility() == View.INVISIBLE || image5.getVisibility() == View.VISIBLE) {
                    image1.setVisibility(View.VISIBLE);
                    image2.setVisibility(View.VISIBLE);
                    image3.setVisibility(View.VISIBLE);
                    image4.setVisibility(View.VISIBLE);
                    image5.setVisibility(View.VISIBLE);
                    sao0.setVisibility(View.INVISIBLE);
                    sao1.setVisibility(View.INVISIBLE);
                    sao2.setVisibility(View.INVISIBLE);
                    sao3.setVisibility(View.INVISIBLE);
                    sao4.setVisibility(View.INVISIBLE);
                    sao5.setVisibility(View.VISIBLE);
                }
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("danh_gia");
        giu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rating = calculateRating();
                String danhgia = ((EditText) findViewById(R.id.editTextdanhgia)).getText().toString();
                String emailValue = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String danhgiaId = mDatabase.child("danh_gia").push().getKey();
                DatabaseReference userRef = mDatabase.child(danhgiaId);
                // Write data to the database
                userRef.child("Email").setValue(emailValue);
                userRef.child("Sao").setValue(rating);
                userRef.child("DanhGia").setValue(danhgia)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {

                            @Override
                            public void onSuccess(Void aVoid) {
                                // Data has been written successfully, now read it
                                readDataFromDatabase(danhgiaId);
                            }
                        });
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(giu.getContext(), "Cảm ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, 500);
            }
            private int calculateRating() {
                int totalVisible = 0;

                if (image1.getVisibility() == View.VISIBLE) totalVisible++;
                if (image2.getVisibility() == View.VISIBLE) totalVisible++;
                if (image3.getVisibility() == View.VISIBLE) totalVisible++;
                if (image4.getVisibility() == View.VISIBLE) totalVisible++;
                if (image5.getVisibility() == View.VISIBLE) totalVisible++;

                // Calculate the average rating based on the total visible images
                if (totalVisible > 0) {
                    return totalVisible;
                } else {
                    // Default rating if no images are visible
                    return 0;
                }
            }

            private void readDataFromDatabase(String userId) {
                DatabaseReference userRef = mDatabase.child(userId);
                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int savedRating = dataSnapshot.child("Sao").getValue(Integer.class);
                            String savedDanhGia = dataSnapshot.child("DanhGia").getValue(String.class);
                            updateUIWithRating(savedRating);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle the error if needed
                    }
                });
            }
        });
    }
    private int calculateRating() {
        return 0;
    }

    private void updateUIWithRating(int rating
    ) {
    }
}
package com.droidfreshsquad.poly2023.Fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.droidfreshsquad.poly2023.HoSo.BaoCao;
import com.droidfreshsquad.poly2023.HoSo.Danh_Gia;
import com.droidfreshsquad.poly2023.HoSo.DieuKhoan;
import com.droidfreshsquad.poly2023.LoginActivity;
import com.droidfreshsquad.poly2023.R;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidfreshsquad.poly2023.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.BuildConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.annotations.Nullable;

public class HosoFragment extends Fragment {
    private FirebaseAuth auth;
    private static final int PICK_IMAGE_REQUEST = 1;

    private TextView versionTextView;
    private TextView txtName;
    private ImageView imgAvatar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String displayName = user.getDisplayName();
            String email = user.getEmail();
            Uri avatarUri = user.getPhotoUrl();
            user.updateProfile(
                    new UserProfileChangeRequest.Builder()
                            .setDisplayName(email)

                            .build()
            );
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hoso, container, false);
// chức năng đăng xuất
        auth = FirebaseAuth.getInstance();  // Khởi tạo Firebase Authentication
        ConstraintLayout signOutButton = view.findViewById(R.id.sign_out_button);
        ConstraintLayout danhgia = view.findViewById(R.id.danhgia);
        ConstraintLayout dieukhoan = view.findViewById(R.id.dieukhoan);
        ConstraintLayout baocao = view.findViewById(R.id.baocao);
        txtName = view.findViewById(R.id.textView5);
        imgAvatar = view.findViewById(R.id.avatarView);

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                // Chuyển người dùng trở lại màn hình đăng nhập
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        dieukhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DieuKhoan.class);
                startActivity(intent);
            }
        });
        baocao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BaoCao.class);
                startActivity(intent);
            }
        });

//mật khẩu và bảo mật
        ConstraintLayout ctlcaidat = view.findViewById(R.id.ctlcaidat);
        ctlcaidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
// nut đánh giá
        danhgia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Danh_Gia dialog = new Danh_Gia(HosoFragment.this);
                dialog.handleDialogEvents();
                dialog.show();
            }
        });
// Lấy phiên bản từ BuildConfig và hiển thị nó trên TextView
        String versionName = BuildConfig.VERSION_NAME;
        versionTextView = view.findViewById(R.id.versionTextView);
        versionTextView.setText("Phiên bản: " + "1.0.0" + " by DroidFresh Sixz"
                );
        // Lấy tên và ảnh của người dùng
        String username = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Uri photoUrl = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl();
        txtName.setText(username);
//        imgAvatar.setImageURI(photoUrl);
        Glide.with(this).load(photoUrl).error(R.drawable.logochim).into(imgAvatar);
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
                // Implement the logic for updating the avatar here
                // You can open a dialog, start a new activity, or perform any action based on your requirements
            }
        });
        return view;
    }

    // Add this method to handle the result from the image picker
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            // Get the selected image URI
            Uri selectedImageUri = data.getData();

            // Update the user's profile with the new avatar URL
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setPhotoUri(selectedImageUri)
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Update the ImageView with the new avatar
                                Glide.with(getContext()).load(selectedImageUri).error(R.drawable.logochim).into(imgAvatar);
                                Toast.makeText(getContext(), "Avatar updated successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "Failed to update avatar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}







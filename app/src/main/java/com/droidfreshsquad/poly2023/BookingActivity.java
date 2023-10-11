package com.droidfreshsquad.poly2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BookingActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        List<String> mangquancao = new ArrayList<>();
        mangquancao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fwww.vietnamairlines.com%2F~%2Fmedia%2FFiles%2FVNANewPage-Images%2FLotusmiles%2FEarn%2520Miles%2FPage%2FTren_Vietnamairlines.jpg&tbnid=9YG4P5X_WDiBeM&vet=12ahUKEwiI6sKdhe6BAxXXMHAKHf_NCQ4QMygFegQIARBy..i&imgrefurl=https%3A%2F%2Fwww.vietnamairlines.com%2Fno%2Fvi%2Flotusmile%2Fearn-miles%2Fearn-miles-with-vietnam-airlines&docid=6LiROrNVqixh-M&w=1000&h=342&q=vietnam%20airline&ved=2ahUKEwiI6sKdhe6BAxXXMHAKHf_NCQ4QMygFegQIARBy");
        mangquancao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fvj-prod-website-cms.s3.ap-southeast-1.amazonaws.com%2Fvietjet-aircraft-1669708588791.jpg&tbnid=oP2NjKC5tOU4LM&vet=12ahUKEwicu4Oqhe6BAxXFfXAKHe7UDpIQMygBegQIARBE..i&imgrefurl=https%3A%2F%2Fwww.vietjetair.com%2Fvi%2Fpages%2Fvietjet-doat-3-giai-thuong-quoc-te-uy-tin-cua-skytrax-va-world-business-outlook-1669708669552&docid=3FQKPYJkVjkDNM&w=1600&h=1066&q=vietjet&ved=2ahUKEwicu4Oqhe6BAxXFfXAKHe7UDpIQMygBegQIARBE");
        mangquancao.add("https://www.google.com.vn/imgres?imgurl=https%3A%2F%2Fvj-prod-website-cms.s3.ap-southeast-1.amazonaws.com%2Fvietjet-aircraft-1669708588791.jpg&tbnid=oP2NjKC5tOU4LM&vet=12ahUKEwicu4Oqhe6BAxXFfXAKHe7UDpIQMygBegQIARBE..i&imgrefurl=https%3A%2F%2Fwww.vietjetair.com%2Fvi%2Fpages%2Fvietjet-doat-3-giai-thuong-quoc-te-uy-tin-cua-skytrax-va-world-business-outlook-1669708669552&docid=3FQKPYJkVjkDNM&w=1600&h=1066&q=vietjet&ved=2ahUKEwicu4Oqhe6BAxXFfXAKHe7UDpIQMygBegQIARBE");
        for (int i = 0; i<mangquancao.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquancao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
}
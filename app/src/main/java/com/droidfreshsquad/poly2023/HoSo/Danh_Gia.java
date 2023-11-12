package com.droidfreshsquad.poly2023.HoSo;
import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidfreshsquad.poly2023.Fragment.HomeFragment;
import com.droidfreshsquad.poly2023.Fragment.HosoFragment;
import com.droidfreshsquad.poly2023.R;

public class Danh_Gia {
    private Dialog dialog;

    public Danh_Gia(HosoFragment context) {
        dialog = new Dialog(context.requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_danh_gia);
    }
    public Danh_Gia(HomeFragment context) {
        dialog = new Dialog(context.requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_danh_gia);
    }

    public void show() {
        dialog.show();
    }

    public void handleDialogEvents() {
        ImageView image1 = dialog.findViewById(R.id.image1);
        ImageView image11 = dialog.findViewById(R.id.image11);
        ImageView image2 = dialog.findViewById(R.id.image2);
        ImageView image22 = dialog.findViewById(R.id.image22);
        ImageView image3 = dialog.findViewById(R.id.image3);
        ImageView image33 = dialog.findViewById(R.id.image33);
        ImageView image4 = dialog.findViewById(R.id.image4);
        ImageView image44 = dialog.findViewById(R.id.image44);
        ImageView image5 = dialog.findViewById(R.id.image5);
        ImageView image55 = dialog.findViewById(R.id.image55);
        ImageView sao0 = dialog.findViewById(R.id.sao0);
        ImageView sao1 = dialog.findViewById(R.id.sao1);
        ImageView sao2 = dialog.findViewById(R.id.sao2);
        ImageView sao3 = dialog.findViewById(R.id.sao3);
        ImageView sao4 = dialog.findViewById(R.id.sao4);
        ImageView sao5 = dialog.findViewById(R.id.sao5);
        TextView giu = dialog.findViewById(R.id.giu);


        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        giu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Sử dụng Handler để đặt độ trễ 0,5 giây trước khi đóng dialog
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(giu.getContext(), "Cảm ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show(); // Sử dụng contexttoast.setIcon(null);
                        dialog.dismiss();
                    }
                }, 500);
            }
        });
    }
}

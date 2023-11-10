package com.droidfreshsquad.poly2023.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.droidfreshsquad.poly2023.Domain.ExploreDomain;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.ScreenExplore.ScreenDanang;
import com.droidfreshsquad.poly2023.ScreenExplore.ScreenHanoi;
import com.droidfreshsquad.poly2023.ScreenExplore.ScreenSaigon;

import java.util.ArrayList;
import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHodel> {

    private List<ExploreDomain> mexploreDomains;



    public ExploreAdapter(ArrayList<ExploreDomain> exploreDomains) {
        this.exploreDomains = exploreDomains;
    }

    ArrayList<ExploreDomain> exploreDomains;

    @NonNull
    @Override
    public ExploreAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewhodel_explore,parent,false);
        return new ViewHodel(infalate);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ViewHodel holder, int position) {
        View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ScreenDanang.class);
                context.startActivity(intent);
            }
        };
        View.OnClickListener myClickListener1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ScreenSaigon.class);
                context.startActivity(intent);
            }
        };
        View.OnClickListener myClickListener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ScreenHanoi.class);
                context.startActivity(intent);
            }
        };

        holder.exploreName.setText(exploreDomains.get(position).getTitle());
        holder.exploreName1.setText(exploreDomains.get(position).getTitle1());
        holder.exploreName1.setOnClickListener(myClickListener);
        String picUrl = "";
        switch (position) {
            case 0: {
                picUrl = "imgexplore1";
                holder.explorePic.setImageResource(R.drawable.imgexplore1);
                holder.explorePic.setOnClickListener(myClickListener);
                break;
            }
            case 1: {
                picUrl = "imgexplore2";

                holder.explorePic.setImageResource(R.drawable.imgexplore2);
                holder.explorePic.setOnClickListener(myClickListener1);
                break;
            }
            case 2: {
                picUrl = "imgexplore3";

                holder.explorePic.setImageResource(R.drawable.imgexplore3);
                holder.explorePic.setOnClickListener(myClickListener2);
                break;
            }
        }
        int drawableResourceID = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceID).into(holder.explorePic);
    }

    @Override
    public int getItemCount() {
        return exploreDomains.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView exploreName;
        TextView exploreName1;
        ImageView explorePic;
        ConstraintLayout mainLayout;
        ConstraintLayout item_layout;



        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            exploreName = itemView.findViewById(R.id.exploreName);
            exploreName1 = itemView.findViewById(R.id.exploreName1);
            explorePic = itemView.findViewById(R.id.explorePic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            item_layout = itemView.findViewById(R.id.item_layout);
        }
    }
}

package com.droidfreshsquad.poly2023.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.droidfreshsquad.poly2023.Domain.ExploreDomain;
import com.droidfreshsquad.poly2023.R;

import java.util.ArrayList;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHodel> {
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
        holder.exploreName.setText(exploreDomains.get(position).getTitle());
        holder.exploreName1.setText(exploreDomains.get(position).getTitle1());
        String picUrl="";
        switch (position){
            case 0:{
                picUrl = "explore2";
                              holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.explore1_background));

                break;
            }
            case 1:{
                picUrl = "explore2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.explore1_background));

                break;
            }
            case 2:{
                picUrl = "explore2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.explore1_background));

                break;
            }
            case 3:{
                picUrl = "explore2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.explore1_background));

                break;
            }
        }
        int drawableResourceID = holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceID).into(holder.explorePic);
    }

    @Override
    public int getItemCount() {
        return exploreDomains.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        TextView exploreName;
        TextView exploreName1;
        ConstraintLayout mainLayout;
        ImageView explorePic;

        public ViewHodel(@NonNull View itemView) {
            super(itemView);
            exploreName = itemView.findViewById(R.id.exploreName);
            exploreName1 = itemView.findViewById(R.id.exploreName1);
            explorePic = itemView.findViewById(R.id.explorePic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

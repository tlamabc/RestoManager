package com.droidfreshsquad.poly2023.Fragment;
import com.droidfreshsquad.poly2023.R;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    public List<Message> getMessages;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.getMessages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = getMessages.get(position);

        TextView tvSender = holder.tvSender;
        tvSender.setText(message.sender);

        TextView tvContent = holder.tvContent;
        tvContent.setText(message.content);
    }

    @Override
    public int getItemCount() {
        return getMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvSender;
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSender = itemView.findViewById(R.id.tv_sender);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}

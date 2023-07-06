package com.example.collegeappuser.UI.notice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collegeappuser.R;
import com.example.collegeappuser.fullImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class noticeAdapter extends RecyclerView.Adapter<noticeAdapter.noticeViewAdapter> {
    private Context context;

    private ArrayList<noticeData> list;


    public noticeAdapter(Context context, ArrayList<noticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public noticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_feed_item, parent, false);
        return new noticeViewAdapter(view);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onBindViewHolder(@NonNull noticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        noticeData currentItem = list.get(position);
        holder.deleteNoticeTitle.setText(currentItem.getTitle());
        holder.time.setText(currentItem.getTime());
        holder.date.setText(currentItem.getData());
        try {
            if (currentItem.getImage() != null)
                Glide.with(context).load(currentItem.getImage()).into(holder.deleteNoticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.deleteNoticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,fullImageView.class);
                intent.putExtra("image",currentItem.getImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class noticeViewAdapter extends RecyclerView.ViewHolder {
        private TextView deleteNoticeTitle, time,date;
        private ImageView deleteNoticeImage;

        public noticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            deleteNoticeTitle = itemView.findViewById(R.id.deleteNoticeTitle);
            deleteNoticeImage = itemView.findViewById(R.id.deleteNoticeImage);
            date=itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}

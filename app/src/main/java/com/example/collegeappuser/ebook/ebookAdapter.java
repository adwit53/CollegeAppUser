package com.example.collegeappuser.ebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collegeappuser.R;

import java.util.List;

public class ebookAdapter extends RecyclerView.Adapter<ebookAdapter.ebookViewHolder>{
    private Context context;
    private List<ebookData> list;

    public ebookAdapter(Context context, List<ebookData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ebookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.ebook_item_layout,parent,false);
        return new ebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ebookViewHolder holder, int position) {
    holder.ebookName.setText(list.get(position).getName());
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "opening", Toast.LENGTH_SHORT).show();
        }
    });
    holder.ebookDownload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(context, "Downloading", Toast.LENGTH_SHORT).show();
        }
    });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ebookViewHolder extends RecyclerView.ViewHolder {
    private TextView ebookName;
    private ImageView ebookDownload;
        public ebookViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookName=itemView.findViewById(R.id.ebookName);
            ebookDownload=itemView.findViewById(R.id.ebookDownload);
        }
    }
}

package com.example.collegeappuser.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.collegeappuser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ebookActivity extends AppCompatActivity {
private RecyclerView ebookRecycler;
private DatabaseReference reference;
private List<ebookData> list;
private ebookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ebook);

        ebookRecycler=findViewById(R.id.ebookRecycler);
        reference= FirebaseDatabase.getInstance().getReference().child("pdf");
        getData();
    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list=new ArrayList<>();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    ebookData d=data.getValue(ebookData.class);
                    list.add(d);
                }
                adapter=new ebookAdapter(ebookActivity.this,list);
                ebookRecycler.setLayoutManager(new LinearLayoutManager(ebookActivity.this));
                ebookRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ebookActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
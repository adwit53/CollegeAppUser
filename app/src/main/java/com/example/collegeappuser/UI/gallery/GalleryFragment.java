package com.example.collegeappuser.UI.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collegeappuser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GalleryFragment extends Fragment {

    RecyclerView convoRecycler, otherRecycler;
    galleryAdapter adapter;
    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        convoRecycler = view.findViewById(R.id.convoRecycler);
        otherRecycler = view.findViewById(R.id.otherRecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("gallery");
        getConvoImage();
        getOtherImage();
        return view;
    }

    private void getOtherImage() {
        reference.child("Gravitas").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String imageURL = data.getValue(String.class);
                        imageList.add(imageURL);
                    }
                    adapter = new galleryAdapter(getContext(), imageList);
                    otherRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    otherRecycler.setAdapter(adapter);
                } else {
                    // Handle the case when "Gravitas" node does not exist
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error case if necessary
            }
        });
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    // Check if the data is a String or a HashMap
                    if (data.getValue() instanceof String) {
                        String imageURL = data.getValue(String.class);
                        imageList.add(imageURL);
                    } else if (data.getValue() instanceof HashMap) {
                        // Handle the case when the data is a HashMap
                        HashMap<String, Object> imageData = (HashMap<String, Object>) data.getValue();
                        String imageURL = (String) imageData.get("imageURL");
                        imageList.add(imageURL);
                    }
                }
                adapter = new galleryAdapter(getContext(), imageList);
                convoRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                convoRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error case if necessary
            }
        });
    }

}

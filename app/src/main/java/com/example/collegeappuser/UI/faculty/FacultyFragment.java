package com.example.collegeappuser.UI.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.collegeappuser.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {
    private RecyclerView csDepartment, mechanical,electrical;
    private LinearLayout csNoData,mechNoDta,elecNoData;
    private List<TeacherData> list1,list2,list3;
    private DatabaseReference reference,dbRef;
    private TeacherAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);
        csDepartment=view.findViewById(R.id.csDepartment);
        mechanical=view.findViewById(R.id.mechanicalDepartment);
        electrical=view.findViewById(R.id.eeeDepartment);
        csNoData=view.findViewById(R.id.csNoData);
        mechNoDta=view.findViewById(R.id.mechanicalNoData);
        elecNoData=view.findViewById(R.id.eeeNoData);
        reference= FirebaseDatabase.getInstance().getReference().child("teacher");
        csDepartment();
        mechanicalDepartment();
        electricalDepartment();
        return view;
    }

    private void csDepartment() {
        dbRef=reference.child("CS");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1=new ArrayList<>();
                if(!snapshot.exists())
                {
                    csNoData.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }
                else {
                    csNoData.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap:snapshot.getChildren())
                    {
                        TeacherData data=snap.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list1,getContext());
                    csDepartment.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void electricalDepartment() {
        dbRef=reference.child("Electrical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3=new ArrayList<>();
                if(!snapshot.exists())
                {
                    elecNoData.setVisibility(View.VISIBLE);
                    electrical.setVisibility(View.GONE);
                }
                else {
                    elecNoData.setVisibility(View.GONE);
                    electrical.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap:snapshot.getChildren())
                    {
                        TeacherData data=snap.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    electrical.setHasFixedSize(true);
                    electrical.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list3,getContext());
                    electrical.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void mechanicalDepartment() {
        dbRef=reference.child("Mechanical");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2=new ArrayList<>();
                if(!snapshot.exists())
                {
                    mechNoDta.setVisibility(View.VISIBLE);
                    mechanical.setVisibility(View.GONE);
                }
                else {
                    mechNoDta.setVisibility(View.GONE);
                    mechanical.setVisibility(View.VISIBLE);
                    for(DataSnapshot snap:snapshot.getChildren())
                    {
                        TeacherData data=snap.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    mechanical.setHasFixedSize(true);
                    mechanical.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter=new TeacherAdapter(list2,getContext());
                    mechanical.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }



}

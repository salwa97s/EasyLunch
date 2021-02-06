package com.example.easylunchapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.easylunchapp.R;
import com.example.easylunchapp.Settings_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_Fragment extends Fragment {

    private TextView Name , Email  , City , School ,Role  , Welcome;
    private ImageView Settings , Crown ,ProfilePic;
    private View v ;
    private FirebaseDatabase database;
    private DatabaseReference myref ;
    private String uid ,role;

    public Profile_Fragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_profile_, container, false);

        GetIds();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference("user");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("id").getValue().equals(uid)){
                        Name.setText(ds.child("name").getValue(String.class));
                        Email.setText(ds.child("email").getValue(String.class));
                        City.setText(ds.child("city").getValue(String.class));
                        School.setText(ds.child("school").getValue(String.class));
                        Role.setText(ds.child("role").getValue(String.class));
                        role=(ds.child("role").getValue(String.class));
                        Welcome.setText("Welcome "+ds.child("name").getValue(String.class));
                    }
                }
                if(role.equals("Manager")){
                    Crown.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext() , Settings_Activity.class);
                startActivity(intent);
            }
        });

        return v ;
    }

    public void GetIds(){

        Name=(TextView)v.findViewById(R.id.profile_name_id);
        Email=(TextView)v.findViewById(R.id.profile_email_id);
        City=(TextView)v.findViewById(R.id.profile_city_id);
        School=(TextView)v.findViewById(R.id.profile_school_id);
        Role=(TextView)v.findViewById(R.id.profile_role_id);
        Welcome=(TextView)v.findViewById(R.id.profile_welcome_id);

        Settings=(ImageView)v.findViewById(R.id.prodile_settings_id);
        Crown=(ImageView)v.findViewById(R.id.profile_crown_id);
    }

}

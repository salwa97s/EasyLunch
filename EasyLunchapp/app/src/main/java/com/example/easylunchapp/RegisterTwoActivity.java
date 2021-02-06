package com.example.easylunchapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterTwoActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myref ,myref1 ;
    private TextView name , city , school , countertxt ;
    private String uid ;
    private int counter = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);


        name=(TextView)findViewById(R.id.infoname_id);
        city=(TextView)findViewById(R.id.infocity_id);
        school=(TextView)findViewById(R.id.infoschool_id);
        countertxt = (TextView)findViewById(R.id.counterid);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        name.setText(uid);

        database = FirebaseDatabase.getInstance();
        myref = database.getReference("user");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("id").getValue().equals(uid)){
                        name.setText(ds.child("name").getValue(String.class));
                        city.setText(ds.child("city").getValue(String.class));
                        school.setText(ds.child("school").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
       /* myref1 = database.getReference("Requests");

        myref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.getValue().equals(uid)){
                        for(DataSnapshot ds2 : dataSnapshot.getChildren()) {
                            name.setText(ds2.child("name").getValue(String.class));
                            city.setText(ds2.child("city").getValue(String.class));
                            school.setText(ds2.child("school").getValue(String.class));
                            counter++;
                        }
                    }
                }
                countertxt.setText(String.valueOf(counter));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}

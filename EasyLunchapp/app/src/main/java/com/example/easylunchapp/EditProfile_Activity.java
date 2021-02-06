package com.example.easylunchapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.Classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfile_Activity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myref;
    private String uid , RoleS;
    private EditText Name, Email, City, School ;
    private TextView Role ;
    private Button save  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_);

        Name = (EditText) findViewById(R.id.edit_name_id);
        Email = (EditText) findViewById(R.id.edit_email_id);
        City = (EditText) findViewById(R.id.edit_city_id);
        School = (EditText) findViewById(R.id.edit_school_id);
        save=(Button)findViewById(R.id.edit_save_btn_id);
        Role = (TextView)findViewById(R.id.edit_Role_id);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference("user");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("id").getValue().equals(uid)) {
                        Name.setText(ds.child("name").getValue(String.class));
                        Email.setText(ds.child("email").getValue(String.class));
                        City.setText(ds.child("city").getValue(String.class));
                        School.setText(ds.child("school").getValue(String.class));
                        Role.setText(ds.child("role").getValue(String.class));
                        RoleS = (ds.child("role").getValue(String.class)) ;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDataBase();
                Toast.makeText(EditProfile_Activity.this, "Information Saved",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SetDataBase() {

        // Write a message to the database
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        int i;
        String o = "1";

        User USER = new User(Name.getText().toString(), Email.getText().toString(), "00", City.getText().toString(), School.getText().toString(),RoleS, uid);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("user");
        DatabaseReference yourRef = rootRef.child(uid);
        yourRef.setValue(USER);


    }
}
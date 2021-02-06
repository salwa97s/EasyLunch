package com.example.easylunchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.Begain.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings_Activity extends AppCompatActivity {

    private TextView Edit , Promote , Signout ;
    private FirebaseDatabase database;
    private DatabaseReference myref ;
    private String uid , Role ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_);

        Edit=(TextView)findViewById(R.id.setting_edit_id);
        Promote=(TextView)findViewById(R.id.setting_promote_id);
        Signout=(TextView)findViewById(R.id.setting_signout_id);

        premession();

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Settings_Activity.this , EditProfile_Activity.class);
                startActivity(intent);
            }
        });

        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (Settings_Activity.this , LoginActivity.class);
                startActivity(intent);
            }
        });

        Promote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Role.equals("Manager")){
                    Intent intent = new Intent (Settings_Activity.this , Report_Activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Settings_Activity.this, "ERROR",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void premession(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference("user");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("id").getValue().equals(uid)){
                        Role=(ds.child("role").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

package com.example.easylunchapp.Begain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button BtnWelcome , selva ,cool;
    private List<String> StaffLst ;
    private ImageView img ;
    private AnimationDrawable rocketAnimation;
    private static int Splash_Time = 4 ; //4900

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* BtnWelcome=(Button)findViewById(R.id.welcome_btn_id);
        selva = (Button)findViewById(R.id.selvaid);
        cool = (Button)findViewById(R.id.cool);

       /* BtnWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

       /* selva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent (MainActivity.this , MenuActivity.class);
                startActivity(x);
            }
        });

        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent (MainActivity.this , yoyo.class);
                startActivity(x);
            }
        });*/

        img=(ImageView)findViewById(R.id.animatedimg);
        img.setBackgroundResource(R.drawable.animation);
        rocketAnimation = (AnimationDrawable) img.getBackground();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
                finish();
            }
        },Splash_Time);

        CreateStaffList();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        rocketAnimation.start();
    }

    public void start (){
        Intent x = new Intent (MainActivity.this , LoginActivity.class);
        startActivity(x);

    }

    public void CreateStaffList(){
        StaffLst = new ArrayList<>();
        StaffLst.add("majd@hotmail.com");
        StaffLst.add("salwa@hotmail.com");
        StaffLst.add("efi@hotmail.com");
        StaffLst.add("nora@hotmail.com");
        SharedPreferences sp  = getSharedPreferences("STAFF" , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(StaffLst);
        editor.putString("Emails",json);
        editor.apply();
    }
}

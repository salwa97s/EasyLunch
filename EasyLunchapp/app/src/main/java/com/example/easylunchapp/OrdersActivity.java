package com.example.easylunchapp;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easylunchapp.Fragments.OrderF_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OrdersActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav ;
    private FrameLayout mMainFrame ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_main);

        mMainFrame = (FrameLayout)findViewById(R.id.Fragment_orders_id);

        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_orders_id,new OrderF_Fragment()).commit();




    }
}

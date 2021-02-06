package com.example.easylunchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.easylunchapp.Fragments.Home_Fragment;
import com.example.easylunchapp.Fragments.Order_Fragment;
import com.example.easylunchapp.Fragments.Profile_Fragment;
import com.example.easylunchapp.Fragments.Search_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav ;
    private FrameLayout mMainFrame ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mMainNav = (BottomNavigationView)findViewById(R.id.btm_nav_id);
        mMainFrame = (FrameLayout)findViewById(R.id.Fragment_container_id);

        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_id,new Home_Fragment()).commit();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){

                    case R.id.MHome :
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_id,new Home_Fragment()).commit();
                        break;
                    case R.id.MSearch :
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_id,new Search_Fragment()).commit();
                        break;
                    case R.id.MOrder :
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_id,new Order_Fragment()).commit();
                        break;
                    case R.id.MProfile :
                        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container_id,new Profile_Fragment()).commit();
                        break;
                }
                return true;
            }
        });


    }
}

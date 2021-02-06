package com.example.easylunchapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.ExampleAdapter;
import com.example.easylunchapp.Classes.Food;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class yoyo extends AppCompatActivity {

    private ArrayList<Food> mExampleList;

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView MyRecViewEvents ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoyo);

        createExampleList();
        buildRecyclerView();
    }


    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        SaveData();
    }


    public void createExampleList() {
        mExampleList = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences("FOOD" , MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("FoodArray" , null);
        Type typeT = new TypeToken<ArrayList<Food>>() {}.getType();
        mExampleList = gson.fromJson(json , typeT );

    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void SaveData(){
        SharedPreferences sp  = getSharedPreferences("FOOD" , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("FoodArray",json);
        editor.apply();
    }

}
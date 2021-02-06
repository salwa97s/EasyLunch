package com.example.easylunchapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.RecviewadapterReports;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Report_Activity extends AppCompatActivity {
    private ArrayList<String> ReportsLst ;
    private RecyclerView MyRecViewReport ;
    private Button removeAll , addManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_);

        CreateReportLst();

        MyRecViewReport=(RecyclerView)findViewById(R.id.Rec_Reports_id);
        RecviewadapterReports recadapter = new RecviewadapterReports(this,ReportsLst);
        MyRecViewReport.setLayoutManager(new GridLayoutManager(this, 1)) ;
        MyRecViewReport.setAdapter(recadapter);


        removeAll=(Button)findViewById(R.id.Report_btn_remove_id);
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoveAll();
                SaveData();
                finish();
                startActivity(getIntent());
            }
        });

        addManager=(Button)findViewById(R.id.Report_btn_Add_id);
        addManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public void CreateReportLst() {
        ReportsLst = new ArrayList<>();
        SharedPreferences sp = getSharedPreferences("REPORTS" , MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonn = sp.getString("ReportsArray" , null);
        Type typeTT = new TypeToken<ArrayList<String>>() {}.getType();
        ReportsLst = gson.fromJson(jsonn , typeTT );

    }


    public void SaveData(){
        SharedPreferences sp  = getSharedPreferences("REPORTS" , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ReportsLst);
        editor.putString("ReportsArray",json);
        editor.apply();
    }

    public void RemoveAll(){
        SharedPreferences sp2 = getSharedPreferences("REPORTS" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp2.getString("ReportsArray" , null);
        Type typeT = new TypeToken<ArrayList<String>>() {}.getType();
        ReportsLst = gson.fromJson(json , typeT );
        if(ReportsLst == null){
            ReportsLst = new ArrayList<>();
        }
        ReportsLst.clear();
    }
}

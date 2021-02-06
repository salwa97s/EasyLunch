package com.example.easylunchapp.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.ExampleAdapter;
import com.example.easylunchapp.Classes.Food;
import com.example.easylunchapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Order_Fragment extends Fragment {

    View v ;
    public Order_Fragment() {}
    private List<Food> Foodlst ;
    private RecyclerView MyRecViewEvents ;
    private ArrayList<Food> mExampleList ;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button Reportbtn ;
    //userinfo
    private FirebaseDatabase database;
    private DatabaseReference myref ;
    private String uid , Name , City , School ,Email ,Report , FoodName , FoodPrice;
    private int Totalprice = 0 ;
    private ArrayList<String> ReportsLst ;
    private TextView Citytxt , Schooltxt ;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_order_, container, false);

        LoadUserInfo();
        createExampleList();
        TotalPrice();

        if(mExampleList.size()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Your Order Is Null");
            builder.setIcon(R.drawable.boxlunch);
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else {
            buildRecyclerView();
            Reportbtn = (Button) v.findViewById(R.id.Order_Report_id);
            Reportbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mExampleList.size()==0){
                        Toast.makeText(getContext(), "ERROR : Empty Order",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        CreateReport(); //text contains the user info and the orders !
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Your order have been added");
                        builder.setIcon(R.drawable.sent);
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        int size  =(mExampleList.size()) -1;
                        for(int j = size ; j>=0 ; j -- ){
                            removeItem(j);
                        }
                    }
                }
            });
        }
        return v ;
    }

    public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
        SaveData();
        TotalPrice();
    }


    public void createExampleList() {
        mExampleList = new ArrayList<>();
        SharedPreferences sp = getActivity().getSharedPreferences("FOOD" , MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString("FoodArray" , null);
        Type typeT = new TypeToken<ArrayList<Food>>() {}.getType();
        mExampleList = gson.fromJson(json , typeT );
        //if it's null do something here

    }

    public void buildRecyclerView() {
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
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
        SharedPreferences sp  = getActivity().getSharedPreferences("FOOD" , Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("FoodArray",json);
        editor.apply();
    }


    private void LoadUserInfo() {

        Citytxt = (TextView)v.findViewById(R.id.order_city_id);
        Schooltxt=(TextView)v.findViewById(R.id.order_school_id);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        database = FirebaseDatabase.getInstance();
        myref = database.getReference("user");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    if(ds.child("id").getValue().equals(uid)){
                        Name = (ds.child("name").getValue(String.class));
                        Email = (ds.child("email").getValue(String.class));
                        City =(ds.child("city").getValue(String.class));
                        School = (ds.child("school").getValue(String.class));
                        Citytxt.setText(ds.child("city").getValue(String.class));
                        Schooltxt.setText(ds.child("school").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void CreateReport() {

        Report = Name+" , "+Email+" , "+City+" , "+School+"\n" ;

        for(int i = 0 ; i<mExampleList.size();i++){
            Food currentItem = mExampleList.get(i);
            FoodName = currentItem.getFoodName();
            FoodPrice = currentItem.getPrice();
            Report = Report+"Order "+i+": "+FoodName+" , " +FoodPrice +"\n";
        }

        LoadReports();
        SaveReports();
        Report = " ";
    }


    public void SaveReports(){
        SharedPreferences sp  = getActivity().getSharedPreferences("REPORTS" ,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ReportsLst);
        editor.putString("ReportsArray",json);
        editor.apply();
    }

    public void LoadReports(){
        SharedPreferences sp2 = getActivity().getSharedPreferences("REPORTS" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp2.getString("ReportsArray" , null);
        Type typeT = new TypeToken<ArrayList<String>>() {}.getType();
        ReportsLst = gson.fromJson(json , typeT );
        if(ReportsLst == null){
            ReportsLst = new ArrayList<>();
        }
        ReportsLst.add(Report);
    }

    public void TotalPrice(){
        Totalprice = 0 ;
        for(int i = 0 ; i<mExampleList.size();i++){
            Food currentItem = mExampleList.get(i);
            FoodPrice = currentItem.getPrice();
            String price = FoodPrice;
            int num = Integer.parseInt(price);
            Totalprice+=num ;
        }
        String totalprice = Integer.toString(Totalprice);
        TextView total = (TextView)v.findViewById(R.id.order_totalprice_id);
        total.setText(totalprice);
    }


}

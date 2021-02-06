package com.example.easylunchapp.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.RecviewadapterOrderSteps;
import com.example.easylunchapp.Classes.Food;
import com.example.easylunchapp.MenuActivity;
import com.example.easylunchapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderD_Fragment extends Fragment {

    View v ;
    private List<Food> Healthylst ;
    private Button next;
    private RecyclerView MyRecViewEvents ;

    public OrderD_Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_orderd, container, false);

        createlist();
        MyRecViewEvents=(RecyclerView)v.findViewById(R.id.orderd_id);
        RecviewadapterOrderSteps recadapter = new RecviewadapterOrderSteps(getContext(),Healthylst);
        MyRecViewEvents.setLayoutManager(new GridLayoutManager(getContext() , 1));
        MyRecViewEvents.setAdapter(recadapter);

        next=(Button)v.findViewById(R.id.S_next_id);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DiaMsg();

            }
        });

        return v ;
    }

    public void Next(){
        Intent intent = new Intent(getActivity(), MenuActivity.class);
        startActivity(intent);
    }
    public void DiaMsg(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        //builder.setMessage("Allow SAVVY to access your calendar ?");
        builder.setTitle("Your order have been added");
        builder.setIcon(R.drawable.boxlunch);
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Next();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createlist(){

        Healthylst = new ArrayList<>();
        Healthylst.add(new Food("Water","5",R.drawable.water));
        Healthylst.add(new Food("Fresh Juice","8",R.drawable.orange));
        Healthylst.add(new Food("Milk","4",R.drawable.milk));

    }
}
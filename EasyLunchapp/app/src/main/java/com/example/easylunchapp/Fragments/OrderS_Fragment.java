package com.example.easylunchapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.RecviewadapterOrderSteps;
import com.example.easylunchapp.Classes.Food;
import com.example.easylunchapp.R;

import java.util.ArrayList;
import java.util.List;

public class OrderS_Fragment extends Fragment {

    View v ;
    private List<Food> Healthylst ;
    private Button next;
    private RecyclerView MyRecViewEvents ;

    public OrderS_Fragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=  inflater.inflate(R.layout.fragment_orders, container, false);

        createlist();
        MyRecViewEvents=(RecyclerView)v.findViewById(R.id.orders_id);
        RecviewadapterOrderSteps recadapter = new RecviewadapterOrderSteps(getContext(),Healthylst);
        MyRecViewEvents.setLayoutManager(new GridLayoutManager(getContext() , 1));
        MyRecViewEvents.setAdapter(recadapter);

        next=(Button)v.findViewById(R.id.S_next_id);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_orders_id,new OrderD_Fragment()).commit();
            }
        });

        return v ;
    }

    public void createlist(){

        Healthylst = new ArrayList<>();
        Healthylst.add(new Food("Apple","1",R.drawable.apple));
        Healthylst.add(new Food("Banana","1",R.drawable.banana));
        Healthylst.add(new Food("Strawberry","1",R.drawable.strawberry));
        Healthylst.add(new Food("Carrots","3",R.drawable.carrots));
        Healthylst.add(new Food("Tomato Cherry","2",R.drawable.cherry));
    }
}

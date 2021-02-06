package com.example.easylunchapp.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Adapter.RecviewadapterCategory;
import com.example.easylunchapp.R;

public class Search_Fragment extends Fragment {

    View v ;
    private RecyclerView MyRecViewCategory ;
    private int[] categorieslst = new int [] {R.drawable.healthyb , R.drawable.sanb , R.drawable.fastb , R.drawable.pastryb , R.drawable.fruitb , R.drawable.drinkb};

    public Search_Fragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_search_, container, false);

        MyRecViewCategory=(RecyclerView)v.findViewById(R.id.CategoryRec_id);
        RecviewadapterCategory recadapter = new RecviewadapterCategory(getContext(),categorieslst);
        MyRecViewCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyRecViewCategory.setLayoutManager(new GridLayoutManager(getContext(), 2)) ;
        MyRecViewCategory.setAdapter(recadapter);
        return v;
    }

}

package com.example.easylunchapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.easylunchapp.Adapter.RecviewadapterOffers;
import com.example.easylunchapp.Classes.Special;
import com.example.easylunchapp.OrdersActivity;
import com.example.easylunchapp.R;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {

    View v ;
    private ViewPager viewPager ;
    private RecyclerView MyRecViewEvents ;
    private int[] imglst = new int[]{R.drawable.addone ,R.drawable.addtwo,R.drawable.addthree } ;
    private List<Special> lstFavorite ;
    private SlidingPaneLayout slider ;
    private Button create  ;
    private ViewFlipper Flipper ;
    private RecyclerView MyRecViewOffers ;


    public Home_Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       v= inflater.inflate(R.layout.fragment_home_, container, false);

       //Flipper ADS !
        Flipper = (ViewFlipper)v.findViewById(R.id.Flipper);
       for(int img:imglst){
           flipperImages(img);
       }

        createlst();
        MyRecViewOffers=(RecyclerView)v.findViewById(R.id.Favorites_id);
        RecviewadapterOffers recadapter2 = new RecviewadapterOffers(getContext(),lstFavorite);
        MyRecViewOffers.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false )) ;
        MyRecViewOffers.setAdapter(recadapter2);

        create = (Button)v.findViewById(R.id.Order_Create_id);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent (getContext() , OrdersActivity.class);
                startActivity(x);
            }
        });

       return v ;
    }


    public void flipperImages(int image){
        ImageView imgview = new ImageView((getContext()));
        imgview.setBackgroundResource(image);

        Flipper.addView(imgview);
        Flipper.setFlipInterval(4900);
        Flipper.setAutoStart(true);

        Flipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        Flipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

    public void createlst(){

        lstFavorite = new ArrayList<>();
        lstFavorite.add(new Special(R.drawable.abokado , "Avocado" , "12sh"));
        lstFavorite.add(new Special(R.drawable.zaatar , "Zaatar" , "5sh"));
        lstFavorite.add(new Special(R.drawable.tost , "Tost" , "7sh"));
        lstFavorite.add(new Special(R.drawable.yogurt , "Yogurt" , "12sh"));

    }

}

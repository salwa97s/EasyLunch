
package com.example.easylunchapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.CategoryFoodActivity;
import com.example.easylunchapp.R;



public class RecviewadapterCategory extends RecyclerView.Adapter<RecviewadapterCategory.MyViewHolderS> {

    Context mContext ;
    int[] mData;
    public static final String SHARED_TYPE = "TYPE" ;
    public static final String TYPE = "Type";

    public RecviewadapterCategory(Context mContext, int[] mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolderS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        v= LayoutInflater.from(mContext).inflate(R.layout.cardview_pictureslide,parent,false);
        MyViewHolderS VHolder = new MyViewHolderS(v);



        return VHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderS holder, int position) {

        holder.pic.setImageResource(mData[position]);
        final int t = position ;

        holder.CategoryCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent (mContext , CategoryFoodActivity.class);
                // shared preferences
                SharedPreferences sp = mContext.getSharedPreferences(SHARED_TYPE ,mContext.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit() ;

                //saving data
                editor.putInt(TYPE , t);
                editor.apply();
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class MyViewHolderS extends RecyclerView.ViewHolder{

        ImageView pic ;
        private CardView CategoryCV ;


        public MyViewHolderS(@NonNull View itemView) {
            super(itemView);
            pic = (ImageView)itemView.findViewById(R.id.img_event);
            CategoryCV = (CardView) itemView.findViewById(R.id.categoryCV);
        }
    }
}


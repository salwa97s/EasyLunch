package com.example.easylunchapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Classes.Food;
import com.example.easylunchapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecviewadapterOrderSteps extends  RecyclerView.Adapter<RecviewadapterOrderSteps.MyViewHolderF> {

    Context mContext ;
    List<Food> mData;
    private List<Food> FoodLst ,FoodLst2 ;
    public static final String SHARED_TYPE = "Food" ;
    public static final String TYPE = "Type";
    private Food food ;

    public RecviewadapterOrderSteps(Context mContext, List<Food> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolderF onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v ;
        v= LayoutInflater.from(mContext).inflate(R.layout.cardview_food,parent,false);
        final MyViewHolderF VHolder = new MyViewHolderF(v);
        return VHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderF holder,final int position) {

        final String t = mData.get(position).getFoodName();
        holder.txt_Title.setText(mData.get(position).getFoodName());
        holder.txt_Price.setText((mData.get(position).getPrice()));
        holder.Img_food.setImageResource(mData.get(position).getPicture());
        food = new Food(mData.get(position).getFoodName(),mData.get(position).getPrice(),mData.get(position).getPicture());

        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Authentication succeed.",
                        Toast.LENGTH_SHORT).show();
                food = new Food(mData.get(position).getFoodName(),mData.get(position).getPrice(),mData.get(position).getPicture());
                FoodLst2 = new ArrayList<>();
                LoadData();
                SaveData();
            }
        });

        holder.Food_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderF extends RecyclerView.ViewHolder{

        private TextView txt_Title , txt_Price ;
        private ImageView Img_food;
        private LinearLayout Food_contact;
        private Button btn_add;

        public MyViewHolderF(@NonNull View itemView) {
            super(itemView);

            Food_contact = (LinearLayout)itemView.findViewById(R.id.CV_FoodLO_id);
            txt_Title = (TextView)itemView.findViewById(R.id.CV_Name_id);
            txt_Price = (TextView)itemView.findViewById(R.id.CV_Price_id);
            Img_food = (ImageView)itemView.findViewById(R.id.CV_Img_id);
            btn_add = (Button)itemView.findViewById(R.id.CV_btn_id);

        }
    }

    public void SaveData(){
        SharedPreferences sp  = mContext.getSharedPreferences("FOOD" ,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(FoodLst2);
        editor.putString("FoodArray",json);
        editor.apply();
    }

    public void LoadData(){
        SharedPreferences sp2 = mContext.getSharedPreferences("FOOD" , Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp2.getString("FoodArray" , null);
        Type typeT = new TypeToken<ArrayList<Food>>() {}.getType();
        FoodLst2 = gson.fromJson(json , typeT );
        if(FoodLst2 == null){
            FoodLst2 = new ArrayList<>();
        }
        FoodLst2.add(food);


    }
}

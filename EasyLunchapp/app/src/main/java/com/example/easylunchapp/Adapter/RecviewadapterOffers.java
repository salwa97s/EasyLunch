package com.example.easylunchapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.Classes.Special;
import com.example.easylunchapp.R;

import java.util.List;

public class RecviewadapterOffers extends RecyclerView.Adapter<RecviewadapterOffers.MyViewHolderS> {

    Context mContext ;
    List<Special> mData;

    public RecviewadapterOffers(Context mContext, List<Special> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolderS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        v= LayoutInflater.from(mContext).inflate(R.layout.cardview_special,parent,false);
        MyViewHolderS VHolder = new MyViewHolderS(v);

        return VHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderS holder, int position) {

        holder.pic.setImageResource(mData.get(position).getImage());
        holder.title.setText((mData.get(position).getTitle()));
        holder.price.setText((mData.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderS extends RecyclerView.ViewHolder{

        ImageView pic ;
        TextView title , price ;

        public MyViewHolderS(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.SCV_title_id);
            price = (TextView)itemView.findViewById(R.id.SCV_price_id);
            pic = (ImageView)itemView.findViewById(R.id.SCV_img_id);
        }
    }
}

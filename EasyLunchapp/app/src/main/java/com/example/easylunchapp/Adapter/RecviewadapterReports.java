
package com.example.easylunchapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylunchapp.R;

import java.util.List;


public class RecviewadapterReports extends RecyclerView.Adapter<RecviewadapterReports.MyViewHolderS> {

    Context mContext ;
    List<String> mData;

    public RecviewadapterReports(Context mContext, List<String> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolderS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v ;
        v= LayoutInflater.from(mContext).inflate(R.layout.cardview_report,parent,false);
        MyViewHolderS VHolder = new MyViewHolderS(v);

        return VHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderS holder, int position) {

        //String currentItem = mData.get(position);
        holder.report.setText( mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolderS extends RecyclerView.ViewHolder{

        TextView report ;


        public MyViewHolderS(@NonNull View itemView) {
            super(itemView);
            report = (TextView) itemView.findViewById(R.id.Report_txt_id);
        }
    }
}


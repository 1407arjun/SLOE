package com.blunderbois.sloe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.blunderbois.sloe.R;
import com.blunderbois.sloe.models.ClassModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.MyViewHolder> {
    Context context;
    ArrayList<Map<String, String>> list;

    public ClassAdapter(Context context, ArrayList<Map<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_class, parent, false);
        return new ClassAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.startTime.setText(list.get(position).get("startTime") + " hrs");
        holder.endTime.setText(list.get(position).get("endTime") + " hrs");
        holder.mood.setText(list.get(position).get("mood"));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView startTime, endTime, mood;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            startTime = itemView.findViewById(R.id.startTime);
            endTime = itemView.findViewById(R.id.endTime);
            mood = itemView.findViewById(R.id.mood);
        }
    }
}

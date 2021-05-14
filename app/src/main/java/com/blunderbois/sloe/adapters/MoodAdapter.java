package com.blunderbois.sloe.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blunderbois.sloe.R;
import com.blunderbois.sloe.models.MoodModel;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MyViewHolder> {
    ArrayList<MoodModel> list = new ArrayList<>();
    Context context;

    public MoodAdapter(ArrayList<MoodModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_mood, parent, false);
        return new MoodAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MoodModel model = list.get(position);
        holder.date.setText(model.getDate());
        holder.moodCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        CardView moodCard;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            moodCard = itemView.findViewById(R.id.moodCard);

        }
    }
}

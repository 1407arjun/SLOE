package com.blunderbois.sloe.adapters;

import android.content.Context;
import android.graphics.Color;
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
import java.util.HashMap;
import java.util.Map;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MyViewHolder> {
    ArrayList<Map<String, String>> list;
    Context context;

    public MoodAdapter(ArrayList<Map<String, String>> list, Context context) {
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
        Map<String, String> colors = new HashMap<>();
        colors.put("Attentive", "#7CFC00");
        colors.put("Unattentive", "#FF7F50");
        colors.put("Confused", "#0000FF");
        colors.put("Depressed", "#DC143C");
        colors.put("Cheerful", "#FAFAD2");
        colors.put("null", "#FFFFFF");
        holder.date.setText(position + 1);
        holder.moodCard.setCardBackgroundColor(Color.parseColor(colors.get(list.get(position).get("overall"))));
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

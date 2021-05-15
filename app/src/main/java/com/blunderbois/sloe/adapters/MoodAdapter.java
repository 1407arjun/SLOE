package com.blunderbois.sloe.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.blunderbois.sloe.DataActivity;
import com.blunderbois.sloe.R;
import com.blunderbois.sloe.models.ClassModel;
import com.blunderbois.sloe.models.MoodModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.blunderbois.sloe.DataActivity.moodEmoji;
import static com.blunderbois.sloe.DataActivity.moodText;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.MyViewHolder> {
    ArrayList<MoodModel> list;
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
        String emoji = null;
        String color = null;
       if(model.getOverall().equals("Attentive")){
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#A1ECBF"));
           color = "#A1ECBF";
           emoji = "\uD83D\uDE42";
       } else if (model.getOverall().equals("Depressed")){
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#ff726f"));
           emoji = "\uD83D\uDE1E";
           color = "#ff726f";
       } else if(model.getOverall().equals("Happy")){
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#fea82f"));
           emoji = "\uD83D\uDE00";
           color = "#fea82f";
       } else if(model.getOverall().equals("Unattentive")){
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#958ce8"));
           emoji = "\uD83D\uDE44";
           color = "#958ce8";
       } else if(model.getOverall().equals("Confused")){
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#fdd5b0"));
           emoji = "\uD83D\uDE15";
           color = "#fdd5b0";
       } else {
           holder.moodCard.setCardBackgroundColor(Color.parseColor("#dbdbe5"));
       }

        String finalEmoji = emoji;
        String finalColor = color;
        holder.moodCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.i("xx", model.getOverall());
                DocumentReference documentReference = FirebaseFirestore.getInstance()
                        .collection(FirebaseAuth.getInstance().getCurrentUser().getUid()).document(Integer.toString(position + 1));
                Log.i("lec1", "Here");
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        Log.i("lec1", "There");
                        if (task.isSuccessful()){
                            Log.i("lec1", "Everywhere");
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()){
                                Log.i("lec1", "Again");
                                //ClassModel classModel = (ClassModel) documentSnapshot.getData();
                                for (int i = 1; i <= documentSnapshot.getData().size(); i++){
                                    HashMap<String, String> hashMap = (HashMap<String, String>) documentSnapshot.getData().get(Integer.toString(i));
                                    ClassModel classModel = new ClassModel();
                                    classModel.setEndTime(hashMap.get("endTime"));
                                    classModel.setStartTime(hashMap.get("startTime"));
                                    classModel.setMood(hashMap.get("mood"));
                                    DataActivity.classList.add((classModel));
                                }
                                //Log.i("lec1", DataActivity.classList.get(0).getMood());
                            }else{
                                Log.i("lec1", "No exists");
                            }
                        }
                    }
                });
                Intent intent = new Intent(context, DataActivity.class);
                intent.putExtra("mood", model.getOverall());
                intent.putExtra("emoji", finalEmoji);
                intent.putExtra("color", finalColor);
                context.startActivity(intent);
                return true;
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
        ConstraintLayout forColor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.date);
            moodCard = itemView.findViewById(R.id.moodCard);
            forColor = itemView.findViewById(R.id.forColor);

        }
    }
}

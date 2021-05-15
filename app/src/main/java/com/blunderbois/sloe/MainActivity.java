package com.blunderbois.sloe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.blunderbois.sloe.adapters.MoodAdapter;
import com.blunderbois.sloe.models.MoodModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ArrayList<MoodModel> moodList = new ArrayList<>();
    private LottieAnimationView loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameText = findViewById(R.id.nameTextView);
        TextView schoolText = findViewById(R.id.schoolTextView);
        loading = findViewById(R.id.loading);


        mAuth = FirebaseAuth.getInstance();
        RecyclerView moodRecyclerView = findViewById(R.id.moodRecyclerView);
        MoodAdapter moodAdapter = new MoodAdapter(moodList,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,5);
        moodRecyclerView.setLayoutManager(layoutManager);
        moodRecyclerView.setAdapter(moodAdapter);

        loading.setVisibility(View.VISIBLE);
        moodRecyclerView.setVisibility(View.INVISIBLE);

        if (mAuth.getCurrentUser() != null){
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("UserData").child(mAuth.getCurrentUser().getUid());
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, String> data = new HashMap<>();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        data.put(dataSnapshot.getKey(), dataSnapshot.getValue().toString());
                    }
                    nameText.setText(data.get("name"));
                    schoolText.setText(data.get("school"));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child(mAuth.getCurrentUser().getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        MoodModel model = dataSnapshot.getValue(MoodModel.class);
                        moodList.add(model);
                    }
                    moodAdapter.notifyDataSetChanged();
                    if (moodList.isEmpty()){
                        loading.setVisibility(View.VISIBLE);
                        moodRecyclerView.setVisibility(View.INVISIBLE);
                    } else {
                        loading.setVisibility(View.INVISIBLE);
                        moodRecyclerView.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }
}
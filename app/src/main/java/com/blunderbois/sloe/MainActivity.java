package com.blunderbois.sloe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private ArrayList<Map<String, String>> moodList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nameText = findViewById(R.id.nameTextView);
        TextView schoolText = findViewById(R.id.schoolTextView);

        mAuth = FirebaseAuth.getInstance();
        RecyclerView moodRecyclerView = findViewById(R.id.moodRecyclerView);

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
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        MoodModel moodModel = (MoodModel) dataSnapshot.getValue();
                        Map<String, String> data = new HashMap<>();
                        if (moodModel != null) {
                            if (moodModel.getOverall() != null)
                                data.put("overall", moodModel.getOverall());
                            if (moodModel.getLec1() != null)
                                data.put("lec1", moodModel.getLec1());
                            if (moodModel.getLec2() != null)
                                data.put("lec2", moodModel.getLec2());
                            if (moodModel.getLec3() != null)
                                data.put("lec3", moodModel.getLec3());
                            if (moodModel.getLec4() != null)
                                data.put("lec4", moodModel.getLec4());
                            if (moodModel.getLec5() != null)
                                data.put("lec5", moodModel.getLec5());
                        } else {
                            data.put("overall", "null");
                        }
                        moodList.add(data);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        MoodAdapter moodAdapter = new MoodAdapter(moodList,this);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        moodRecyclerView.setLayoutManager(layoutManager);
        moodRecyclerView.setAdapter(moodAdapter);
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
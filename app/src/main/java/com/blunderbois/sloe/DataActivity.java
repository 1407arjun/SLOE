package com.blunderbois.sloe;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.blunderbois.sloe.models.ClassModel;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static TextView moodText, moodEmoji;
    public static ArrayList<ClassModel> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        toolbar = findViewById(R.id.toolbar);
        moodText = findViewById(R.id.moodText);
        moodEmoji = findViewById(R.id.moodEmoji);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);

        Bundle bd = getIntent().getExtras();
        moodText.setText(bd.getString("mood"));
        moodText.setTextColor(Color.parseColor(bd.getString("color")));
        moodEmoji.setText(bd.getString("emoji"));

    }
}
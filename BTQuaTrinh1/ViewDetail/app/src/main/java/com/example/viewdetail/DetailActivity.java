package com.example.viewdetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get passed data
        Intent i = getIntent();
        pos = i.getExtras().getInt("Position");

        final CustomAdapter adapter = new CustomAdapter(this);
        final ImageView img = findViewById(R.id.imageView);
        final TextView name = findViewById(R.id.nameTxt);
        final TextView description = findViewById(R.id.description);

        //set data
        img.setImageResource(adapter.images[pos]);
        name.setText(adapter.foodList[pos]);
        description.setText(adapter.descriptions[pos]);
    }
}
package com.example.rubetektest;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rubetektest.models.door;

public class DomophoneActivity extends AppCompatActivity {

    door current_door;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domophone);

        current_door = (door)getIntent().getExtras().get("current_door");
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView title = findViewById(R.id.title);
        ImageView snapshot = findViewById(R.id.snapshot);

        title.setText(current_door.getName());

        Glide
                .with(this)
                .load(current_door.getSnapshot())
                .into(snapshot);
    }
}

package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class secondactivity extends AppCompatActivity {
    ImageView secondimage_view;
     TextView textView;
    MainData mainData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        textView = (TextView) findViewById(R.id.secondtextView);
        secondimage_view = (ImageView)findViewById(R.id.secondimage_view);

        Intent intent = getIntent();
        mainData  = (MainData) intent.getSerializableExtra("key");

        textView.setText(mainData.getName());
        Glide.with(getApplicationContext())
                .load(mainData.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(secondimage_view);

    }
}
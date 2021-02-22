package com.example.careerchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class AboutActivity extends AppCompatActivity {

    LinearLayout linearLayoutmayank, linearLayout_ram;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        SlidrInterface slidrInterface = Slidr.attach(this);
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        linearLayoutmayank = findViewById(R.id.mayank_about);
        linearLayoutmayank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, About_Mayank_Details_Activity.class);
                startActivity(intent);

            }
        });

        linearLayout_ram = findViewById(R.id.ram_about);
        linearLayout_ram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this,About_Ram_Details_Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_tejas = findViewById(R.id.tejas_about);
        linearLayout_tejas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this,About_Tejas_Details_Activity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout_sanjay = findViewById(R.id.sanjay_about);
        linearLayout_sanjay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this,About_Sanjay_Details_Activity.class);
                startActivity(intent);
            }
        });
    }
}
package com.example.careerchoice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class About_Mayank_Details_Activity extends AppCompatActivity {
    FloatingActionButton floatingActionback;
    TextView facebook, instagram;

    LinearLayout linearLayoutmayank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_mayank_details);
        floatingActionback = findViewById(R.id.floting_back);
        floatingActionback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        facebook = findViewById(R.id.facebook_text);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/mayank.kanjariya/"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/mayank.kanjariya/")));
                }
            }
        });
        TextView call = findViewById(R.id.call_text);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:8000434869"));
                startActivity(intent);
            }
        });

        instagram = findViewById(R.id.instagram_text);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en")));
                }
            }
        });
    }
}
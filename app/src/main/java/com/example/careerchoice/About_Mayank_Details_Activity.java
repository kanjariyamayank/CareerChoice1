package com.example.careerchoice;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class About_Mayank_Details_Activity extends AppCompatActivity {
    FloatingActionButton floatingActionback;
    TextView facebook, instagram;

    LinearLayout linearLayoutmayank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_mayank_details);
        SlidrInterface slidrInterface = Slidr.attach(this);
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

                Uri uri = Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en")));
                }
                /*try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mayank.j.kanjariya/?hl=en")));
                }*/
            }
        });

        TextView twitter = findViewById(R.id.twitter_text);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=KanjariyaMayank"));
                    startActivity(intent);
                } catch (Exception e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/KanjariyaMayank")));
                }

            }
        });
    }
}
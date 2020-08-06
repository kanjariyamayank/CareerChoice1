package com.example.careerchoice;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    TextView a, slogan;
    ImageView splash;
    Animation topAnimantion, bottomAnimation, middleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        a = findViewById(R.id.a);
        splash = findViewById(R.id.image_splsh);
        slogan = findViewById(R.id.tagLine);
        topAnimantion = AnimationUtils.loadAnimation(this, R.anim.top);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom);
        middleAnimation = AnimationUtils.loadAnimation(this, R.anim.middle);
        splash.setAnimation(middleAnimation);
        a.setAnimation(topAnimantion);
        slogan.setAnimation(bottomAnimation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
                    if (pref.getBoolean("activity_executed", false)) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        SharedPreferences.Editor edt = pref.edit();
                        edt.putBoolean("activity_executed", true);
                        edt.apply();
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, SPLASH_TIME_OUT);

    }
}
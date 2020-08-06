package com.example.careerchoice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.careerchoice.utils.Constants;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DashboardActivity extends AppCompatActivity {

    TextView textUsername, textEmail, text_lastname;
    LinearLayout shareapp, feedback, careerBook, sliderfield,About;
    DrawerLayout drawerLayout;
    LinearLayout logout, field;
    private long backpressedTime;
    ImageView uplode;
    ImageView imageMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        drawerLayout = findViewById(R.id.drawer_layout);
        imageMenu = findViewById(R.id.image_menu);
        logout = findViewById(R.id.logout_button);
        textUsername = findViewById(R.id.text_username);
        textEmail = findViewById(R.id.text_email);
        text_lastname = findViewById(R.id.text_lastname);
        About = findViewById(R.id.layout_about);
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(DashboardActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });
        sliderfield = findViewById(R.id.layout_field);
        sliderfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this,CategoriesActivity.class);
                startActivity(intent);
            }
        });
        field = findViewById(R.id.card_field);
        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });
        careerBook = findViewById(R.id.career_book);
        careerBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, CareerBookActivity.class);
                startActivity(intent);
            }
        });
        feedback = findViewById(R.id.layout_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, FeedbackActivity.class);
                startActivity(intent);

            }
        });
        shareapp = findViewById(R.id.share_app);
        shareapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Insert Subject here");
                String app_url = " Application linke is Not Available";
                shareIntent.putExtra(Intent.EXTRA_TEXT, app_url);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
        imageMenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        final SharedPreferences preferences = getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE);
        final boolean isLoggedIn = preferences.getBoolean(Constants.KEY_ISE_LOGGED_IN, false);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new SweetAlertDialog(DashboardActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Logout!")
                        .setCancelText("No")
                        .setConfirmText("Yes")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                /*PreferenceHelper s = new PreferenceHelper(DashboardActivity.this);
                                s.logOut(DashboardActivity.this);*/
                                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                /*DashboardActivity.this.finish();*/
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.cancel();
                            }
                        })
                        .show();
                /*
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DashboardActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Are you sure you want to logout?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Toast.makeText(DashboardActivity.this, "You have been logged out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();*/
            }
        });
        if (!isLoggedIn) {
            textUsername.setText(R.string.welcome_guest);
            textUsername.setVisibility(View.VISIBLE);
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        } else {
            textUsername.setText(preferences.getString(Constants.KEY_USERNAME, "N/A"));
            textEmail.setText(preferences.getString(Constants.KEY_EMAIL, "N/A"));
            textUsername.setVisibility(View.VISIBLE);
            textEmail.setVisibility(View.VISIBLE);
        }

        /*uplode = findViewById(R.id.uplode_image);
        uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });*/
    }

    @Override
    public void onBackPressed() {

        if (backpressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        } else
            Toast.makeText(this, "Press Back Again to Exit App", Toast.LENGTH_SHORT).show();
        backpressedTime = System.currentTimeMillis();
    }
}
package com.example.careerchoice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.card.MaterialCardView;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class FeedbackActivity extends AppCompatActivity {

    EditText feedback_edit;
    ImageView imageback;
    TextView textRating;
    TextView send_feedback;
    CardView ratingButton;
    RatingBar ratingBar;
    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_NAME = "keyname";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        SlidrInterface slidrInterface = Slidr.attach(this);
        feedback_edit = findViewById(R.id.feedback_edit);
        ratingBar = findViewById(R.id.rating_app);
        textRating = findViewById(R.id.rating_text);
        ratingButton = findViewById(R.id.rating_button);
        imageback = findViewById(R.id.image_menu);
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        send_feedback = findViewById(R.id.send_feedback);

        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String feedback = feedback_edit.getText().toString();


                /*Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(feedback));
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kanjariyamayank8000@gmail.com"});*/
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"kanjariyamayank8000@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT, feedback);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(FeedbackActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ratingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*float rating = ratingBar.getRating();
                textRating.setText("Rating" + String.valueOf(rating));
                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("Rating", textRating.getText().toString());
                editor.commit();*/
                /*Toast.makeText(getApplicationContext(), rating + "", Toast.LENGTH_LONG).show();*/
                saveName();
                displayName();
            }
        });

    }

    private void saveName() {
        float name = ratingBar.getRating();

        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(KEY_NAME, String.valueOf(name));

        editor.apply();
    }

    private void displayName() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, null);

        if (name != null) {
            textRating.setText("Rating " + name);
        }
    }
}
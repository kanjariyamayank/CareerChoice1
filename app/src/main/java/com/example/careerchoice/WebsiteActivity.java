package com.example.careerchoice;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class WebsiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);
        final ListView weblistview = findViewById(R.id.website_list);
        SlidrInterface slidrInterface = Slidr.attach(this);
        ImageView imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        final String[] weblist = {"After 10th:Course Details, Scope and Colleges",
                "All Courses", "List of ITI Courses- After 10th & 8th", "Bachelor of Science (BSc)",
                "About Bachelor of Computer Applications (BCA)"};
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (WebsiteActivity.this, android.R.layout.simple_list_item_1, weblist) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView mytext = findViewById(android.R.id.text1);
                return view;
            }

        };

        weblistview.setAdapter(arrayAdapter);
        weblistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = weblistview.getItemAtPosition(i).toString();

                /*String getItem = getIntent().getStringExtra("PdfFileName");*/

                if (item.equals("After 10th:Course Details, Scope and Colleges")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.collegedekho.com/articles/diploma-courses-after-10th-course-details-scope-colleges/"));
                    startActivity(intent);
                }
                if (item.equals("All Courses")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://collegedunia.com/courses")));
                }
                if (item.equals("List of ITI Courses- After 10th & 8th")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.collegedekho.com/articles/iti-courses-after-10th/")));
                }
                if (item.equals("Bachelor of Science (BSc)")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.shiksha.com/b-sc-chp")));
                }
                if (item.equals("About Bachelor of Computer Applications (BCA)")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.collegedekho.com/courses/bca/#:~:text=The%203%20years%20BCA%20course,Multimedia%20Systems%2C%20Operating%20Systems%20etc.")));
                }

                /*Intent intent = new Intent(WebsiteActivity.this,PDFOpen.class);
                intent.putExtra("PdfFileName",item);
                startActivity(intent);*/
            }
        });

    }
}
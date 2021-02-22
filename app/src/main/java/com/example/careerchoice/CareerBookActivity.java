package com.example.careerchoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class CareerBookActivity extends AppCompatActivity {

    ListView pdfView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_career_book);
        SlidrInterface slidrInterface = Slidr.attach(this);
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pdfView = findViewById(R.id.pdfView);
        /*pdfView.fromAsset("career.pdf").load();*/
        String[] pdflist = {"A Self Career Counseling Guide", "Career After 12th Science","Job Opportunities After 10th & 12th",
                "Academic Courses After 12th","The Career Book","HANDBOOK ON CAREER GUIDANCE AND COUNSELLING",
                "Career Opportunities For Master Of Public Health Graduates In India"};

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(CareerBookActivity.this, android.R.layout.simple_list_item_1, pdflist) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView mytext = findViewById(android.R.id.text1);
                return view;
            }
        };

        pdfView.setAdapter(arrayAdapter);
        pdfView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = pdfView.getItemAtPosition(i).toString();
                Intent intent = new Intent(CareerBookActivity.this,PDFOpen.class);
                intent.putExtra("PdfFileName",item);
                startActivity(intent);
            }
        });

    }

}
package com.example.careerchoice;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


public class PDFOpen extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfopen);
        pdfView = findViewById(R.id.pdf_Viewer);
        String getItem = getIntent().getStringExtra("PdfFileName");

        if (getItem.equals("A Self Career Counseling Guide")) {
            pdfView.fromAsset("career.pdf").load();
        }

        if (getItem.equals("Career After 12th Science")) {
            pdfView.fromAsset("Career after 12th Science.pdf").load();
        }
        if (getItem.equals("Job Opportunities After 10th & 12th")) {
            pdfView.fromAsset("Final job.pdf").load();
        }
        if (getItem.equals("Academic Courses After 12th")) {
            pdfView.fromAsset("Academic Courses after +2.pdf").load();
        }
        if (getItem.equals("The Career Book")) {
            pdfView.fromAsset("The Career Book.pdf").load();
        }
        if (getItem.equals("HANDBOOK ON CAREER GUIDANCE AND COUNSELLING")) {
            pdfView.fromAsset("HANDBOOK.pdf").load();
        }
        if (getItem.equals("Career Opportunities For Master Of Public Health Graduates In India")) {
            pdfView.fromAsset("CareerOpportunities.pdf").load();
        }
    }
}
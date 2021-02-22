package com.example.careerchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Courses_Details_Activity extends AppCompatActivity {

    TextView course, time, exam_type, eligibility, Admission, Syllabus;
    ImageView imageView, imageView_slider,imageAddToCart;
    TextView textView;
    SliderLayout sliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses__details_);

        /*sliderLayout = findViewById(R.id.slider);*/
        imageView_slider = findViewById(R.id.image_book);
        SlidrInterface slidrInterface = Slidr.attach(this);
        textView = findViewById(R.id.text_toolbar_title_courses_details);
        textView.setText(getIntent().getStringExtra("courses"));
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        time = findViewById(R.id.time);
        exam_type = findViewById(R.id.exame_type);
        eligibility = findViewById(R.id.eligibility_criteria);
        Admission = findViewById(R.id.admission);
        Syllabus = findViewById(R.id.syllabus);
        sliderLayout = findViewById(R.id.slider);

        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("image"))
                .into(imageView_slider);

        /*Picasso.get().load(getIntent().getStringExtra("image")).into(imageView_slider);*/
        time.setText(getIntent().getStringExtra("introduction"));
        exam_type.setText(getIntent().getStringExtra("course_details"));
        eligibility.setText(getIntent().getStringExtra("eligibility_criteria"));
        Admission.setText(getIntent().getStringExtra("admission"));
        Syllabus.setText(getIntent().getStringExtra("syllabus"));
        try {
            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put("image", getIntent().getStringExtra("image"));
            url_maps.put("image_1", getIntent().getStringExtra("image_1"));
            url_maps.put("image_2", getIntent().getStringExtra("image_2"));

            for (String name : url_maps.keySet()) {
                TextSliderView textSliderView = new TextSliderView(this);
                textSliderView
                        .description(name)
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit);

                sliderLayout.addSlider(textSliderView);
            }
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(4000);

        } catch (Exception ex) {

        }
    }
}
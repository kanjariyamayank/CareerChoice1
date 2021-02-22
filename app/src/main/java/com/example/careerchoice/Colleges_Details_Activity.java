package com.example.careerchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.careerchoice.database.DatabaseHandler;
import com.example.careerchoice.models.CollegeModel;
import com.example.careerchoice.utils.MyBounceInterpolator;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class Colleges_Details_Activity extends AppCompatActivity {

    TextView address, college_code, contect, department, college_details,college_link;
    ImageView imageView, imageView_slider, imageAddToCart;
    TextView textView_toolbar;
    SliderLayout sliderLayout;
    ExpandableLinearLayout expandableLinearLayout;
    LinearLayout all_deteails_lineear;
    SlidrInterface slidrInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colleges__details_);
        imageView_slider = findViewById(R.id.image_slider_college);
        textView_toolbar = findViewById(R.id.text_toolbar_title_college_details);
        textView_toolbar.setText(getIntent().getStringExtra("college_name"));
        imageView = findViewById(R.id.image_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        address = findViewById(R.id.college_address);
        college_code = findViewById(R.id.college_code);
        contect = findViewById(R.id.college_contect);
        department = findViewById(R.id.college_department);
        college_details = findViewById(R.id.college_details);
        college_link = findViewById(R.id.college_link);
        slidrInterface = Slidr.attach(this);

        imageAddToCart = (ImageView) findViewById(R.id.image_add_to_favorite);
        imageAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Animation animation = AnimationUtils.loadAnimation(Colleges_Details_Activity.this, R.anim.bounce_animation);
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                animation.setInterpolator(interpolator);
                imageAddToCart.startAnimation(animation);

                CollegeModel collegekModel = new CollegeModel();
                collegekModel.setId(getIntent().getStringExtra("id"));
                collegekModel.setFieldname(getIntent().getStringExtra("field_name"));
                collegekModel.setCollege_name(getIntent().getStringExtra("college_name"));
                collegekModel.setCollege_code(getIntent().getStringExtra("college_code"));
                collegekModel.setAddress(getIntent().getStringExtra("address"));
                collegekModel.setContact(getIntent().getStringExtra("contact"));
                collegekModel.setDepartment(getIntent().getStringExtra("department"));
                collegekModel.setCollege_details(getIntent().getStringExtra("college_details"));
                collegekModel.setImage_college(getIntent().getStringExtra("image"));
                collegekModel.setCollege_link(getIntent().getStringExtra("college_link"));

                DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                databaseHandler.addToCart(collegekModel);
                Toast.makeText(Colleges_Details_Activity.this, "Added to cart!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), FavoriteActivity.class);
                startActivity(intent);
            }
        });
        /*DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
        List<CollegeModel> cartitme = databaseHandler.getCartItems();

        for (int i =0 ;i<= cartitme.size()-1; i++ ){

        }*/

        sliderLayout = findViewById(R.id.slider_college);
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("image"))
                .into(imageView_slider);

        /*Picasso.get().load(getIntent().getStringExtra("image")).into(imageView_slider);*/
        address.setText(getIntent().getStringExtra("address"));
        college_code.setText(getIntent().getStringExtra("college_code"));
        contect.setText(getIntent().getStringExtra("contact"));
        department.setText(getIntent().getStringExtra("department"));
        college_details.setText(getIntent().getStringExtra("college_details"));
        college_link.setText(getIntent().getStringExtra("college_link"));
       /* college_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = String.valueOf(college_link);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(string));
                startActivity(intent);

            }
        });*/
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
        final ImageView imageView_arrow = findViewById(R.id.all_image_arrow);
        TextView all_text = findViewById(R.id.all_text);
        all_deteails_lineear = findViewById(R.id.all_deteails_layout);
        all_deteails_lineear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableLinearLayout = findViewById(R.id.expandle_all_deteails);
                expandableLinearLayout.toggle();
            }
        });
       /* imageView_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (all_deteails_lineear.getVisibility() == view.GONE) {
                    imageView_arrow.setBackgroundResource(R.drawable.arrow_up_24);
                } else {
                    expandableLinearLayout.toggle();
                    imageView_arrow.setBackgroundResource(R.drawable.arrow_down_24);
                }
            }
        });*/
    }
}
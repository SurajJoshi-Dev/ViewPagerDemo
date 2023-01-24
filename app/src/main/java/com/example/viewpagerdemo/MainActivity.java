package com.example.viewpagerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewpagerdemo.Adapter.SliderAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager slideViewPager;
    LinearLayout dots_layout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    TextView nextTxt, skip;
    ImageButton nxt;
    int mCurrentPage;
    ProgressBar progressBarOnboarding;
    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMethod();
    }

    private void initMethod() {
        slideViewPager = findViewById(R.id.slideViewPager);
        dots_layout = findViewById(R.id.dots_layout);
        skip = findViewById(R.id.prevBtn);
        skip.setVisibility(View.GONE);
        nxt = findViewById(R.id.nextBtn);
        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);
        nextTxt = findViewById(R.id.next_text);
        progressBarOnboarding = findViewById(R.id.progressBarOnboarding);
        progressBarOnboarding.setVisibility(View.GONE);
        start_button = findViewById(R.id.start_button);
        start_button.setVisibility(View.GONE);
        nxt.setOnClickListener(this);
        nextTxt.setOnClickListener(this);
        skip.setOnClickListener(this);
        start_button.setOnClickListener(this);
    }

    private void addDotsIndicator(int pos) {
        dots = new TextView[3];
        dots_layout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots_layout.addView(dots[i]);
            if (dots.length > 0) {
                // dots[pos].setTextColor(getResources().getColor(R.color.colorPrimary));

            }
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0) {
                nxt.setVisibility(View.VISIBLE);
                nextTxt.setVisibility(View.VISIBLE);
                skip.setVisibility(View.GONE);
            } else if (position == dots.length - 1) {
                skip.setVisibility(View.VISIBLE);
                nextTxt.setText("Done");
                start_button.setVisibility(View.VISIBLE);
                nextTxt.setVisibility(View.GONE);
                nxt.setVisibility(View.GONE);
            } else {
//                nxt.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.mutton_icon));
                nxt.setVisibility(View.VISIBLE);
                nextTxt.setText("Next");
                start_button.setVisibility(View.GONE);
                nextTxt.setVisibility(View.VISIBLE);
                nextTxt.setVisibility(View.VISIBLE);
                skip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextBtn:
                if (mCurrentPage == 2) {
                    nxt.setEnabled(false);
                    skip.setEnabled(false);
                }
                slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;

            case R.id.next_text:
                if (mCurrentPage == 2) {
                    nxt.setEnabled(false);
                    skip.setEnabled(false);
                }
                slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;
            case R.id.prevBtn:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                Toast.makeText(this, "Skip Button Clicked", Toast.LENGTH_SHORT).show();
                startActivity(i);
                finish();
                break;

            case R.id.start_button:
                Intent i1 = new Intent(MainActivity.this, MainActivity.class);
                Toast.makeText(this, "Get Started Button  Clicked", Toast.LENGTH_SHORT).show();

                startActivity(i1);
                finish();
                break;
        }
    }
}
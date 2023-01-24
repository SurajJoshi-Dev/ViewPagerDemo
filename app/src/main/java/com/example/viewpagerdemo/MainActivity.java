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
import com.example.viewpagerdemo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   
    SliderAdapter sliderAdapter;
    TextView[] dots;
    int mCurrentPage;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        initMethod();
    }

    private void initMethod() {
        activityMainBinding.prevBtn.setVisibility(View.GONE);
        sliderAdapter = new SliderAdapter(this);
        activityMainBinding.slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        activityMainBinding.slideViewPager.addOnPageChangeListener(viewListner);
        activityMainBinding.progressBarOnboarding.setVisibility(View.GONE);
        activityMainBinding.startButton.setVisibility(View.GONE);
        activityMainBinding.nextBtn.setOnClickListener(this);
        activityMainBinding.nextText.setOnClickListener(this);
        activityMainBinding.prevBtn.setOnClickListener(this);
        activityMainBinding.startButton.setOnClickListener(this);
    }

    private void addDotsIndicator(int pos) {
        dots = new TextView[3];
        activityMainBinding.dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            activityMainBinding.dotsLayout.addView(dots[i]);
            if (i== pos) {
                dots[pos].setTextColor(getResources().getColor(R.color.colorPrimary));
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
                activityMainBinding.nextBtn.setVisibility(View.VISIBLE);
                activityMainBinding.nextText.setVisibility(View.VISIBLE);
                activityMainBinding.prevBtn.setVisibility(View.GONE);
            } else if (position == dots.length - 1) {
                activityMainBinding.prevBtn.setVisibility(View.VISIBLE);
                activityMainBinding.nextText.setText("Done");
                 activityMainBinding.startButton.setVisibility(View.VISIBLE);
                activityMainBinding.nextText.setVisibility(View.GONE);
                activityMainBinding.nextBtn.setVisibility(View.GONE);
            } else {
                activityMainBinding.nextBtn.setVisibility(View.VISIBLE);
                activityMainBinding.nextText.setText("Next");
                 activityMainBinding.startButton.setVisibility(View.GONE);
                activityMainBinding.nextText.setVisibility(View.VISIBLE);
                activityMainBinding.nextText.setVisibility(View.VISIBLE);
                activityMainBinding.prevBtn.setVisibility(View.VISIBLE);
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
                    activityMainBinding.nextBtn.setEnabled(false);
                    activityMainBinding.prevBtn.setEnabled(false);
                }
                activityMainBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;

            case R.id.next_text:
                if (mCurrentPage == 2) {
                    activityMainBinding.nextBtn.setEnabled(false);
                    activityMainBinding.prevBtn.setEnabled(false);
                }
                activityMainBinding.slideViewPager.setCurrentItem(mCurrentPage + 1);
                break;
            case R.id.prevBtn:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                Toast.makeText(this, "activityMainBinding.prevBtn Button Clicked", Toast.LENGTH_SHORT).show();
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
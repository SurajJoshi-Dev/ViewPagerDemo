package com.example.viewpagerdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;
import com.example.viewpagerdemo.R;


public class SliderAdapter extends PagerAdapter{
        Context context;
        LayoutInflater layoutInflater;

        public SliderAdapter(Context context) {
            this.context = context;
        }

        public int[] slideImage={
                R.drawable.current_marker,
                R.drawable.current_marker,
                R.drawable.current_marker
        };

        public  String[] heading={
                "Mobile Tracking",
                "Role based Dashboard",
                "Location Tracking Reports"
        };

        public String[] desc={
                "Android mobile app for GPCB Hazardous Waste - Vehicle LocationReal " +
                        "Time Tracking System.",
                "A portal has provided role based dashboard for " +
                        "Gujarat Pollution Control Board.",
                "Authorized users can download last location" +
                        " tracking reports as well as date wise location tracking reports."
        };

        @Override
        public int getCount() {
            return heading.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=layoutInflater.inflate(R.layout.slide_view_layout_for_onboarding,container,false);
            ImageView slideImageView=view.findViewById(R.id.slideimg);
            TextView headingText=view.findViewById(R.id.slidehed);
            TextView descText=view.findViewById(R.id.slidedec);
            slideImageView.setImageResource(slideImage[position]);
            headingText.setText(heading[position]);
            descText.setText(desc[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ConstraintLayout) object);
        }

}

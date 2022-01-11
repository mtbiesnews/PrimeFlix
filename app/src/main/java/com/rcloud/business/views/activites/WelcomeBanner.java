package com.rcloud.business.views.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.ActivitySignIn;
import com.rcloud.netflix.ActivitySignUp;
import com.rcloud.netflix.MainActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityMtbiesLoginBinding;
import com.rjesture.activities.Dashboard;
import com.rjesture.activities.SignUp;

/**
 * Created by Rjesture on 29/12/2021.
 */
public class WelcomeBanner extends BaseActivity<ActivityMtbiesLoginBinding> {

    int page = 0;
    LoginBannerViewPagerAdapter loginBannerViewPagerAdapter;
    ViewPager.OnPageChangeListener viewPagerPageChangeListener;
    private int[] layouts;
    //TextView
    private TextView[] dots;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_mtbies_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListeners();
        setAdapter();

    }

    private void setListeners() {
        dataBinding.btnLogin.setOnClickListener(v -> {
//            startActivity(new Intent(this, ActivitySignIn.class));
            startActivity(new Intent(this, SignUp.class));
        });
        dataBinding.btnSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this, Dashboard.class));
//            startActivity(new Intent(this, ActivitySignUp.class));
        });
        dataBinding.tvSkip.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        };
    }

    private void setAdapter() {
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.activity_splash,
                R.layout.activity_splash,
                R.layout.activity_splash,
                R.layout.activity_splash};
        // adding bottom dots
        addBottomDots(0);
        loginBannerViewPagerAdapter = new LoginBannerViewPagerAdapter();
        dataBinding.vpBanners.setAdapter(loginBannerViewPagerAdapter);
        dataBinding.vpBanners.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    private void addBottomDots(int currentPage) {

        page = currentPage;
        dots = new TextView[layouts.length];
        dataBinding.llDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            if (i == currentPage) {
                dots[i] = new TextView(this);
                dots[i].setTextSize(35);
                dots[i].setText(Html.fromHtml("\u25C9"));
                dots[i].setTextColor(getResources().getColor(R.color.colorAccent));
                dots[i].setGravity(Gravity.CENTER);
                dots[i].setPadding(5, -14, 5, 0);
                dataBinding.llDots.addView(dots[i]);
            } else {
                dots[i] = new TextView(this);
                dots[i].setTextSize(25);
                dots[i].setText(Html.fromHtml("\u25CF"));
                dots[i].setTextColor(getResources().getColor(R.color.grey));
                dots[i].setGravity(Gravity.CENTER);
                dots[i].setPadding(5, 0, 5, 0);
                dataBinding.llDots.addView(dots[i]);
            }
        }

        /*btnNext.setText(getString(R.string.next_));
        if (currentPage == 3) {
            btnNext.setText(getString(R.string.getStarted));
        }*/

    }

    public class LoginBannerViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public LoginBannerViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
package com.rcloud.business.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rcloud.business.fragment.AboutFragment;
import com.rcloud.business.fragment.ApplyJobFragment;
import com.rcloud.business.fragment.CareerFragment;
import com.rcloud.business.fragment.ContactFragment;
import com.rcloud.business.fragment.FamilyFragment;
import com.rcloud.business.fragment.LifeStyleFragment;
import com.rcloud.business.fragment.MarraigeProfileFragment;
import com.rcloud.business.fragment.ModelProfileFragment;


public class ViewPagerStateAdapter extends FragmentStatePagerAdapter {

    // tab titles
    private String[] titles = new String[]{"About", "Career", "Lifestyle", "Family", "Contact",
            "Apply Job", "Marriage Profile", "Model Profile"};

    public ViewPagerStateAdapter(@NonNull  FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public String[] getTitles() {
        return titles;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AboutFragment();
            case 1:
                return new CareerFragment();
            case 2:
                return new LifeStyleFragment();
            case 3:
                return new FamilyFragment();
            case 4:
                return new ContactFragment();
            case 5:
                return new ApplyJobFragment();
            case 6:
                return new MarraigeProfileFragment();
            case 7:
                return new ModelProfileFragment();
        }
        return new AboutFragment();
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}

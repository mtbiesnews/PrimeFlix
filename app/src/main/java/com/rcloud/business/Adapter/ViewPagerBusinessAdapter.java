package com.rcloud.business.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.rcloud.business.fragment.BusineesProfileFrag.AboutFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.CaseSchudleFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.EventsFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.NetWorkFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.PhotosFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.PostJobFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.ProfileFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.ServiceOnTimeFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.ServicesFragment;
import com.rcloud.business.fragment.BusineesProfileFrag.VideosFragment;


public class ViewPagerBusinessAdapter  extends FragmentStatePagerAdapter {
    private String[] titles = new String[]{ "Profile", "Network", "About", "Service",
            "Events", "Post Job", "Photos", "Videos","Service on Time","Case Schedule"};



    public ViewPagerBusinessAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public String[] getTitles() {
        return titles;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new NetWorkFragment();
            case 2:
                return new AboutFragment();
            case 3:
                return new ServicesFragment();
            case 4:
                return new EventsFragment();
            case 5:
                return new PostJobFragment();
            case 6:
                return new PhotosFragment();
            case 7:
                return new VideosFragment();
            case 8:
                return new ServiceOnTimeFragment();
                case 9:
                return new CaseSchudleFragment();
        }
        return new ProfileFragment();    }

    @Override
    public int getCount() {
        return titles.length;
    }
}

package com.rcloud.business.views.fragments;

import android.os.Bundle;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.fragment.AllVideoFragment;
import com.example.fragment.CategoryFragment;
import com.example.fragment.DownloadsFragment;
import com.example.fragment.FavoriteFragment;
import com.example.fragment.HomeFragment;
import com.example.helper.BottomNavigationBehavior;
import com.example.item.ItemAbout;
import com.example.util.JsonUtils;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.ads.consent.ConsentForm;
import com.rcloud.netflix.MyApplication;
import com.rcloud.netflix.R;

import java.util.ArrayList;


public class media extends Fragment {   private BubbleNavigationConstraintView bubbleNavigationLinearView;
    MyApplication App;
    ArrayList<ItemAbout> mListItem;
    JsonUtils jsonUtils;
    LinearLayout adLayout;
    private ConsentForm form;
    String strMessage;
    private FragmentManager fragmentManager;
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_media, container, false);
        App = MyApplication.getInstance();

        mListItem = new ArrayList<>();
        jsonUtils = new JsonUtils(requireActivity());
        jsonUtils.forceRTLIfSupported(requireActivity().getWindow());

        fragmentManager = requireActivity().getSupportFragmentManager();

//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setVisibility(View.VISIBLE);
        bubbleNavigationLinearView = view.findViewById(R.id.top_navigation_constraint);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bubbleNavigationLinearView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

start();
        return view;
    }
    void start(){
        if (bubbleNavigationLinearView != null) {
            selectFragmentStyle(0);

            bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
                @Override
                public void onNavigationChanged(View view, int position) {
//                viewPager.setCurrentItem(position, true);
                    selectFragmentStyle(position);
                }
            });
        }
    }
    protected void selectFragmentStyle(int item) {

        bubbleNavigationLinearView.setCurrentActiveItem(item);

        switch (item) {
            case 0:
                visible_bottomNavigation();
                // Action to perform when Home Menu item is selected.
                HomeFragment homeFragment = new HomeFragment();
                loadFrag(homeFragment, getString(R.string.menu_home), fragmentManager);
                highLightNavigation(0, getString(R.string.menu_home));

                break;
            case 1:
                // Action to perform when Bag Menu item is selected.
                CategoryFragment categoryFragment = new CategoryFragment();
                loadFrag(categoryFragment, getString(R.string.menu_category), fragmentManager);
                highLightNavigation(2, getString(R.string.menu_category));
                break;
            case 2:
                // Action to perform when Account Menu item is selected.
                AllVideoFragment allVideoFragment = new AllVideoFragment();
                loadFrag(allVideoFragment, getString(R.string.menu_video), fragmentManager);
                highLightNavigation(0, getString(R.string.menu_video));
                break;

            case 3:
                // Action to perform when Account Menu item is selected.
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                loadFrag(favoriteFragment, getString(R.string.menu_favorite), fragmentManager);
                highLightNavigation(3, getString(R.string.menu_favorite));
                break;

            case 4:
                // Action to perform when Account Menu item is selected.
                DownloadsFragment downloadsFragment = new DownloadsFragment();
                loadFrag(downloadsFragment, getString(R.string.menu_download), fragmentManager);
                highLightNavigation(4, getString(R.string.menu_download));
                break;
        }
    }
    public void loadFrag(Fragment f1, String name, FragmentManager fm) {
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        FragmentTransaction ft = fm.beginTransaction();
        //  ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(R.id.Containera, f1, name);
        ft.commit();

    }
    public void visible_bottomNavigation() {
        bubbleNavigationLinearView.setVisibility(View.VISIBLE);
    }
    public void highLightNavigation(int position, String name) {


    }

    public void invisible_bottomNavigation() {
        bubbleNavigationLinearView.setVisibility(View.GONE);
    }
}
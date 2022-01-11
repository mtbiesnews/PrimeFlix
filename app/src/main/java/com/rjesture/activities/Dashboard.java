package com.rjesture.activities;

import static com.Tools.AppUtils.showLog;
import static com.Tools.AppUtils.showToast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment.AllVideoFragment;
import com.example.fragment.CategoryFragment;
import com.example.fragment.DownloadsFragment;
import com.example.fragment.FavoriteFragment;
import com.example.fragment.HomeFragment;
import com.example.util.PrefManager;
import com.facebook.ads.AudienceNetworkAds;
import com.rcloud.base.BaseActivity;
import com.rcloud.netflix.ActivitySearch;
import com.rcloud.netflix.MyApplication;
import com.rcloud.netflix.NotificationActivity;
import com.rcloud.netflix.R;
import com.rcloud.netflix.databinding.ActivityDashboardBinding;
import com.rjesture.fragments.DashboardHome;

public class Dashboard extends BaseActivity<ActivityDashboardBinding> {
    Context mActivity;
    Toolbar toolbar;
    MyApplication App;
    public static int backFrag = 0;
    private PrefManager prf;
    private Fragment fragment;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_dashboard;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setIds();
        setListeners();
        displayView(1);

/*
        prepareViewPager();
*/
    }

    private void setListeners() {
        dataBinding.rlHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(1);
            }
        });
        dataBinding.rlBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(2);
                tabChooser(dataBinding.vBusiness,dataBinding.clBottomNavHome);
            }
        });
        dataBinding.rlEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabChooser(dataBinding.vEntertainment,dataBinding.clBottomNavEntertainment);
                selectEntertainmentPage(0);
            }
        });
        dataBinding.rlNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(4);
                tabChooser(dataBinding.vNews,dataBinding.clBottomNavHome);
            }
        });
        dataBinding.rlSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(5);
                tabChooser(dataBinding.vSport,dataBinding.clBottomNavHome);
            }
        });
    }


    private void tabChooser(View viewChoice, View bottomNav) {
        dataBinding.vHome.setVisibility(View.INVISIBLE);
        dataBinding.vEntertainment.setVisibility(View.INVISIBLE);
        dataBinding.vBusiness.setVisibility(View.INVISIBLE);
        dataBinding.vNews.setVisibility(View.INVISIBLE);
        dataBinding.vSport.setVisibility(View.INVISIBLE);
        dataBinding.clBottomNavHome.setVisibility(View.GONE);
        dataBinding.clBottomNavEntertainment.setVisibility(View.GONE);
        viewChoice.setVisibility(View.VISIBLE);
        bottomNav.setVisibility(View.VISIBLE);
    }

    public void displayView(int position) {
        switch (position) {
            case 1:// homePage
                backFrag = 0;
                tabChooser(dataBinding.vHome,dataBinding.clBottomNavHome);
                showToast(mActivity,"Comming Soon");
                fragment = new DashboardHome();
//                fragment = new HomeFragment();
                break;
            case 2:// Business
                backFrag = 1;
                tabChooser(dataBinding.vBusiness,dataBinding.clBottomNavHome);
                showToast(mActivity,"Comming Soon");
//                fragment = new HomeFragment();
                break;

            case 3:// Entertainment Home
                backFrag = 1;
                tabChooser(dataBinding.vEntertainment,dataBinding.clBottomNavEntertainment);
                fragment = new HomeFragment();
                break;

            case 31:// Entertainment Category
                backFrag = 3;
                fragment = new CategoryFragment();
                break;

            case 32:// Entertainment AllVideos
                backFrag = 3;
                fragment = new AllVideoFragment();
                break;

            case 33:// Entertainment Favourites
                backFrag = 3;
                fragment = new FavoriteFragment();
                break;


            case 34:// Entertainment Favourites
                backFrag = 3;
                fragment = new DownloadsFragment();
                break;

            case 4:// News
                backFrag = 1;
                showToast(mActivity,"Comming Soon");
//                fragment = new AllVideoFragment();
                break;

            case 5:// Sports
                backFrag = 1;
                showToast(mActivity,"Comming Soon");
//                fragment = new FavoriteFragment();
                break;

            case 6:
                backFrag = 1;
                showToast(mActivity,"Comming Soon");
//                fragment = new DownloadsFragment();
                break;

            default:
                showToast(mActivity, "Comming Soon");
                break;
        }


        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction tx = fragmentManager.beginTransaction();
            tx.replace(R.id.frame_dashBoard, fragment);
            tx.commit();
            System.gc();

        } else {
            showLog("Dashboard", "Error in creating fragment");
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            // TODO Auto-generated method stub
            if (!hasFocus) {
                searchMenuItem.collapseActionView();
                searchView.setQuery("", false);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Dashboard.this, ActivitySearch.class);
                intent.putExtra("search", arg0);
                startActivity(intent);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String arg0) {
                // TODO Auto-generated method stub
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                Intent i = new Intent(this, NotificationActivity.class);
                this.startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void setIds() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AudienceNetworkAds.initialize(this);
        prf = new PrefManager(Dashboard.this);
        mActivity = Dashboard.this;
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dataBinding.dlDashBoard, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        dataBinding.dlDashBoard.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        if (dataBinding.bbBottomNavHome != null) {
            selectEntertainmentPage(0);
            dataBinding.bbBottomNavHome.setNavigationChangeListener((view, position) -> {
                selectEntertainmentPage(position);
            });
        }


    }

    protected void selectHomePage(int item) {
        if(dataBinding.clBottomNavHome.getVisibility()== View.VISIBLE) {
            dataBinding.bbBottomNavHome.setCurrentActiveItem(item);
            switch (item) {
                case 0:
                    displayView(3);
                    break;
                case 1:
                    displayView(31);
                    break;
                case 2:
                    displayView(32);
                    break;
                case 3:
                    displayView(33);
                    break;

                case 4:
                    displayView(34);
                    break;
            }
        }
    }

    protected void selectEntertainmentPage(int item) {
        if(dataBinding.clBottomNavEntertainment.getVisibility()== View.VISIBLE) {
            dataBinding.bbBottomNavEntertainment.setCurrentActiveItem(item);
            switch (item) {
                case 0:
                    displayView(3);
                    break;
                case 1:
                    displayView(31);
                    break;
                case 2:
                    displayView(32);
                    break;
                case 3:
                    displayView(33);
                    break;

                case 4:
                    displayView(34);
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(backFrag==3){
            selectEntertainmentPage(0);
        }else if(backFrag==1){
            displayView(1);
        }else {
            super.onBackPressed();
        }
    }
}
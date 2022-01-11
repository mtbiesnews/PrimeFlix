package com.rcloud.business;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.fragment.ProfileFragment;
import com.example.helper.BottomNavigationBehavior;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.map_location.Map_Fragment;
import com.rcloud.business.fragment.BusineesProfileFrag.PostJobFragment;
import com.rcloud.business.fragment.MarraigeProfileFragment;
import com.rcloud.business.fragment.home_mtbies;
import com.rcloud.business.views.fragments.media;
import com.rcloud.netflix.ActivitySearch;
import com.rcloud.netflix.NotificationActivity;
import com.rcloud.netflix.R;

import java.util.ArrayList;
import java.util.List;

public class Mtbies extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav_View;
    private androidx.appcompat.widget.Toolbar toolbar,toolbar1;
    private BubbleNavigationConstraintView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mtbies);
        tab = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewPager);
        drawerLayout = findViewById(R.id.MdrawerLayout);
        nav_View = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        toolbar1 = findViewById(R.id.toolbar1);
        navigationView = findViewById(R.id.bottom_navaa);


        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());








    //Setting ToolBar
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar1, R.string.start, R.string.close);
        toggle.syncState();
     //Setting Drawer Layout
        drawerLayout.addDrawerListener(toggle);
        //Prepare View Pager
        prepareViewPager(viewPager);

        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.view_profile:
                        ProfileFragment fragment = new ProfileFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.add(R.id.Container, fragment,"home");
                        transaction.commit();                            break;

                    case R.id.business:
                        startActivity(new Intent(Mtbies.this,BusineesProfileActivity.class));
                        break;
                    case R.id.jobs:
                        PostJobFragment fragment2 = new PostJobFragment();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        transaction1.add(R.id.Container, fragment2,"home");
                        transaction1.commit();
                        break;

                    case R.id.matrimonial:
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        MarraigeProfileFragment fragment3 = new MarraigeProfileFragment();
                        FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                        transaction3.add(R.id.Container, fragment3,"home");
                        transaction3.commit();
                        break;

                    case R.id.location:
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        Map_Fragment fragment4 = new Map_Fragment();
                        FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                        transaction4.add(R.id.Container, fragment4,"home");
                        transaction4.commit();
                        break;
                }


                return true;
            }
        });


        if (navigationView != null) {
            navigationView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
                @Override
                public void onNavigationChanged(View view, int position) {
                    switch (view.getId()) {
                        case R.id.call:
                            Toast.makeText(getApplicationContext(), "call", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.network:
                            Toast.makeText(getApplicationContext(), "network", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.message:
                            Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.bank:
                            Toast.makeText(getApplicationContext(), "bank", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.media:
                            Toast.makeText(getApplicationContext(), "media", Toast.LENGTH_SHORT).show();
                            break;





                    }
                }
            });
        }





    }

    private void prepareViewPager(ViewPager viewPager) {


        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());

        home_mtbies hm = new home_mtbies();
        home_mtbies hm1 = new home_mtbies();
        home_mtbies hm2 = new home_mtbies();
        home_mtbies hm3 = new home_mtbies();
        media me = new media();


        adapter.addFragment(hm, "Home");
        adapter.addFragment(me, "");
        adapter.addFragment(hm1,"");
        adapter.addFragment(hm2,"");
        adapter.addFragment(hm3,"");

        viewPager.setAdapter(adapter);


        tab.setupWithViewPager(viewPager);

        for(int o = 0; o<tab.getTabCount();o++){
            if (o==0){
                tab.getTabAt(0).setCustomView(R.layout.home_tab);
                tab.getTabAt(2).setCustomView(R.layout.other_tab1);
                tab.getTabAt(1).setCustomView(R.layout.other_tabs);
                tab.getTabAt(3).setCustomView(R.layout.other_tab2);
                tab.getTabAt(4).setCustomView(R.layout.other_tab3);

                tab.getTabAt(0).getCustomView().post(new Runnable()
                {

                    @Override
                    public void run()
                    {
//                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) toolbar.getLayoutParams();
//                        params.setMargins( tab.getTabAt(0).getCustomView().getMeasuredWidth(), 0, 0, 0);
//                        toolbar.setLayoutParams(params);

                    }
                });




            }
            else {
//                String op =tab.getTabAt(o).getText().toString();
//                tab.getTabAt(o).setCustomView(R.layout.other_tabs);
//                TextView title =  tab.getTabAt(o).getCustomView().findViewById(R.id.title);
//
//                title.setText(op);



            }
        }

    }


    ///View Pager Adapter
    private class viewPagerAdapter extends FragmentPagerAdapter {

        ArrayList<String> nameList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {

            nameList.add(title);
            fragmentList.add(fragment);
        }

        public viewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {


            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return nameList.get(position);
        }
    }


    //Adding search bar and notification menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        final MenuItem searchMenuItem = menu.findItem(R.id.search);
        final MenuItem chat = menu.findItem(R.id.chat);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (!hasFocus) {
                    searchMenuItem.collapseActionView();
                    searchView.setQuery("", false);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Mtbies.this, ActivitySearch.class);
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

    //Opening Notification & chat menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:
                Intent i = new Intent(this, NotificationActivity.class);
                this.startActivity(i);
                break;
            case R.id.chat:
                Toast.makeText(getApplicationContext(), "Lets chat", Toast.LENGTH_SHORT).show();
                break;

            default:
        }
        return super.onOptionsItemSelected(item);

    }

}
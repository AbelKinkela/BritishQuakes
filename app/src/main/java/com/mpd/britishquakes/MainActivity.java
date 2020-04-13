/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final static String url = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";
    ArrayList<ItemClass> data;
    ParcelableItem parcelableItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            parcelableItem=savedInstanceState.getParcelable("data");
            data=parcelableItem.arrayList;
            callBackData(data);
        }else{
            new GetDataTask(this, url).execute();
        }
        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);



    }

    public void callBackData(ArrayList<ItemClass> itemList) {
        this.data = itemList;
        initPagerAdapterAndTabLayout(data);

    }

    public void initPagerAdapterAndTabLayout(ArrayList<ItemClass> items) {
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), items);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<ItemClass> items = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<ItemClass> items) {

            super(fm);
            this.items = items;
        }

        @Override
        public int getCount() {
            return 2;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                //Fragment EarthQuakeFragment reused
                case 0:
                    EarthQuakeFragment newEarthQuakes = new EarthQuakeFragment();
                    Bundle newEarthQuakesBundle = new Bundle();
                    ParcelableItem parcelableNewItems = new ParcelableItem(Helper.filterEvents(this.items, "new"));
                    newEarthQuakesBundle.putParcelable("items", parcelableNewItems);
                    newEarthQuakes.setArguments(newEarthQuakesBundle);
                    return newEarthQuakes;
                case 1:
                    EarthQuakeFragment oldEarthQuakes = new EarthQuakeFragment();
                    Bundle newEarthQuakesBundle2 = new Bundle();
                    ParcelableItem parcelableNewItems2 = new ParcelableItem(Helper.filterEvents(this.items, "old"));
                    newEarthQuakesBundle2.putParcelable("items", parcelableNewItems2);
                    oldEarthQuakes.setArguments(newEarthQuakesBundle2);
                    return oldEarthQuakes;
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.new_earth_quakes_tab);
                case 1:
                    return getResources().getText(R.string.Old_Earth_quakes_tab);
            }
            return null;
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        ParcelableItem parcelableItem = new ParcelableItem(data);
        savedInstanceState.putParcelable("data",parcelableItem);

    }

/*    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            parcelableItem=savedInstanceState.getParcelable("data");
            data=parcelableItem.arrayList;
            callBackData(data);
        }else{
            new GetDataTask(this, url).execute();
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }
}
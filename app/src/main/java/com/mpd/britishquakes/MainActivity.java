package com.mpd.britishquakes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private final static String url = "https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        new GetDataTask(this, url).execute();

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);

        //Attach the ViewPager to the TabLayout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
    }

    public void callBackData(ArrayList<ItemClass> itemList) {
        //Filter and display new events
        ArrayList<ItemClass> newEventsList =new ArrayList<>();
        newEventsList.addAll(itemList);
        newEventsList.removeAll(filterEvents(itemList));
        ParcelableItem parcelableNewEvents = new ParcelableItem(newEventsList);
        Bundle newEventsBundle= new Bundle();
        newEventsBundle.putParcelable("newEventsList",parcelableNewEvents);


        /*       //ListView home_listView = (ListView) findViewById(R.id.listView_LIST);
        CaptionedItemAdapter adapter = new CaptionedItemAdapter(itemTitles, itemDates);
        itemRecycler.setAdapter(adapter);
       ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, newEventsList);
       home_listView.setAdapter(arrayAdapter);*/

        //Filter and display Old events
        ListView old_events_listView = (ListView) findViewById(R.id.listView_old_events);
        ArrayList<ItemClass> oldEventsList = filterEvents(itemList);
        ArrayAdapter oldEventsAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, oldEventsList);
        old_events_listView.setAdapter(oldEventsAdapter);

    }



    public ArrayList<ItemClass> filterEvents(ArrayList<ItemClass> itemList){
        Date today = new Date();

        long daysInMilli = 86400000;
        long oldEventTime = 90;
        ArrayList<ItemClass> oldEvents = new ArrayList<>();
        for (ItemClass itemClass: itemList) {
            long differenceDays = ((today.getTime() - itemClass.getPubDate().getTime())/daysInMilli);
            System.out.println("Difference days: "+differenceDays);
            if( differenceDays > oldEventTime){
                oldEvents.add(itemClass);
            }
        }
        return oldEvents;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this will add items to the app bar.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                //Code to run when the Create Order item is clicked
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TopFragment();
                case 1:
                    return new OldEarthQuakes();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.home_tab);
                case 1:
                    return getResources().getText(R.string.Old_Earth_quakes_tab);
            }
            return null;
        }
    }

}
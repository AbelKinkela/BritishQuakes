/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthQuakeDetail extends AppCompatActivity {
    public static final String EXTRA_EARTHQUAKE_ID = "earthquakeId";
    ArrayList<ItemClass> newEventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake_detail);

        //Set the toolbar as the activity's app bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int earthquakeId = (Integer)getIntent().getExtras().get(EXTRA_EARTHQUAKE_ID);
        ParcelableItem parcelableItem = getIntent().getExtras().getParcelable("items");
        newEventsList= parcelableItem.arrayList;

        TextView title_value_textView = (TextView)findViewById(R.id.event_title_textView);
        title_value_textView.setText(newEventsList.get(earthquakeId).getTitle());

        TextView date_value_textView = (TextView)findViewById(R.id.occurred_on_textView);
        date_value_textView.setText(newEventsList.get(earthquakeId).getStringPubDate());

        TextView location_value_textView = (TextView)findViewById(R.id.location_textView);
        location_value_textView.setText(newEventsList.get(earthquakeId).getLocation());

        TextView magnitude_value_textView = (TextView)findViewById(R.id.magnitude_textView);
        magnitude_value_textView.setText(newEventsList.get(earthquakeId).getMagnitude());

        TextView latitude_value_textView = (TextView)findViewById(R.id.latitude_textView);
        latitude_value_textView.setText(newEventsList.get(earthquakeId).getLatitude());

        TextView longitude_value_textView = (TextView)findViewById(R.id.longitude_textView);
        longitude_value_textView.setText(newEventsList.get(earthquakeId).getLongitude());
    }
}

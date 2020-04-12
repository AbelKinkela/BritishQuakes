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
        String event_title = newEventsList.get(earthquakeId).getTitle();
        TextView title_value_textView = (TextView)findViewById(R.id.title_value_textView);
        title_value_textView.setText(event_title);

        String event_date = newEventsList.get(earthquakeId).getPubDate().toString();
        TextView date_value_textView = (TextView)findViewById(R.id.date_value_textView);
        date_value_textView.setText(event_date);

    }
}

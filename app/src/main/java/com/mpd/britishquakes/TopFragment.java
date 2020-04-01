package com.mpd.britishquakes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;



public class TopFragment extends Fragment {

LinkedList<ItemClass> items = new LinkedList<>();

    String[] itemTitles;
    Date[] itemDates;
    ArrayList<ItemClass> newEventsList;
    public TopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getArguments() !=null){
            System.out.println("Items were sent your at Top fragment!");
            ParcelableItem parcelableItem= getArguments().getParcelable("newEventsList");
            newEventsList = parcelableItem.arrayList;
        }
        RecyclerView itemRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_top, container, false);

        String[] itemTitles=getItemTitles(newEventsList);
        Date[] itemDates=getItemDates(newEventsList);

        CaptionedItemAdapter adapter = new CaptionedItemAdapter(itemTitles, itemDates);
        itemRecycler.setAdapter(adapter);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top, container, false);
    }


    private String[] getItemTitles(ArrayList<ItemClass> newEventsList) {
        String[] titles= new String[newEventsList.size()];
        int count=0;
        for(ItemClass itemClass: newEventsList){
            titles[count]=itemClass.getTitle();
            count++;
        }
        return titles;
    }

    private Date[] getItemDates(ArrayList<ItemClass> newEventsList) {
        Date[] dates= new Date[newEventsList.size()];
        int count=0;
        for(ItemClass itemClass: newEventsList){
            dates[count]=itemClass.getPubDate();
            count++;
        }
        return dates;
    }



}

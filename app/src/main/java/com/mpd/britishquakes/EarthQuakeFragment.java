package com.mpd.britishquakes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;



public class EarthQuakeFragment extends Fragment {

LinkedList<ItemClass> items = new LinkedList<>();

    String[] itemTitles;
    Date[] itemDates;
    ArrayList<ItemClass> newEventsList;
    ParcelableItem parcelableItem;


    public EarthQuakeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        RecyclerView itemRecycler = (RecyclerView)inflater.inflate(
                R.layout.fragment_earth_quakes, container, false);
        if (savedInstanceState != null) {
            parcelableItem=savedInstanceState.getParcelable("data");
            newEventsList=parcelableItem.arrayList;
        }
        if (getArguments()!=null){
            parcelableItem = getArguments().getParcelable("items");
            newEventsList= parcelableItem.arrayList;
        }
        String[] itemTitles=Helper.getItemTitles(newEventsList);
        String[] itemDates=Helper.getItemDates(newEventsList);

        CaptionedItemAdapter adapter = new CaptionedItemAdapter(itemTitles, itemDates);
        itemRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        itemRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedItemAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), EarthQuakeDetail.class);
                intent.putExtra("items", parcelableItem);
                intent.putExtra(EarthQuakeDetail.EXTRA_EARTHQUAKE_ID, position);
                getActivity().startActivity(intent);

            }
        });

        return itemRecycler;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putParcelable("data",parcelableItem);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            parcelableItem=savedInstanceState.getParcelable("data");
            newEventsList=parcelableItem.arrayList;
        }
    }
}

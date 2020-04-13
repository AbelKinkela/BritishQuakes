/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;



public class EarthQuakeFragment extends Fragment {

LinkedList<ItemClass> items = new LinkedList<>();
    CaptionedItemAdapter adapter;
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
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {
            parcelableItem=savedInstanceState.getParcelable("data");
            newEventsList=parcelableItem.arrayList;
        }
        if (getArguments()!=null){
            parcelableItem = getArguments().getParcelable("items");
            newEventsList= parcelableItem.arrayList;
        }

        adapter = new CaptionedItemAdapter(newEventsList);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem searchItem=menu.findItem(R.id.app_bar_search);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setIconifiedByDefault(false);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }
                    @Override
                    public boolean onQueryTextChange(String newText) {
                        adapter.getFilter().filter(newText);
                        return true;
                    }
                });


                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

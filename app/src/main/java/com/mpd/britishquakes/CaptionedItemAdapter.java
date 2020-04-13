/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;

public class CaptionedItemAdapter extends RecyclerView.Adapter<CaptionedItemAdapter.ViewHolder> implements Filterable {

    private ArrayList<ItemClass> exampleList;
    private ArrayList<ItemClass> fullList;

    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_item, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CardView cardView = holder.cardView;

        ImageView imageView = (ImageView)cardView.findViewById(R.id.eventImage);

        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), R.drawable.ic_earthquake1);
        imageView.setImageDrawable(drawable);

        ItemClass itemClass = exampleList.get(position);
        TextView title_textView = (TextView) cardView.findViewById(R.id.title_textView);
        title_textView.setText(itemClass.getTitle());
        TextView date_textView = (TextView) cardView.findViewById(R.id.date_textView);
        date_textView.setText(itemClass.getStringPubDate());
        TextView location_textView = (TextView) cardView.findViewById(R.id.location_textView);
        location_textView.setText(itemClass.getLocation());
        TextView depth_textView = (TextView) cardView.findViewById(R.id.depth_textView);
        depth_textView.setText(itemClass.getDepth());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
                }
            });

    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return filteredList;
    }

    private Filter filteredList = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ItemClass> filtered = new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filtered.addAll(fullList);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(ItemClass itemClass : fullList){
                    if(itemClass.toString().toLowerCase().contains(filterPattern)){
                        filtered.add(itemClass);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((ArrayList<ItemClass>) results.values);
            notifyDataSetChanged();

        }
    };

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

    }

    public CaptionedItemAdapter(ArrayList<ItemClass> exampleList) {

        this.exampleList = exampleList;
        this.fullList= new ArrayList<>(exampleList);

    }


}

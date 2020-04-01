package com.mpd.britishquakes;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;

public class CaptionedItemAdapter extends RecyclerView.Adapter<CaptionedItemAdapter.ViewHolder>{

    Date pubDate[];
    String title[];

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_item, parent, false);

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
/*        ImageView imageView = (ImageView)cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);*/
        TextView title_textView = (TextView)cardView.findViewById(R.id.title_textView);
        title_textView.setText(title[position]);
        TextView date_textView = (TextView)cardView.findViewById(R.id.date_textView);
        date_textView.setText(title[position]);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }

    }


    public CaptionedItemAdapter(String[] title, Date[] pubDate){
        this.pubDate= pubDate;
        this.title = title;
    }



}

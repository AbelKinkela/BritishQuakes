package com.mpd.britishquakes;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemClass{
    private Date pubDate;
    private String title;
    private String description;
    private String location;
    private String magnitude;
    private String depth;
    private String link;
    private String longitude;
    private String latitude;

/*    public ItemClass(Parcel parcel){
        pubDate=parcel.readString();
        title=parcel.readString();
        description=parcel.readString();
        location=parcel.readString();
        magnitude=parcel.readString();
        depth=parcel.readString();
        link=parcel.readString();
        longitude=parcel.readString();
        latitude=parcel.readString();
    }
    public static final Parcelable.Creator<ItemClass> CREATOR = new Parcelable.Creator<ItemClass>() {
        @Override
        public ItemClass createFromParcel(Parcel parcel) {
            return new ItemClass(parcel);
        }

        @Override
        public ItemClass[] newArray(int size) {
            return new ItemClass[size];
        }
    };

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pubDate);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(location);
        dest.writeString(magnitude);
        dest.writeString(depth);
        dest.writeString(link);
        dest.writeString(longitude);
        dest.writeString(latitude);

    }*/

    public ItemClass() {
        pubDate = null;
        title = "";
        description = "";
        link = "";
        longitude = "";
        latitude = "";

    }


    public ItemClass(Date pubDate, String title, String description, String link, String longitude, String latitude) {
        this.pubDate = pubDate;
        this.title = title;
        this.description = description;
        this.link = link;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        try {
            Date aDate = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss").parse(pubDate);
            this.pubDate = aDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        setLocation(description);
        setMagnitude(description);
        setDepth(description);
        this.description = description.replaceAll("Location.*?;", "");

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        Pattern pattern = Pattern.compile("Location: (.*?);");
        Matcher matcher = pattern.matcher(location);
        if (matcher.find()) {
            this.location = matcher.group(1);
        }
    }

    public String getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(String magnitude) {
        Pattern pattern = Pattern.compile("Magnitude: (.*)");
        Matcher matcher = pattern.matcher(magnitude);
        if (matcher.find()) {
            this.magnitude = matcher.group(1);
        }
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        Pattern pattern = Pattern.compile("Depth: (.*?);");
        Matcher matcher = pattern.matcher(depth);
        if (matcher.find()) {
            this.depth = matcher.group(1);
        }
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void removeDateFromTitle() {
        setTitle(getTitle().replaceAll(",.*", ""));
    }


    @Override
    public String toString() {
        removeDateFromTitle();
        return String.format("PubDate: %s\nTitle: %s\nDescription: %s\nLocation: %s\nMagnitude: %s\nDepth: %s\nLat: %s\nLong: %s",
                getPubDate(),
                getTitle(),
                getDescription(),
                getLocation(),
                getMagnitude(),
                getDepth(),
                getLatitude(),
                getLongitude());
    }


} // End of class
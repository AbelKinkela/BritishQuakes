/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemClass implements Serializable{
    private Date pubDate;
    private String stringPubDate;
    private String title;
    private String description;
    private String location;
    private String magnitude;
    private String depth;
    private String link;
    private String longitude;
    private String latitude;



    public ItemClass() {
        pubDate = null;
        title = "Check your connection and try again!";
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
        setStringPubDate(pubDate);
        try {
            Date aDate = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss").parse(pubDate);
            this.pubDate = aDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public String getStringPubDate() {
        return stringPubDate;
    }

    public void setStringPubDate(String pubDate) {
        this.stringPubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        Pattern pattern = Pattern.compile("(:.*?,)");
        Matcher matcher = pattern.matcher(title);
        if (matcher.find()) {
            this.title = clearTitle(matcher.group(1));
        }

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
        return "Depth: "+depth;
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

    public String clearTitle(String title){
        int x= title.length();
        String newValue =title.substring(2, x-1);

        return newValue;
    }
} // End of class
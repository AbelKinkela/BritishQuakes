package com.mpd.britishquakes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "britishquakes"; // the name of our database
    private static final int DB_VERSION = 1; // the version of the database

    DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ITEM ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "DESCRIPTION TEXT, "
                + "PUBDATE TEXT,"
                + "LINK TEXT, "
                + "LONGITUDE TEXT, "
                + "LATITUDE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static void insertItem(SQLiteDatabase db, String title, String description, String pubDate, String link, String longitude, String latitude) {
        ContentValues itemValues = new ContentValues();
        itemValues.put("TITLE", title);
        itemValues.put("DESCRIPTION", title);
        itemValues.put("PUBDATE", title);
        itemValues.put("LINK", title);
        itemValues.put("LONGITUDE", title);
        itemValues.put("LATITUDE", title);
        db.insert("ITEM", null, itemValues);
    }

    private static void updateItem(SQLiteDatabase db, String title, String description, String pubDate, String link, String longitude, String latitude) {
        ContentValues itemValues = new ContentValues();
        itemValues.put("TITLE", title);
        itemValues.put("DESCRIPTION", title);
        itemValues.put("PUBDATE", title);
        itemValues.put("LINK", title);
        itemValues.put("LONGITUDE", title);
        itemValues.put("LATITUDE", title);
        db.update("ITEM",  itemValues, "TITLE = ?", new String[]{title});
    }



}

/*
Author: Abel Makanzu Kinkela
Student ID: S1803438
 */
package com.mpd.britishquakes;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ParcelableItem implements Parcelable {
    ArrayList<ItemClass> arrayList;

    public ParcelableItem(Parcel parcel){
        arrayList=parcel.readArrayList(ItemClass.class.getClassLoader());

    }
    public static final Parcelable.Creator<ParcelableItem> CREATOR = new Parcelable.Creator<ParcelableItem>() {
        @Override
        public ParcelableItem createFromParcel(Parcel parcel) {
            return new ParcelableItem(parcel);
        }

        @Override
        public ParcelableItem[] newArray(int size) {
            return new ParcelableItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(arrayList);

    }
    public ParcelableItem(ArrayList<ItemClass> arrayList){
        this.arrayList = arrayList;
    }



}

package com.mpd.britishquakes;

import java.util.ArrayList;
import java.util.Date;

public class Helper {

    static ArrayList<ItemClass> filterEvents(ArrayList<ItemClass> itemList, String type) {
        Date today = new Date();

        long daysInMilli = 86400000;
        long oldEventTime = 90;
        ArrayList<ItemClass> events = new ArrayList<>();
        if(type=="new"){
            for (ItemClass itemClass : itemList) {
                long differenceDays = ((today.getTime() - itemClass.getPubDate().getTime()) / daysInMilli);
                if (differenceDays < oldEventTime) {
                    events.add(itemClass);
                }
            }
        }else {
            for (ItemClass itemClass : itemList) {
                long differenceDays = ((today.getTime() - itemClass.getPubDate().getTime()) / daysInMilli);
                System.out.println("Difference days: " + differenceDays);
                if (differenceDays > oldEventTime) {
                    events.add(itemClass);
                }
            }

        }
        return events;
    }

    static String[] getItemTitles(ArrayList<ItemClass> newEventsList) {
        String[] titles= new String[newEventsList.size()];
        int count=0;
        for(ItemClass itemClass: newEventsList){
            titles[count]=itemClass.getTitle();
            count++;
        }
        return titles;
    }

    static String[] getItemDates(ArrayList<ItemClass> newEventsList) {
        String[] dates= new String[newEventsList.size()];
        int count=0;
        for(ItemClass itemClass: newEventsList){
            dates[count]=itemClass.getStringPubDate();
            count++;
        }
        return dates;
    }

}

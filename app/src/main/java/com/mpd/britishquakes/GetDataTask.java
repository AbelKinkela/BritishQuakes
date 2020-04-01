package com.mpd.britishquakes;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class GetDataTask extends AsyncTask<String[], Void, ArrayList<ItemClass> > {

    private MainActivity activity;
    ArrayList<ItemClass> itemList=new ArrayList<>();
    ItemClass item=new ItemClass();
    private String url;
    private XmlPullParserFactory xmlFactoryObject;
    private ProgressDialog pDialog;

    public GetDataTask(){}

    public GetDataTask(MainActivity activity, String url) {
        this.activity = activity;
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setTitle("Earthquakes Information");
        pDialog.setMessage("Loading...");
        pDialog.show();
    }

    @Override
    protected ArrayList<ItemClass> doInBackground(String[]... params) {
        try {
            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.setConnectTimeout(15000 /* milliseconds */);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            InputStream stream = connection.getInputStream();

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myParser = xmlFactoryObject.newPullParser();

            myParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            myParser.setInput(stream, null);
            ArrayList<ItemClass> itemList = parseXML(myParser);
            stream.close();

            return itemList;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AsyncTask", "exception");
            return null;
        }
    }

    public ArrayList<ItemClass> parseXML(XmlPullParser myParser) {

        int event;
        String text = null;

        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = myParser.getName();

                // Found a start tag
                if (event == XmlPullParser.START_TAG) {
                    // Check which Tag we have
                    if (myParser.getName().equalsIgnoreCase("channel")) {
                        itemList = new ArrayList<ItemClass>();
                    } else if (myParser.getName().equalsIgnoreCase("item")) {
                        item = new ItemClass();
                    } else if (myParser.getName().equalsIgnoreCase("title")) {
                        // Now just get the associated text
                        String temp = myParser.nextText();
                        // Do something with text
                        item.setTitle(temp);
                    } else
                        // Check which Tag we have
                        if (myParser.getName().equalsIgnoreCase("description")) {
                            // Now just get the associated text
                            String temp = myParser.nextText();
                            // Do something with text
                            item.setDescription(temp);
                        } else
                            // Check which Tag we have
                            if (myParser.getName().equalsIgnoreCase("link")) {
                                // Now just get the associated text
                                String temp = myParser.nextText();
                                // Do something with text
                                item.setLink(temp);
                            } else
                                // Check which Tag we have
                                if (myParser.getName().equalsIgnoreCase("pubdate")) {
                                    // Now just get the associated text
                                    String temp = myParser.nextText();
                                    // Do something with text
                                    item.setPubDate(temp);
                                } else
                                    // Check which Tag we have
                                    if (myParser.getName().equalsIgnoreCase("geo:lat")) {
                                        // Now just get the associated text
                                        String temp = myParser.nextText();
                                        // Do something with text
                                        item.setLongitude(temp);

                                    }
                                    else //
                                        if (myParser.getName().equalsIgnoreCase("geo:long")) {
                                        // Now just get the associated text
                                        String temp = myParser.nextText();
                                        // Do something with text
                                        item.setLatitude(temp);

                                    }
                } else if (event == XmlPullParser.END_TAG) {
                    if (myParser.getName().equalsIgnoreCase("item")) {
                        itemList.add(item);
                    } else if (myParser.getName().equalsIgnoreCase("channel")) {
                        int size;
                        size = itemList.size();
                        Log.e("MyTag", "channel size is " + size);
                    }
                }
                // Get the next event
                event = myParser.next();

            } // end of while


        } catch (XmlPullParserException ae1) {
            Log.e("MyTag", "Parsing error" + ae1.toString());
        } catch (IOException ae1) {
            Log.e("MyTag", "IO error during parsing");
        }

        return itemList;
    }

    @Override
    protected void onPostExecute(ArrayList<ItemClass> itemList) {
        //call back data to main thread
        pDialog.dismiss();
        activity.callBackData(itemList);

    }
}
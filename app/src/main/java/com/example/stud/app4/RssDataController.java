package com.example.stud.app4;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.stud.app4.Utils.RSSXMLTag.DESCRIPTION;
import static com.example.stud.app4.Utils.RSSXMLTag.IGNORETAG;
import static com.example.stud.app4.Utils.RSSXMLTag.LINK;
import static com.example.stud.app4.Utils.RSSXMLTag.TITLE;

/**
 * Created by CaNou on 11/22/2016.
 */



public  abstract class RssDataController extends AsyncTask< //comment >
        String,
        Integer,
        ArrayList<Stire>> {

    private Utils.RSSXMLTag currentTag;
    ArrayList<Stire> postStiriList = new ArrayList<Stire>();


    @Override
    protected ArrayList<Stire> doInBackground(String... params) {

        // TODO Auto-generated method stub
        String urlStr = params[0];

        InputStream is = null;


        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();

            int response = connection.getResponseCode();
            Log.d("debug", "The response is: " + response);
            is = connection.getInputStream();


            if(params.length == 2){
                getRssItem(is, Integer.valueOf(params[1]));
            }else if(params.length == 1) {
                parseRss(is);
            }else{
                getRssItemInterval(is, Integer.valueOf(params[1]), Integer.valueOf(params[2]));
            }

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return postStiriList;
    }



    private void parseRss(InputStream is){
        // parse xml after getting the data
        XmlPullParserFactory factory = null;
        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);

            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);

            int eventType = xpp.getEventType();
            Stire stire = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        stire = new Stire();
                        currentTag = IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = LINK;
                    } else if (xpp.getName().equals("description")) {
                        currentTag = DESCRIPTION;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter

                        postStiriList.add(stire);
                    } else {
                        currentTag = IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (stire != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (stire.titlu != null) {
                                        stire.titlu += content;
                                    } else {
                                        stire.titlu = content;
                                    }
                                }
                                break;

                            case LINK:
                                if (content.length() != 0) {
                                    if (stire.urlSursa != null) {
                                        stire.urlSursa += content;
                                    } else {
                                        stire.urlSursa = content;
                                    }
                                }
                                break;
                            case DESCRIPTION:
                                if (content.length() != 0) {
                                    if (stire.text != null) {
                                        stire.text += content;
                                    } else {
                                        stire.text = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("tst", String.valueOf((postStiriList.size())));
    }

    private void getRssItem(InputStream is, int nr){
        // parse xml after getting the data
        XmlPullParserFactory factory = null;

        int nrElem = 0;
        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);

            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);

            int eventType = xpp.getEventType();
            Stire stire = null;
            while (eventType != XmlPullParser.END_DOCUMENT && nrElem <= nr) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        stire = new Stire();
                        currentTag = IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = LINK;
                    } else if (xpp.getName().equals("description")) {
                        currentTag = DESCRIPTION;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter
                        if(nrElem == nr) {
                            postStiriList.add(stire);
                        }

                        nrElem++;
                    } else {
                        currentTag = IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (stire != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (stire.titlu != null) {
                                        stire.titlu += content;
                                    } else {
                                        stire.titlu = content;
                                    }
                                }
                                break;

                            case LINK:
                                if (content.length() != 0) {
                                    if (stire.urlSursa != null) {
                                        stire.urlSursa += content;
                                    } else {
                                        stire.urlSursa = content;
                                    }
                                }
                                break;
                            case DESCRIPTION:
                                if (content.length() != 0) {
                                    if (stire.text != null) {
                                        stire.text += content;
                                    } else {
                                        stire.text = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("tst", String.valueOf((postStiriList.size())));
    }


    private void getRssItemInterval(InputStream is, int st, int dr){
        // parse xml after getting the data
        XmlPullParserFactory factory = null;

        int nrElem = 0;
        try {
            factory = XmlPullParserFactory.newInstance();

            factory.setNamespaceAware(true);

            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(is, null);

            int eventType = xpp.getEventType();
            Stire stire = null;
            while (eventType != XmlPullParser.END_DOCUMENT && nrElem <= dr) {
                if (eventType == XmlPullParser.START_DOCUMENT) {

                } else if (eventType == XmlPullParser.START_TAG) {
                    if (xpp.getName().equals("item")) {
                        stire = new Stire();
                        currentTag = IGNORETAG;
                    } else if (xpp.getName().equals("title")) {
                        currentTag = TITLE;
                    } else if (xpp.getName().equals("link")) {
                        currentTag = LINK;
                    } else if (xpp.getName().equals("description")) {
                        currentTag = DESCRIPTION;
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (xpp.getName().equals("item")) {
                        // format the data here, otherwise format data in
                        // Adapter
                        if(nrElem >= st ) {
                            if(nrElem < dr)
                                postStiriList.add(stire);
                        }
                        nrElem++;

                    } else {
                        currentTag = IGNORETAG;
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    String content = xpp.getText();
                    content = content.trim();
                    Log.d("debug", content);
                    if (stire != null) {
                        switch (currentTag) {
                            case TITLE:
                                if (content.length() != 0) {
                                    if (stire.titlu != null) {
                                        stire.titlu += content;
                                    } else {
                                        stire.titlu = content;
                                    }
                                }
                                break;

                            case LINK:
                                if (content.length() != 0) {
                                    if (stire.urlSursa != null) {
                                        stire.urlSursa += content;
                                    } else {
                                        stire.urlSursa = content;
                                    }
                                }
                                break;
                            case DESCRIPTION:
                                if (content.length() != 0) {
                                    if (stire.text != null) {
                                        stire.text += content;
                                    } else {
                                        stire.text = content;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }

                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v("tst", String.valueOf((postStiriList.size())));
    }
    @Override
    protected  abstract void onPostExecute(ArrayList<Stire> result);
}

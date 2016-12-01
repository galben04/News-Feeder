package com.example.stud.app4;

/**
 * Created by CaNou on 11/12/2016.
 */

public class Site {
    String titlu;
    String url;
    boolean isPref = false;

    public boolean isPref() {
        return isPref;
    }

    public void setPref(boolean pref) {
        isPref = pref;
    }

    public Site(String url, boolean isPref, String titlu) {
        this.url = url;
        this.isPref = isPref;
        this.titlu = titlu;
    }

    public Site(String title, String urlString) {
        titlu = new String(title);
        url = new String(urlString);

    }

    public Site(String urlString){
        titlu = new String(urlString);
        url = new String(urlString);
    }
}

package com.example.stud.app4;

/**
 * Created by CaNou on 11/12/2016.
 */

public class Site {
    String titlu;
    String url;

    public Site(String title,String urlString) {
        titlu = new String(title);
        url = new String(urlString);
    }

    public Site(String urlString){
        titlu = new String(urlString);
        url = new String(urlString);
    }
}

package com.example.stud.app4;

/**
 * Created by CaNou on 11/13/2016.
 */

public class Stire {
    String titlu;

    public Stire(String titlu) {
        this.titlu = titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    String text;
    String urlSursa;

    public Stire(String titlu, String text, String urlSursa) {
        this.titlu = titlu;
        this.text = text;
        this.urlSursa = urlSursa;
    }

    public Stire() {
    }

    public Stire(String titlu, String text){
        this.titlu = titlu;
        this.text = text;
        urlSursa = "";
    }
}

package com.example.stud.app4;

import java.util.ArrayList;

/**
 * Created by CaNou on 11/13/2016.
 */

public class SurseArrayList {

    public ArrayList<Site> list;

    public ArrayList<Site> getList() {
        return list;
    }

    public SurseArrayList(){
        list = new ArrayList<Site>();
    }

    public SurseArrayList(ArrayList<Site> lst){
        list = new ArrayList<Site>(lst);
    }


}

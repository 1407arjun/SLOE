package com.blunderbois.sloe.models;

public class MoodModel {

    String Overall, date, lec1;

    public MoodModel(String Overall, String date, String lec1) {
        this.Overall = Overall;
        this.date = date;
        this.lec1 = lec1;
    }

    public MoodModel() {
    }

    public String getOverall() {
        return Overall;
    }

    public void setOverall(String overall) {
        this.Overall = overall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLec1() {
        return lec1;
    }

    public void setLec1(String lec1) {
        this.lec1 = lec1;
    }
}

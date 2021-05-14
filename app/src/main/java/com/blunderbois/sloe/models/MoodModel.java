package com.blunderbois.sloe.models;

public class MoodModel {

    String overall;

    public MoodModel() {
    }

    String lec1;
    /*String lec2;
    String lec3;
    String lec4;
    String lec5;*/

    public MoodModel(String overall, String lec1, String lec2, String lec3, String lec4, String lec5) {
        this.overall = overall;
        this.lec1 = lec1;
        /*this.lec2 = lec2;
        this.lec3 = lec3;
        this.lec4 = lec4;
        this.lec5 = lec5;*/
    }

    public String getOverall() {
        return overall;
    }

    public void setOverall(String overall) {
        this.overall = overall;
    }

    public String getLec1() {
        return lec1;
    }

    public void setLec1(String lec1) {
        this.lec1 = lec1;
    }

    /*public String getLec2() {
        return lec2;
    }

    public void setLec2(String lec2) {
        this.lec2 = lec2;
    }

    public String getLec3() {
        return lec3;
    }

    public void setLec3(String lec3) {
        this.lec3 = lec3;
    }

    public String getLec4() {
        return lec4;
    }

    public void setLec4(String lec4) {
        this.lec4 = lec4;
    }

    public String getLec5() {
        return lec5;
    }

    public void setLec5(String lec5) {
        this.lec5 = lec5;
    }*/
}

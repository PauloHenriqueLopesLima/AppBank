package com.neomatrix.appbank.model;

import java.util.ArrayList;
import java.util.Date;

public class Statements {

    private ArrayList < Statements > statementList = new ArrayList<>();


    private String title;
    private String desc;
    private String date;
    private float value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}

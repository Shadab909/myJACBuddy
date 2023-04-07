package com.android.myjacbuddy.model;

public class QuestionModel {
    String name;
    String link;

    public QuestionModel() {

    }

    public QuestionModel(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

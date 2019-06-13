package com.example.wbsu1viratgoradiaserverjava.models;

import javax.persistence.Entity;

@Entity
public class LinkWidget extends Widget {

    private String url;

    private String text;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

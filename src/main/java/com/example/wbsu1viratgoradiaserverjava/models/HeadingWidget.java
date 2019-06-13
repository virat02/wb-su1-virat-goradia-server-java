package com.example.wbsu1viratgoradiaserverjava.models;

import javax.persistence.Entity;

@Entity
public class HeadingWidget extends Widget {

    private String text;

    private Integer size;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}

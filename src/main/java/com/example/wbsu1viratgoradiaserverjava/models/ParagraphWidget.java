package com.example.wbsu1viratgoradiaserverjava.models;

import javax.persistence.Entity;

@Entity
public class ParagraphWidget extends Widget {

    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}

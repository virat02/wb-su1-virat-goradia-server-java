package com.example.wbsu1viratgoradiaserverjava.models;

import javax.persistence.Entity;

@Entity
public class ImageWidget extends Widget {

    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

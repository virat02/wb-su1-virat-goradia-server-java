package com.example.wbsu1viratgoradiaserverjava.models;

import javax.persistence.Entity;

@Entity
public class ListWidget extends Widget {

    private boolean ordered;
    private String[] items;

    public Boolean getOrdered() {
        return ordered;
    }

    public void setOrdered(Boolean ordered) {
        this.ordered = ordered;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }
}

package com.example.wbsu1viratgoradiaserverjava.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Widgets")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Widget {

    public enum Type {
        Heading, List, Paragraph, Image, Link;
    }

    public enum DataType {
        Integer, String, Date, Boolean;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JsonIgnore
    private Topic topic;

    private String name;
    private int ordr;
    private int width;
    private int height;
    private String cssClass;
    private String style;
    private String value;
    private Type type;
    private DataType dataType;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return this.cssClass;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return this.style;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setId (int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getOrdr() {
        return ordr;
    }

    public void setOrdr(int order) {
        this.ordr = order;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}


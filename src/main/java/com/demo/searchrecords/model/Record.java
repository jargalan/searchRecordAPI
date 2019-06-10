package com.demo.searchrecords.model;


public class Record {
    private String id;
    private String text;

    public Record() {}

    public Record(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }


}


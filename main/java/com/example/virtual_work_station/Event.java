package com.example.virtual_work_station;

public class Event {

    public String id = "";
    public String title = "";
    public String description = "";
    public String location = "";
    public String member = "";
    public String date = "";
    public String type = "";


    public Event(String id, String title, String description, String type, String date, String member, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.member = member;
        this.date = date;
        this.type = type;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


package com.example.virtual_work_station;

public class Business {


    public String Name = "";
    public String Bus_name = "";
    public String location = "";
    public String Description = "";


    public Business(String Name, String Bus_name, String location, String Description) {

        this.Name = Name;
        this.Bus_name = Bus_name;
        this.location = location;
        this.Description = Description;


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBus_name() {
        return Bus_name;
    }

    public void setBus_name(String bus_name) {
        Bus_name = bus_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}

package com.example.virtual_work_station;

public class Member {


    public String name = "";
    public String email = "";
    public String contact = "";
    public String department = "";
    public String date1 = "";


    public Member(String name, String email, String contact, String department, String date1) {

        this.name = name;
        this.email = email;
        this.contact = contact;
        this.department = department;
        this.date1 = date1;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }


}
package com.example.easylunchapp.Classes;

public class User {
    private String Name ;
    private String Email ;
    private String Pass  ;
    private String City;
    private String School ;
    private String ID ;
    private String Role ;

    public User(String name, String email, String pass, String city, String school , String role, String id) {
        Name = name;
        Email = email;
        Pass = pass;
        City = city;
        School = school;
        Role = role ;
        ID=id;
    }

    public User(){}

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRole() {
        return Role;
    }
}

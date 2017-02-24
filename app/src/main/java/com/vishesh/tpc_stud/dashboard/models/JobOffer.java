package com.vishesh.tpc_stud.dashboard.models;

import java.math.BigDecimal;
import java.util.List;

public class JobOffer {

    private int id;
    private String position;
    private int noOfOffers;
    private String location;
    private BigDecimal payPackage;
    private String description;
    private List<Course> courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getNoOfOffers() {
        return noOfOffers;
    }

    public void setNoOfOffers(int noOfOffers) {
        this.noOfOffers = noOfOffers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPayPackage() {
        return payPackage;
    }

    public void setPayPackage(BigDecimal payPackage) {
        this.payPackage = payPackage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

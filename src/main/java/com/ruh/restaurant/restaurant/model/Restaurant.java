package com.ruh.restaurant.restaurant.model;

import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private Double rate;
    private String cuisine;

    public Restaurant() {}

    public Restaurant(int id, String name, String location, Double rate, String cuisine) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rate = rate;
        this.cuisine = cuisine;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }
    public Double getRate() { return rate; }
    public String getCuisine() { return cuisine; }


    public void setId(int id) { this.id = id;}
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setRate(Double rate) { this.rate = rate; }
    public void setCuisine(String cuisine) { this.cuisine = cuisine; }
}
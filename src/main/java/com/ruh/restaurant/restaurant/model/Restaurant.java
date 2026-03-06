package com.ruh.restaurant.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    private int id;
    private String name;
    private String location;
    private Double rating;
    private String cuisine;

    public Restaurant() {

    }

    public Restaurant(int id, String name, String location, Double rating, String cuisine) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.cuisine = cuisine;


    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Double getRating() {
        return rating;
    }

    public String getCuisine() {
        return cuisine;
    }
}

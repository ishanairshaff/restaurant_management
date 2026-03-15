package com.ruh.restaurant.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "restaurant")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("cuisine")
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
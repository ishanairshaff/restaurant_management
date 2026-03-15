package com.ruh.restaurant.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for the Restaurant entity.
 * Uses explicit Jackson annotations to control JSON serialization and
 * deserialization, decoupling the API contract from the persistence model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestaurantDTO {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("cuisine")
    private String cuisine;

    public RestaurantDTO() {}

    @JsonCreator
    public RestaurantDTO(
            @JsonProperty("id")       Integer id,
            @JsonProperty("name")     String name,
            @JsonProperty("location") String location,
            @JsonProperty("rate")     Double rate,
            @JsonProperty("cuisine")  String cuisine) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.rate = rate;
        this.cuisine = cuisine;
    }

    public Integer getId()          { return id; }
    public String  getName()        { return name; }
    public String  getLocation()    { return location; }
    public Double  getRate()        { return rate; }
    public String  getCuisine()     { return cuisine; }

    public void setId(Integer id)            { this.id = id; }
    public void setName(String name)         { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setRate(Double rate)         { this.rate = rate; }
    public void setCuisine(String cuisine)   { this.cuisine = cuisine; }
}

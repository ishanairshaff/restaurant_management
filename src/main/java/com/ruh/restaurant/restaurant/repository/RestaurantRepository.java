package com.ruh.restaurant.restaurant.repository;

import com.ruh.restaurant.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

}
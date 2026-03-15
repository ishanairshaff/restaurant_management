package com.ruh.restaurant.restaurant.service;

import com.ruh.restaurant.restaurant.dto.RestaurantDTO;
import com.ruh.restaurant.restaurant.model.Restaurant;
import com.ruh.restaurant.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public void deleteRestaurantById(int id) {
        restaurantRepository.deleteById(id);
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    // ── DTO serialization helpers ────────────────────────────────

    public RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;
        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getLocation(),
                restaurant.getRate(),
                restaurant.getCuisine()
        );
    }

    public Restaurant fromDTO(RestaurantDTO dto) {
        if (dto == null) return null;
        Restaurant restaurant = new Restaurant();
        if (dto.getId() != null) restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setLocation(dto.getLocation());
        restaurant.setRate(dto.getRate());
        restaurant.setCuisine(dto.getCuisine());
        return restaurant;
    }

    public List<RestaurantDTO> getAllRestaurantsAsDTO() {
        return getAllRestaurants().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RestaurantDTO saveFromDTO(RestaurantDTO dto) {
        Restaurant saved = restaurantRepository.save(fromDTO(dto));
        return toDTO(saved);
    }
}
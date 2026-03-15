package com.ruh.restaurant.restaurant.controller;

import com.ruh.restaurant.restaurant.dto.RestaurantDTO;
import com.ruh.restaurant.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API controller that exposes restaurant data as serialized JSON.
 * Complements the MVC controller by providing a machine-readable API
 * at the /api/restaurants path.
 */
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantApiController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurantsAsDTO());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable int id) {
        RestaurantDTO dto = restaurantService.toDTO(restaurantService.getRestaurantById(id));
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO dto) {
        RestaurantDTO saved = restaurantService.saveFromDTO(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@PathVariable int id,
                                                           @RequestBody RestaurantDTO dto) {
        if (restaurantService.getRestaurantById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        dto.setId(id);
        RestaurantDTO updated = restaurantService.saveFromDTO(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable int id) {
        if (restaurantService.getRestaurantById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}

package com.ruh.restaurant.restaurant.controller;

import com.ruh.restaurant.restaurant.model.Restaurant;
import com.ruh.restaurant.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("listRestaurants", restaurants);
        return "index";
    }

    @GetMapping("/new")
    public String showNewRestaurantForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "new_restaurant";
    }


    @PostMapping("/save")
    public String saveRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/";
    }

}
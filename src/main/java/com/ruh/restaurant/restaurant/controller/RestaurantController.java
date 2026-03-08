package com.ruh.restaurant.restaurant.controller;

import com.ruh.restaurant.restaurant.model.Restaurant;
import com.ruh.restaurant.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    // ── Step 1: Load ALL restaurants from DB freshly ────────────
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listRestaurants", restaurantService.getAllRestaurants());
        return "index";
    }


    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        return "new_restaurant";
    }


    @PostMapping("/save")
    public String saveRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurantById(id));
        return "edit_restaurant";
    }


    @PostMapping("/update")
    public String updateRestaurant(@ModelAttribute("restaurant") Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurantById(id);
        return "redirect:/";
    }
}
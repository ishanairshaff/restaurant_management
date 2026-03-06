package com.ruh.restaurant.restaurant.controller;

import com.ruh.restaurant.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listRestaurants",
                restaurantService.getAllRestaurants());

        return "index";
    }
}
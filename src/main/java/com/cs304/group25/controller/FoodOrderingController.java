package com.cs304.group25.controller;

import com.cs304.group25.model.Restaurant;
import com.cs304.group25.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FoodOrderingController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/hello")
    public String hello (){
        return "hello";
    }

    @GetMapping("/getAllRestaurant")
    public List<Restaurant> getAllRestaurant (){
        return foodService.getAllRestaurant();
    }
}

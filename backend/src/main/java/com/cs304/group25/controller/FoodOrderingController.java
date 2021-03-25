package com.cs304.group25.controller;

import com.cs304.group25.model.Customer;
import com.cs304.group25.model.Deliverer;
import com.cs304.group25.model.Restaurant;
import com.cs304.group25.model.Review;
import com.cs304.group25.service.FoodService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/getAllCustomer")
    public List<Customer> getAllCustomer (){
        return foodService.getAllCustomer();
    }

    @GetMapping("/getAllDeliverer")
    public List<Deliverer> getAllDeliverer (){
        return foodService.getAllDeliverer();
    }

    @GetMapping("/getAllReviews")
    public List<Review> getAllReview (){
        return foodService.getAllReviews();
    }

    @GetMapping("/getAllMatchingReviews")
    public List<Review> getMatchingReview(int id){
        return foodService.getMatchingReview(id);
    }

    @GetMapping("/updateAddress")
    public List<Customer> updateAddress(Customer uCustomer, String uAddress){
        uAddress = "New Street4";
        uCustomer = new Customer(2223,"New Street2","Bill",888444,"aaaa","5y7u");
        foodService.updateAddress(uCustomer, uAddress);
        return getAllCustomer();
    }



}


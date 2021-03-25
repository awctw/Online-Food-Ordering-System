package com.cs304.group25.controller;

import com.cs304.group25.model.Customer;
import com.cs304.group25.model.Deliverer;
import com.cs304.group25.model.Restaurant;
import com.cs304.group25.model.Review;
import com.cs304.group25.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class FoodOrderingController {
    @Autowired
    private FoodService foodService;

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
    public List<Review> getMatchingReview(@RequestParam int id){
        return foodService.getMatchingReview(id);
    }

    @PostMapping("/insertCustomer")
    public Integer insertCustomer(@RequestBody Customer customer){
        return foodService.insertCustomer(customer);
    }

    @GetMapping("/updateAddress")
    public String updateAddress(Customer uCustomer, String uAddress){
         //uAddress = "New Street7";
         //uCustomer = new Customer(2223,"New Street6","Bill",888444,"aaaa","5y7u");
         return foodService.updateAddress(uCustomer, uAddress);
    }

    @PostMapping("/updateCustomer")
    public Integer updateCustomer(@RequestBody Customer customer){
        return foodService.updateCustomer(customer);
    }

}


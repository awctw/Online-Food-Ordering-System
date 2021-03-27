package com.cs304.group25.controller;

import com.cs304.group25.model.*;
import com.cs304.group25.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/getAllPayments")
    public List<Payment> getAllPayment (){
        return foodService.getAllPayments();
    }

    @GetMapping("/getAllMatchingReviews")
    public List<Review> getMatchingReview(@RequestParam Integer id){
        return foodService.getMatchingReview(id);
    }

    @GetMapping("/filterByCategory")
    public List<Restaurant.RestaurantCol> filterByCategory(@RequestParam String category) {
        //category = "Dessert";
        return foodService.filterByCategory(category);
    }

    @GetMapping("/filterByRating")
    public List<Restaurant> filterByRating(@RequestParam Integer rating) {
        //rating = 3;
        return foodService.filterByRating(rating);
    }

    // Generated new table getRestaurantMenuItems
    @GetMapping("/getRestaurantMenuItems")
    public List<RestaurantMenuItems> getRestaurantMenuItems(@RequestParam String menuType) {
        //menuType = "Lunch";
        return foodService.getRestaurantMenuItems(menuType);
    }

    @PostMapping("/insertCustomer")
    public Integer insertCustomer(@RequestBody Customer customer){
        return foodService.insertCustomer(customer);
    }

    // Should change into PutMapping?
    @PostMapping("/updateCustomerAddress")
    public Integer updateCustomer(@RequestBody Customer customer, @RequestParam String address){
        return foodService.updateCustomerAddress(customer, address);
    }

    @PostMapping("/insertPayment")
    public Integer insertPayment(@RequestBody Payment payment){
        return foodService.insertPayment(payment);
    }

    @DeleteMapping("/deleteOrderDetails")
    public void deleteOrderDetails(@RequestBody Order order, @RequestParam Integer id) {
        foodService.deleteOrderDetails(order, id);
    }

}


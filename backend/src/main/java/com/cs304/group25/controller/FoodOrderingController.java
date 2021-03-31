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

    @GetMapping("/getAllRestaurants")
    public List<Restaurant> getAllRestaurant (){
        return foodService.getAllRestaurant();
    }

    @GetMapping("/getAllCustomers")
    public List<Customer> getAllCustomer (){
        return foodService.getAllCustomer();
    }

    @GetMapping("/getAllDeliverers")
    public List<Deliverer> getAllDeliverer (){
        return foodService.getAllDeliverer();
    }

    @GetMapping("/getAllReviews")
    public List<ReviewDetails> getAllReview (){
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

    @GetMapping("/showRestaurantRanking")
    public List<String> showRestaurantRanking() {
        return foodService.showRestaurantRanking();
    }

    // Generated new table getRestaurantMenuItems
    @GetMapping("/getRestaurantMenuItems")
    public List<RestaurantMenuItems> getRestaurantMenuItems(@RequestParam String menuType, @RequestParam int restaurantId) {
        //menuType = "Lunch";
        return foodService.getRestaurantMenuItems(menuType, restaurantId);
    }

    @GetMapping("/getRestaurantOrders")
    public List<String> getRestaurantOrders(@RequestParam int customerId) {
        return foodService.getRestaurantOrders(customerId);
    }

    @GetMapping("/getVipCustomer")
    public List<Customer> getVipCustomer() { return foodService.getVipCustomer(); }

    @GetMapping("/getCheapRestaurant")
    public List<String> getCheapRestaurant() { return foodService.getCheapRestaurant(); }

    @PostMapping("/insertCustomer")
    public Integer insertCustomer(@RequestBody Customer customer){
        return foodService.insertCustomer(customer);
    }

    @PostMapping("/updateCustomerAddress")
    public Integer updateCustomer(@RequestBody Customer customer, @RequestParam String address){
        return foodService.updateCustomerAddress(customer, address);
    }

    @PostMapping("/insertRestaurant")
    public Integer insertRestaurant(@RequestBody Restaurant restaurant){
        return foodService.insertRestaurant(restaurant);
    }

    @PostMapping("/insertReview")
    public Integer insertReview(@RequestBody Review review){
        return foodService.insertReview(review);
    }

    @PostMapping("/insertDeliverer")
    public Integer insertDeliverer(@RequestBody Deliverer deliverer){
        return foodService.insertDeliverer(deliverer);
    }

    @PostMapping("/insertPayment")
    public Integer insertPayment(@RequestBody Payment payment){
        return foodService.insertPayment(payment);
    }

    @DeleteMapping("/deleteOrderDetails")
    public void deleteOrderDetails(@RequestBody Order order, @RequestParam Integer id) {
        foodService.deleteOrderDetails(order, id);
    }

    @PostMapping("/insertOrder")
    public Integer insertOrder(@RequestBody TotalOrder totalOrder) {
        return foodService.insertOrder(totalOrder);
    }

    @GetMapping("/getAllFoodFromARestaurant")
    public List<RestaurantAllFood> getAllFoodFromARestaurant(@RequestParam Integer restaurantId) {
        return foodService.getAllFoodFromARestaurant(restaurantId);
    }

    @GetMapping("/getHistoryOrder")
    public List<OrderHistory> getHistoryOrder(@RequestParam Integer customerId){
        return foodService.getHistoryOrder(customerId);
    }

    @GetMapping("/deleteOrder")
    public Integer deleteOrder(@RequestParam Integer orderId) {
        return foodService.deleteOrder(orderId);
    }

}


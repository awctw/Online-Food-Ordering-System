package com.cs304.group25.service;

import com.cs304.group25.database.DataHandler;
import com.cs304.group25.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@Service
public class FoodService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DataHandler dataHandler;

    public List<Restaurant> getAllRestaurant() {
        return dataHandler.getAllRestaurants();
    }

    public List<Customer> getAllCustomer() {
        return dataHandler.getAllCustomers();
    }

    public List<Deliverer> getAllDeliverer() {
        return dataHandler.getAllDeliverers();
    }

    public List<Review> getAllReviews() {
        return dataHandler.getAllReviews();
    }

    public List<Payment> getAllPayments() {
        return dataHandler.getAllPayments();
    }

    public List<Review> getMatchingReview(Integer id) {
        return dataHandler.getMatchingRestaurantReviews(id);
    }

    public Integer insertCustomer(Customer customer) {
        logger.info("insert new customer", customer);
        int count = dataHandler.insertCustomer(customer);
        if (count != 1) {
           logger.error("fail to insert new customer");
        }
        return count;
    }


    public Integer updateCustomerAddress(Customer customer, String address) {
        logger.info("update customer address" + customer);
        return dataHandler.updateCustomerAddress(customer, address);
    }

    public Integer insertPayment(Payment payment) {
        return dataHandler.insertPayment(payment);
    }

    public List<Restaurant.RestaurantCol> filterByCategory(String category) {
        return dataHandler.filterByCategory(category);
    }

    public List<Restaurant> filterByRating(int rating) {
        return dataHandler.filterByRating(rating);
    }

    public List<RestaurantMenuItems> getRestaurantMenuItems(String menuType) {
        return dataHandler.getRestaurantMenuItems(menuType);
    }

    public void deleteOrderDetails(Order order, Integer id){
         dataHandler.deleteOrderDetails(order, id);
    }
}

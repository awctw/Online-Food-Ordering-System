package com.cs304.group25.service;

import com.cs304.group25.database.DataHandler;
import com.cs304.group25.model.Customer;
import com.cs304.group25.model.Deliverer;
import com.cs304.group25.model.Restaurant;
import com.cs304.group25.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    public List<Review> getMatchingReview(int id) {
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

    public Integer updateCustomer(Customer customer) {
        logger.info("update customer" + customer);
        return dataHandler.updateCustomer(customer);
    }
}

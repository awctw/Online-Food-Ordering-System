package com.cs304.group25.service;

import com.cs304.group25.database.DataHandler;
import com.cs304.group25.model.Customer;
import com.cs304.group25.model.Deliverer;
import com.cs304.group25.model.Restaurant;
import com.cs304.group25.model.Review;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

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

    public void updateAddress(Customer uCustomer, String uAddress){
        String old = uCustomer.getAddress();
        int id = uCustomer.getCustomerId();
        dataHandler.updateAddress(old, uAddress, id);
    }

}

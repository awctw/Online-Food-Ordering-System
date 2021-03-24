package com.cs304.group25.service;

import com.cs304.group25.database.DataHandler;
import com.cs304.group25.model.Restaurant;
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
}

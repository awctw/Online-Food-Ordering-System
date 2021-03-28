package com.cs304.group25.model;

public class RestaurantMenuItems {
    private Double price;
    private String description;
    private String foodName;
    private Integer restaurantId;
    private String name;


    public RestaurantMenuItems(Integer restaurantId, String name, String foodName, Double price, String description) {
        this.price = price;
        this.description = description;
        this.foodName = foodName;
        this.restaurantId = restaurantId;
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RestaurantMenuItems{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", foodName='" + foodName + '\'' +
                ", restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.cs304.group25.model;

public class RestaurantMenuItems {
    private Double price;
    private String description;
    private String foodName;
    private Integer foodtId;
    private String name;


    public RestaurantMenuItems(Integer foodId, String name, String foodName, Double price, String description) {
        this.price = price;
        this.description = description;
        this.foodName = foodName;
        this.foodtId = foodId;
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

    public Integer getFoodtId() {
        return foodtId;
    }

    public void setFoodtId(Integer foodtId) {
        this.foodtId = foodtId;
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
                ", name='" + name + '\'' +
                '}';
    }
}

package com.cs304.group25.model;

public class Restaurant {
    private Integer restaurantId;
    private String name;
    private String category;
    private String address;
    private String postcode;
    private Integer operatingHours;

    public Restaurant(Integer restaurantId, String name, String category, String address, String postcode, Integer operatingHours) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.category = category;
        this.address = address;
        this.postcode = postcode;
        this.operatingHours = operatingHours;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(Integer operatingHours) {
        this.operatingHours = operatingHours;
    }
}
package com.cs304.group25.model;

public class Order {
    private Integer orderId;
    private Integer restaurantId;
    private Integer customerId;
    private String notes;
    private String status;
    private Double totalPrice;

    public Order(Integer orderId, Integer restaurantId, Integer customerId, String notes, String status, Double totalPrice) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.notes = notes;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


}

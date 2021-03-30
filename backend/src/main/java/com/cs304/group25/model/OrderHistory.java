package com.cs304.group25.model;

public class OrderHistory {
    private Integer orderId;
    private Integer restaurantId;
    private Integer customerId;
    private String notes;
    private String status;
    private Double totalPrice;
    private String restaurantName;
    private Integer delivererId;
    private String delivererName;
    private String type;

    public OrderHistory(Integer orderId, Integer restaurantId, Integer customerId, String notes, String status, Double totalPrice, String restaurantName, Integer delivererId, String delivererName) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.notes = notes;
        this.status = status;
        this.totalPrice = totalPrice;
        this.restaurantName = restaurantName;
        this.delivererId = delivererId;
        this.delivererName = delivererName;
    }

    public OrderHistory(Integer orderId, Integer restaurantId, Integer customerId, String notes, String status, Double totalPrice, String restaurantName) {
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
        this.notes = notes;
        this.status = status;
        this.totalPrice = totalPrice;
        this.restaurantName = restaurantName;
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

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Integer getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
    }

    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderId=" + orderId +
                ", restaurantId=" + restaurantId +
                ", customerId=" + customerId +
                ", notes='" + notes + '\'' +
                ", status='" + status + '\'' +
                ", totalPrice=" + totalPrice +
                ", restaurantName='" + restaurantName + '\'' +
                ", delivererId=" + delivererId +
                ", delivererName='" + delivererName + '\'' +
                '}';
    }
}

package com.cs304.group25.model;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderId;
    private Integer foodId;
    private Integer quantity;

    public OrderDetail(Integer orderDetailId, Integer orderId, Integer foodId, Integer quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailId=" + orderDetailId +
                ", orderId=" + orderId +
                ", foodId=" + foodId +
                ", quantity=" + quantity +
                '}';
    }
}

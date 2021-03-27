package com.cs304.group25.model;

public class OrderDetail {
    private Integer orderDetailId;
    private Integer orderIdDetail;
    private Integer foodId;

    public OrderDetail(Integer orderDetailId, Integer orderIdDetail, Integer foodId) {
        this.orderDetailId = orderDetailId;
        this.orderIdDetail = orderIdDetail;
        this.foodId = foodId;
    }

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getOrderIdDetail() {
        return orderIdDetail;
    }

    public void setOrderIdDetail(Integer orderId) {
        this.orderIdDetail = orderId;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }
}

package com.cs304.group25.model;

public class PickUp {
    private Integer orderId;
    private Integer pickUpId;

    public PickUp(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPickUpId() {
        return pickUpId;
    }

    public void setPickUpId(Integer pickUpId) {
        this.pickUpId = pickUpId;
    }

    @Override
    public String toString() {
        return "PickUp{" +
                "orderId=" + orderId +
                ", pickUpId=" + pickUpId +
                '}';
    }
}

package com.cs304.group25.model;

import java.util.List;

public class TotalOrder {
    private Order order;
    private List<OrderDetail> orderDetailList;
    private boolean isPickUp;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public boolean isPickUp() {
        return isPickUp;
    }

    public void setIsPickUp(boolean pickUp) {
        isPickUp = pickUp;
    }

    @Override
    public String toString() {
        return "TotalOrder{" +
                "order=" + order +
                ", orderDetailList=" + orderDetailList +
                ", isPickUp=" + isPickUp +
                '}';
    }
}

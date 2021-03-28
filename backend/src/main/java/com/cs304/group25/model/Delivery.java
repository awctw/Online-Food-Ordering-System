package com.cs304.group25.model;

import java.util.Date;

public class Delivery {
    private Integer orderId;
    private Integer deliveryId;
    private Integer delivererId;
    private Date eta;

    public Delivery(Integer orderId, Integer delivererId, Date eta) {
        this.orderId = orderId;
        this.delivererId = delivererId;
        this.eta = eta;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Integer deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Date getEta() {
        return eta;
    }

    public void setEta(Date eta) {
        this.eta = eta;
    }

    public Integer getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "orderId=" + orderId +
                ", deliveryId=" + deliveryId +
                ", delivererId=" + delivererId +
                ", eta=" + eta +
                '}';
    }
}

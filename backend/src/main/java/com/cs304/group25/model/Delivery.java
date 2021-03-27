package com.cs304.group25.model;

import java.util.Date;

public class Delivery {
    private Integer orderId;
    private Integer deliveryId;
    private Date eta;

    public Delivery(Integer deliveryId, Date eta) {
        this.deliveryId = deliveryId;
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

    @Override
    public String toString() {
        return "Delivery{" +
                "orderId=" + orderId +
                ", deliveryId=" + deliveryId +
                ", eta=" + eta +
                '}';
    }
}

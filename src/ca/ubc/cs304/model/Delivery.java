package ca.ubc.cs304.model;

import java.sql.Date;

public class Delivery {
    private Integer orderId;
    private Integer deliveryId;
    private Date eta;

    public Delivery(Integer orderId, Integer deliveryId, Date eta) {
        this.orderId = orderId;
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
}

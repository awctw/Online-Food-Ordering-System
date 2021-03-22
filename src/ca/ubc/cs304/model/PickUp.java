package ca.ubc.cs304.model;

public class PickUp {
    private Integer orderId;
    private Integer pickUpId;

    public PickUp(Integer orderId, Integer pickUpId) {
        this.orderId = orderId;
        this.pickUpId = pickUpId;
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
}

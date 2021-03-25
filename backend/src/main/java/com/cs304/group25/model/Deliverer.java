package com.cs304.group25.model;

public class Deliverer {
    private Integer delivererId;
    private String licenseNum;
    private String carPlate;
    private Integer phoneNumber;
    private String name;

    public Deliverer(Integer delivererId, Integer reviewId, String licenseNum, String carPlate, Integer phoneNumber, String name) {
        this.delivererId = delivererId;
        ReviewId = reviewId;
        this.licenseNum = licenseNum;
        this.carPlate = carPlate;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Integer getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
    }

    public Integer getReviewId() {
        return ReviewId;
    }

    public void setReviewId(Integer reviewId) {
        ReviewId = reviewId;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

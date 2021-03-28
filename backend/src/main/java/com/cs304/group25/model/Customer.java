package com.cs304.group25.model;

public class Customer {

    private Integer customerId;
    private String address;
    private String name;
    private String phoneNumber;
    private String email;
    private String postCode;

    public Customer(Integer customerId, String address, String name, String phoneNumber, String email, String postCode) {
        this.customerId = customerId;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.postCode = postCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}

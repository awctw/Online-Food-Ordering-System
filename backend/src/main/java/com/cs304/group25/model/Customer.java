package com.cs304.group25.model;

import lombok.Data;

@Data
public class Customer {

    private Integer customerId;
    private String address;
    private String name;
    private Integer phoneNumber;
    private String email;
    private String postcode;

    public Customer(Integer customerId, String address, String name, Integer phoneNumber, String email, String postcode) {
        this.customerId = customerId;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.postcode = postcode;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPostcode() {
        return postcode;
    }
}

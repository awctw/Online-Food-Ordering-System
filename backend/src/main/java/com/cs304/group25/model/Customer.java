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

}

package ca.ubc.cs304.model;

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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}

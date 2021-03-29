package com.cs304.group25.model;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Restaurant {
    private Integer restaurantId;
    private String name;
    private String category;
    private String address;
    private String postCode;
    private String operatingHours;

    public static class RestaurantCol {
        private String name;
        private String address;
        private String category;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public RestaurantCol(String name, String address, String category) {
            this.address = address;
            this.name = name;
            this.category = category;
        }


    }

    public Restaurant(Integer restaurantId, String name, String category, String address, String postCode, String operatingHours) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.category = category;
        this.address = address;
        this.postCode = postCode;
        this.operatingHours = operatingHours;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", operatingHours='" + operatingHours + '\'' +
                '}';
    }
}




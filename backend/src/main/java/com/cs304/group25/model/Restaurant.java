package com.cs304.group25.model;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Restaurant {
    private Integer restaurantId;
    private String name;
    private String category;
    private String address;
    private String postCode;
    private Integer operatingHours;

    public static class RestaurantCol {
        private Integer restaurantId;
        private String name;
        private Integer operatingHours;

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

        public Integer getOperatingHours() {
            return operatingHours;
        }

        public void setOperatingHours(Integer operatingHours) {
            this.operatingHours = operatingHours;
        }

        public RestaurantCol(Integer restaurantId, String name, Integer operatingHours) {
            this.restaurantId = restaurantId;
            this.name = name;
            this.operatingHours = operatingHours;
        }


    }

    public Restaurant(Integer restaurantId, String name, String category, String address, String postCode, Integer operatingHours) {
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

    public Integer getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(Integer operatingHours) {
        this.operatingHours = operatingHours;
    }
}




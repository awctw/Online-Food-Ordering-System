package com.cs304.group25.model;

public class Menu {
    private Integer menuId;
    private Integer restaurantId;
    private String type;

    public Menu(Integer menuId, Integer restaurantId, String type) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.type = type;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", restaurantId=" + restaurantId +
                ", type='" + type + '\'' +
                '}';
    }
}

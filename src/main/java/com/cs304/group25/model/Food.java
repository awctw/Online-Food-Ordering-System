package com.cs304.group25.model;

public class Food {
    private Integer foodId;
    private Integer menuId;
    private Double price;
    private String description;

    public Food(Integer foodItemId, Integer menuId, Double price, String description) {
        this.foodId = foodItemId;
        this.menuId = menuId;
        this.price = price;
        this.description = description;
    }

    public Integer getFoodItemId() {
        return foodId;
    }

    public void setFoodItemId(Integer foodItemId) {
        this.foodId = foodItemId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

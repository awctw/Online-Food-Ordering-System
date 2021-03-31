package com.cs304.group25.model;

public class Review {
    private Integer reviewId;
    private Integer customerId;
    private Integer restaurantId;
    private Integer delivererId;
    private String comment;
    private Integer rating;

    public Review(Integer reviewId, Integer customerId, Integer restaurantId, Integer delivererId, String comment, Integer rating) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.comment = comment;
        this.rating = rating;
        this.restaurantId = restaurantId;
        this.delivererId = delivererId;
    }

    public static class ReviewSingle{
        private Integer reviewId;

        public ReviewSingle(Integer reviewId) {
            this.reviewId = reviewId;
        }

        public Integer getReviewId() {
            return reviewId;
        }

        public void setReviewId(Integer reviewId) {
            this.reviewId = reviewId;
        }

    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getDelivererId() {
        return delivererId;
    }

    public void setDelivererId(Integer delivererId) {
        this.delivererId = delivererId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", customerId=" + customerId +
                ", restaurantId=" + restaurantId +
                ", delivererId=" + delivererId +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                '}';
    }
}

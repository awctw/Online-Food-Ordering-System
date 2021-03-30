package com.cs304.group25.model;

public class ReviewDetails {
        private Integer reviewId;
        private String customerName;
        private String restaurantName;
        private String delivererName;
        private String comment;
        private Integer rating;

        public ReviewDetails(Integer reviewId, String customerName, String restaurantName, String delivererName, String comment, Integer rating) {
            this.reviewId = reviewId;
            this.customerName = customerName;
            this.restaurantName = restaurantName;
            this.delivererName = delivererName;
            this.comment = comment;
            this.rating = rating;
        }

        public Integer getReviewId() {
            return reviewId;
        }

        public void setReviewId(Integer reviewId) {
            this.reviewId = reviewId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getRestaurantName() {
            return restaurantName;
        }

        public void setRestaurantName(String restaurantName) {
            this.restaurantName = restaurantName;
        }

        public String getDelivererName() {
            return delivererName;
        }

        public void setDelivererName(String delivererName) {
            this.delivererName = delivererName;
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


}

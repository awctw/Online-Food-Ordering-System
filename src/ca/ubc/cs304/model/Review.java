package ca.ubc.cs304.model;

public class Review {
    private Integer reviewId;
    private Integer customerId;
    private String comment;
    private Integer rating;

    public Review(Integer reviewId, Integer customerId, String comment, Integer rating) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.comment = comment;
        this.rating = rating;
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
}

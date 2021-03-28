package com.cs304.group25.model;

public class Payment {
    private String cardId;
    private String customerId;
    private String cardType;
    private Integer expireDate;
    private String cardHolderName;
    private String securityCode;

    public Payment(String cardId, String customerId, String cardType, Integer expireDate, String cardHolderName, String securityCode) {
        this.cardId = cardId;
        this.customerId = customerId;
        this.cardType = cardType;
        this.expireDate = expireDate;
        this.cardHolderName = cardHolderName;
        this.securityCode = securityCode;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Integer expireDate) {
        this.expireDate = expireDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "cardId='" + cardId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", cardType='" + cardType + '\'' +
                ", expireDate=" + expireDate +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}

package org.camunda.training.c8.dto;

import java.math.BigDecimal;

public class ChargeCreditCardData {

    private BigDecimal remainingAmount, orderTotal;
    private String cardNumber, cvc, expiryDate, customerId, orderId;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public String getCvc() {
        return cvc;
    }
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
    @Override
    public String toString() {
        return "ChargeCreditCardData [remainingAmount=" + remainingAmount + ", orderTotal=" + orderTotal
                + ", cardNumber=" + cardNumber + ", cvc=" + cvc + ", expiryDate=" + expiryDate + ", customerId="
                + customerId + ", orderId=" + orderId + "]";
    }
}

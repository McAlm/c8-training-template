package org.camunda.training.c8.dto;

import java.math.BigDecimal;

public class ChargeCreditCardData {

    private String cardNumber, cvc, expiryDate;
    private BigDecimal remainingAmount;

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

    @Override
    public String toString() {
        return "ChargeCreditCardData [cardNumber=" + cardNumber + ", cvc=" + cvc + ", expiryDate=" + expiryDate
                + ", remainingAmount=" + remainingAmount.toPlainString() + "]";
    }
    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    
}

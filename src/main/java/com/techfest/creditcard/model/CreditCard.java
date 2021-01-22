package com.techfest.creditcard.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue
    private int cardId;

    @NotBlank(message = "{creditcard.cardholder.notblank}")
    @Size(min = 3, max = 60, message = "{creditcard.cardholder.size}")
    @Pattern(regexp = "[A-Za-z]+", message = "{creditcard.cardholder.pattrn}")
    private String cardHolder;

    @NotBlank(message = "{creditcard.cardnumber.notblank}")
    @Digits(message = "{creditcard.cardnumber.digit}", integer = 19, fraction = 0)
    private String cardNumber;
    private Double cardBalance = 0.0;

    @NotNull(message = "{creditcard.cardlimit.notnull}")
    @PositiveOrZero(message = "{creditcard.cardlimit.positive}")
    private Double cardLimit;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Double getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Double cardLimit) {
        this.cardLimit = cardLimit;
    }

    public CreditCard(String cardHolder, String cardNumber, Double cardLimit) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.cardLimit = cardLimit;
    }

    public CreditCard() {
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardHolder='" + cardHolder + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardLimit=" + cardLimit +
                '}';
    }
}

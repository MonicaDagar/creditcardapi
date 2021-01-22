package com.techfest.creditcard.Exception;

public class InvalidCreditCardNumber extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidCreditCardNumber(String message) {
        super(message);
    }
}

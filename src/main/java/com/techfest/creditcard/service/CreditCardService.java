package com.techfest.creditcard.service;

import com.techfest.creditcard.model.CreditCard;

import java.util.List;

/**
 * Interface for Credit card service
 */
public interface CreditCardService {
    CreditCard addCreditCard(CreditCard creditCard);

    List<CreditCard> getAllCreditCards();


}

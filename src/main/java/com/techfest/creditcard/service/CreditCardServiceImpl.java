package com.techfest.creditcard.service;

import com.techfest.creditcard.Exception.InvalidCreditCardNumber;
import com.techfest.creditcard.model.CreditCard;
import com.techfest.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    /**
     * @param creditCard Add a credit card after validating the card number
     * @return
     */
    @Override
    @Transactional
    public CreditCard addCreditCard(CreditCard creditCard) {
        if (!validateCreditCardNumber(creditCard.getCardNumber()))
            throw new InvalidCreditCardNumber("Credit card number " + creditCard.getCardNumber() + "  is not valid");
        return creditCardRepository.save(creditCard);
    }

    /**
     * return all the credit card in the system
     *
     * @return
     */
    @Override
    public List<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    /**
     * @param cardNumber Validation method to validate the credit card number using Luhn 10 algorithm
     * @return
     */
    private boolean validateCreditCardNumber(String cardNumber) {
        int digits = cardNumber.length();

        int sum = 0;
        boolean isSecondDigit = false;
        for (int index = digits - 1; index >= 0; index--) {
            int d = cardNumber.charAt(index) - '0';
            if (isSecondDigit)
                d = d * 2;

            // We add two digits to handle cases that make two digits after doubling
            sum += (d > 9) ? (d % 10) + 1 : d;
            isSecondDigit = !isSecondDigit;
        }
        return (sum % 10 == 0);
    }
}

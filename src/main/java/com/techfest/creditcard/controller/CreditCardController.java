package com.techfest.creditcard.controller;

import com.techfest.creditcard.model.CreditCard;
import com.techfest.creditcard.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/cardproviderapi/v1", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardService;

    /**
     * @param creditCard
     * handler method to add a credit card
     * @return
     */
    @PostMapping("/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@RequestBody @Valid CreditCard creditCard) {
        return creditCardService.addCreditCard(creditCard);
    }

    /**
     * handler method to get all the credit cards
     *
     * @return
     */
    @GetMapping("/creditcards")
    public List<CreditCard> getAllCreditCards() {
        return creditCardService.getAllCreditCards();
    }

}

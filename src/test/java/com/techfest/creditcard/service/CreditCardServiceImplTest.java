package com.techfest.creditcard.service;

import com.techfest.creditcard.Exception.InvalidCreditCardNumber;
import com.techfest.creditcard.model.CreditCard;
import com.techfest.creditcard.repository.CreditCardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
public class CreditCardServiceImplTest {
    @InjectMocks
    private CreditCardServiceImpl creditCardService;
    @Mock
    private CreditCardRepository creditCardRepository;

    private MockMvc mockMvc;

    @Test
    @DisplayName("Invalid Credit card number test case")
    public void addCreditCardTest(){
        CreditCard creditCard = new CreditCard("John","17345", 12345.0);
        assertThrows(InvalidCreditCardNumber.class, ()->creditCardService.addCreditCard(creditCard));
    }

    @Test
    @DisplayName("Valid get all Credit cards test case")
    public void getAllCreditCardsTest(){
        CreditCard creditCard = new CreditCard("John","173450", 12345.0);
        List<CreditCard> creditCardList = new ArrayList<>();
        creditCardList.add(creditCard);

        Mockito.when(creditCardRepository.findAll()).thenReturn(creditCardList);
        assertEquals(creditCardList,creditCardService.getAllCreditCards());
    }

    @Test
    @DisplayName("Valid data credit card test case")
    public void addCreditCardTest2(){
        CreditCard mockCreditCard = new CreditCard("John", "173450", 12345.0);

        // creditCardRepository.save to respond back with mockCreditCard
        Mockito.when(creditCardRepository.save(Mockito.any(CreditCard.class))).thenReturn(mockCreditCard);
        assertEquals(mockCreditCard,creditCardService.addCreditCard(mockCreditCard));
    }



}
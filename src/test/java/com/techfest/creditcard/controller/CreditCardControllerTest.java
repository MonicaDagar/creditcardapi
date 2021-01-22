package com.techfest.creditcard.controller;

import com.techfest.creditcard.model.CreditCard;
import com.techfest.creditcard.service.CreditCardServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditCardController.class)
class CreditCardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardServiceImpl creditCardService;

    @Test
    @DisplayName("Valid get credit card test case")
    public void getAllCreditCardTest() throws Exception {

        //mock the data return by the credit card service
        CreditCard creditCard = new CreditCard("John","173450",12345.0);
        List<CreditCard> creditCardList = Arrays.asList(creditCard);
        Mockito.when(creditCardService.getAllCreditCards()).thenReturn(creditCardList);

        mockMvc.perform(MockMvcRequestBuilders.
                get("/cardproviderapi/v1/creditcards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cardHolder", Matchers.is(creditCard.getCardHolder())));
    }

    @Test
    @DisplayName("Valid status and data check for add credit card test")
    public void addCreditCardTest() throws Exception {
        CreditCard mockCreditCard = new CreditCard("John", "173450", 12345.0);

        // creditCardService.addCreditCard to respond back with mockCreditCard
        Mockito.when(creditCardService.addCreditCard(Mockito.any(CreditCard.class))).thenReturn(mockCreditCard);
        String json = "{\n" +
                "    \"cardHolder\":\"John\",\n" +
                "    \"cardNumber\":\"173450\",\n" +
                "    \"cardLimit\":\"12345.0\"\n" +
                "}";
        // Send credit card as body to /cardproviderapi/creditcard
        mockMvc.perform(MockMvcRequestBuilders.
                post("/cardproviderapi/v1/creditcard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cardHolder", Matchers.is("John")))
                .andExpect(jsonPath("$.cardNumber", Matchers.is("173450")))
                .andExpect(jsonPath("$.cardLimit", Matchers.is(12345.0)));
    }

    @Test
    @DisplayName("InValid data check for add credit card test")
    public void addCreditCardTest2() throws Exception {

        String json = "{\n" +
                "    \"cardHolder\":\"\",\n" +
                "    \"cardNumber\":\"173450\",\n" +
                "    \"cardLimit\":\"12345.0\"\n" +
                "}";
        // Send credit card as body to /cardproviderapi/creditcard
        mockMvc.perform(MockMvcRequestBuilders.
                post("/cardproviderapi/v1/creditcard")
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CreditCardRepositoryTest {

    @Autowired
    CreditCardRepository underTest;
    User owner;

    @Test
    void itShouldFindCreditCardByCardNumber() {
        CreditCard creditCard = new CreditCard(
                1L, "Dollar Card", "4441 1114 4423 3221", CreditCard.Status.ACTIVE, BigDecimal.valueOf(1000), owner, false, CreditCard.Request.NOT_REQUESTED
        );
    }

}
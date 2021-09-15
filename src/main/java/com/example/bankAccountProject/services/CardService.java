package com.example.bankAccountProject.services;

import com.example.bankAccountProject.dto.CreditCardNameDTO;
import com.example.bankAccountProject.model.Account;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    public void addCard(Account account, CreditCardNameDTO creditCardNameDTO) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(generateUniqueCardNumber());
        creditCard.setCardName(creditCardNameDTO.getCardName());
        creditCard.setOwner(account);
        creditCardRepository.save(creditCard);
    }

    public void changeStatus(CreditCard creditCard) {
        if(creditCard.getStatus() == CreditCard.Status.ACTIVE) {
            creditCard.setStatus(CreditCard.Status.BLOCKED);
        } else {
            creditCard.setStatus(CreditCard.Status.ACTIVE);
        }
        creditCardRepository.save(creditCard);
    }

    public List<CreditCard> cardInfo() {
        return creditCardRepository.findAll();
    }

    private String generateCardNumber () {
        return (Math.random() * Math.pow(10, 3)) + "";
    }

    public Optional<CreditCard> show(Long id) {
        return creditCardRepository.findById(id);
    }

    private String generateUniqueCardNumber () {
        String uniqueCard;
        do {
             uniqueCard = generateCardNumber();
        } while(!checkCardUniqness(uniqueCard));
        return uniqueCard;
    }

    private boolean checkCardUniqness (String generatedCardNumber) {
        return creditCardRepository.findCreditCardByCardNumberEquals(
                generatedCardNumber).isEmpty();
    }

}

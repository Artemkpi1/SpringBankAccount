package com.example.bankAccountProject.services;

import com.example.bankAccountProject.dto.CreditCardDTO;
import com.example.bankAccountProject.model.CardRequest;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CardService {
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private CardNumberGenerator cardNumberGenerator;

    public void addCard(User user, CreditCardDTO creditCardDTO) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber(generateUniqueCardNumber());
        creditCard.setCardName(creditCardDTO.getCardName());
        creditCard.setOwner(user);
        creditCardRepository.save(creditCard);
    }

    public void addMoney(CreditCardDTO creditCardDTO) {
        Optional<CreditCard> moneyAdder = creditCardRepository.findCreditCardByCardNumber(creditCardDTO.getCardNumber());

        if (moneyAdder.isPresent() && moneyAdder.get().getStatus() == CreditCard.Status.ACTIVE) {
            CreditCard creditCard = moneyAdder.get();
            creditCard.setBalance(creditCard.getBalance().add(creditCardDTO.getBalance()));
            creditCardRepository.save(creditCard);
        }

    }

    public void blockCard(CreditCard creditCard) {
        creditCard.setStatus(CreditCard.Status.BLOCKED);
        creditCardRepository.save(creditCard);
    }

    public List<CreditCard> cardInfo(User currentUser) {
        return creditCardRepository.findCreditCardByOwner(currentUser);
    }

    private String generateCardNumber () {
        return String.valueOf(new Random().nextDouble()).split("\\.")[1];
    }
    public Optional<CreditCard> show(Long id) {
        return creditCardRepository.findById(id);
    }

    private String generateUniqueCardNumber () {
        String uniqueCard;
        do {
             uniqueCard = cardNumberGenerator.generate("",16);
        } while(!checkCardUniqness(uniqueCard));
        return uniqueCard;
    }

    private boolean checkCardUniqness (String generatedCardNumber) {
        return creditCardRepository.findCreditCardByCardNumber(
                generatedCardNumber).isEmpty();
    }

}

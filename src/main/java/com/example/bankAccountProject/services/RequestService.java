package com.example.bankAccountProject.services;

import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.DAORepository.RequestRepository;
import com.example.bankAccountProject.model.CardRequest;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;


    public List<CardRequest> showAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<CardRequest> show(Long id) {
        return requestRepository.findById(id);
    }


    public void requestToUnblock(CreditCard creditCard) {
        if (creditCard.getRequest() == CreditCard.Request.REQUESTED) {
            return;
        }
        creditCard.setRequest(CreditCard.Request.REQUESTED);
        CardRequest cardRequest = new CardRequest();
        cardRequest.setCreditCard(creditCard);
        requestRepository.save(cardRequest);
    }

    public void unblockCard(CardRequest cardRequest) {
        CreditCard creditCard = cardRequest.getCreditCard();
        creditCard.setStatus(CreditCard.Status.ACTIVE);
        creditCardRepository.save(creditCard);
        requestRepository.delete(cardRequest);
    }

}

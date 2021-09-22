package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {



    Optional<CreditCard> findCreditCardByCardNumber(String cardNumber);

    Optional<CreditCard> findCreditCardByOwnerAndCardNumberAndStatus(User user, String cardNumber, CreditCard.Status status);

    Optional<CreditCard> findCreditCardByCardNumberAndStatus(String receiverCard, CreditCard.Status status);

    List<CreditCard> findCreditCardByOwner(User user);
}

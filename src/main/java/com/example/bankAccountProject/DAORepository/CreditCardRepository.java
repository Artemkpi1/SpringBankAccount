package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findCreditCardByCardNumberEquals(String cardNumber);
    Optional<CreditCard> findCreditCardByIdOrderByIdDesc(Long id);
}

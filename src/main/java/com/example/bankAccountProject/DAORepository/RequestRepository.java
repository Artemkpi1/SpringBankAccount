package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.CardRequest;
import com.example.bankAccountProject.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<CardRequest, Long> {

}

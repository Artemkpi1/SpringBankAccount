package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

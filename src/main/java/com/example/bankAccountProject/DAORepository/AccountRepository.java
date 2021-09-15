package com.example.bankAccountProject.DAORepository;

import com.example.bankAccountProject.model.Account;
import com.example.bankAccountProject.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}

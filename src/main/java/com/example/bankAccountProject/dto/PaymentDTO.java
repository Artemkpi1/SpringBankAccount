package com.example.bankAccountProject.dto;


import com.example.bankAccountProject.model.CreditCard;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

public class PaymentDTO {

    private String receiver;
    private String sender;
    private BigDecimal sum;
    private String creditCard;
}

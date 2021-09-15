package com.example.bankAccountProject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String cardNumber;
    private Status status = Status.ACTIVE;
    private BigDecimal balance = new BigDecimal(1000);
    @ManyToOne
    private Account owner;


    public enum Status {
        ACTIVE,
        BLOCKED
    }
}

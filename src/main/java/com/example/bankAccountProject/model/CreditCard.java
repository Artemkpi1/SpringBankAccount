package com.example.bankAccountProject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@EqualsAndHashCode
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

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
    private BigDecimal balance = new BigDecimal(1000);
    @ManyToOne
    private User owner;

    private boolean beenRequested = false;
    private Request request;

    public enum Request {
        REQUESTED,
        NOT_REQUESTED
    }


    public enum Status {
        ACTIVE,
        BLOCKED
    }
}

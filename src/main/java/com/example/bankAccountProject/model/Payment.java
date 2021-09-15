package com.example.bankAccountProject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private CreditCard sender;
    @ManyToOne
    private CreditCard receiver;
    private BigDecimal sum;
    private Date date;

    private State state;
    public enum State {
        PREPARED,
        SENT
    }

}

package com.example.bankAccountProject.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@EqualsAndHashCode
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
    @ManyToOne
    private User owner;

    @Enumerated(EnumType.STRING)
    private State state;
    public enum State {
        PREPARED,
        SENT
    }

}

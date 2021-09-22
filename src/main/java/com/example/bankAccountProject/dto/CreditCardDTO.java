package com.example.bankAccountProject.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@Getter
@Setter
public class CreditCardDTO {

    @NotNull
    private String CardName;
    private String CardNumber;
    @DecimalMin("0.01")
    private BigDecimal balance;

}

package com.example.bankAccountProject.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class CreditCardNameDTO {

    @NotNull
    private String CardName;

}

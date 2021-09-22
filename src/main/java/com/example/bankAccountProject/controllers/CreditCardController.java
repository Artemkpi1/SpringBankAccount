package com.example.bankAccountProject.controllers;

import com.example.bankAccountProject.DAORepository.UserRepository;
import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.CreditCardDTO;
import com.example.bankAccountProject.services.CardService;
import com.example.bankAccountProject.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CreditCardController {

    private final CardService cardService;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;


    @PatchMapping("/changeCardStatus/{id}")
    public String changeCardStatus(@PathVariable("id") Long id) {
        cardService.blockCard(cardService.show(id).get());
        return "redirect:/userPage";
    }

    @GetMapping("/addMoney")
    public String getMoney(@ModelAttribute("creditCardDTOAtt") CreditCardDTO creditCardDTO) {
        return "/addMoney";
    }


    @PatchMapping("/addMoney")
    public String patchMoney(CreditCardDTO creditCardDTO) {
        cardService.addMoney(creditCardDTO);
        return "redirect:/userPage";
    }



}

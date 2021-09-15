package com.example.bankAccountProject.controllers;

import com.example.bankAccountProject.DAORepository.AccountRepository;
import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.CreditCardNameDTO;
import com.example.bankAccountProject.dto.PaymentDTO;
import com.example.bankAccountProject.model.Account;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Payment;
import com.example.bankAccountProject.services.CardService;
import com.example.bankAccountProject.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final CardService cardService;
    private final AccountRepository accountRepository;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;


    @GetMapping("/userPage")
    public String cardInfo(Model model) {
        model.addAttribute("cardInfo", cardService.cardInfo());
        return "userPage";
    }

    @GetMapping("/addCardName")
    public String addCardName(@ModelAttribute("cardNameAtt") CreditCardNameDTO creditCardNameDTO) {
        return "addCardName";
    }


    @PostMapping("/userPage")
    public String newCard(@ModelAttribute("cardNameAtt") CreditCardNameDTO creditCardNameDTO) {

        Account account = new Account();
        System.out.println(creditCardNameDTO.getCardName());
        accountRepository.save(account);
        cardService.addCard(account, creditCardNameDTO);
        return "redirect:/userPage";
    }


    @PatchMapping("/changeStatus/{id}")
    public String changeStatus(@PathVariable("id") Long id) {
        cardService.changeStatus(cardService.show(id).get());
        return "redirect:/userPage";
    }

    @GetMapping("/makePayment")
    public String makePayment(@ModelAttribute("paymentAtt") PaymentDTO paymentDTO) {
        return "makePayment";
    }

    @PostMapping("/postPayment")
    public String postPayment(@ModelAttribute("paymentAtt")PaymentDTO paymentDTO) {
        paymentService.addPayment(paymentDTO);
        return "redirect:/userPage";
    }


    @PatchMapping("/addMoney")
    public String addMoney() {
        return "addMoney";
    }

    @PatchMapping("/withdraw")
    public String withdraw() {
        return "withdraw";
    }

    @DeleteMapping("/deleteCard")
    public String deleteCard() {
        return "deleteCard";
    }

    @PatchMapping("/blockCard")
    public String blockCard() {
        return "blockCard";
    }

}

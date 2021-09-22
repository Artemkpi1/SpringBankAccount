package com.example.bankAccountProject.controllers;

import com.example.bankAccountProject.DAORepository.UserRepository;
import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.CreditCardDTO;
import com.example.bankAccountProject.model.User;
import com.example.bankAccountProject.services.CardService;
import com.example.bankAccountProject.services.PaymentService;
import com.example.bankAccountProject.services.RequestService;
import com.example.bankAccountProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final CardService cardService;
    private final UserRepository userRepository;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final RequestService requestService;

    @GetMapping("/userPage")
    public String cardInfo(Model model,
                           Authentication authentication) {
        User current = (User) authentication.getPrincipal();
        model.addAttribute("cardInfo", cardService.cardInfo(current));
        return "userPage";
    }

    @PostMapping("/userPage")
    public String newCard(@ModelAttribute("cardNameAtt") CreditCardDTO creditCardDTO,
                          Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        cardService.addCard(currentUser, creditCardDTO);
        return "redirect:/userPage";
    }


    @GetMapping("/allUsers")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.showAllUsers());
        return "allUsers";
    }

    @PatchMapping("changeUserState/{id}")
    public String changeUserState(@PathVariable("id") Long id) {
        userService.changeUserState(userService.show(id).get());
        return "redirect:/allUsers";
    }


    @GetMapping("/allRequests")
    public String requestList(Model model) {
        model.addAttribute("allRequests", requestService.showAllRequests());
        return "allRequests";
    }

    @PatchMapping("/unblockCard/{id}")
    public String unblockCard(@PathVariable("id") Long id) {
        requestService.unblockCard(requestService.show(id).get());
        return "redirect:/allRequests";
    }

    @PostMapping("/postRequestCard/{id}")
    public String postRequestCard(@PathVariable("id") Long id) {
        requestService.requestToUnblock(cardService.show(id).get());
        return "redirect:/userPage";
    }

    @GetMapping("/addCardName")
    public String addCardName(@ModelAttribute("cardNameAtt") CreditCardDTO creditCardDTO) {
        return "addCardName";
    }

}

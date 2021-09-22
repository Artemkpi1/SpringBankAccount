package com.example.bankAccountProject.controllers;

import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.PaymentDTO;
import com.example.bankAccountProject.model.User;
import com.example.bankAccountProject.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;


    @GetMapping("/makePayment")
    public String makePayment(@ModelAttribute("paymentAtt") PaymentDTO paymentDTO) {
        return "makePayment";
    }

    @PostMapping("/preparePayment")
    public String preparePayment(@ModelAttribute("paymentAtt")PaymentDTO paymentDTO) {
        paymentService.preparePayment(paymentDTO);
        return "redirect:/userPage";
    }

    @GetMapping("/allPayments")
    public String showAllPayments(Model model) {
        model.addAttribute("payments", paymentService.showAllPayments());
        return "/allPayments";
    }

    @PatchMapping("/executePayment")
    public String addPayment(@RequestParam("senderCardNumber") String senderCardNumber,
                             @RequestParam("receiverCardNumber") String receiverCardNumber,
                             @RequestParam("sum")BigDecimal sum,
                             Authentication authentication,
                             @RequestParam("paymentId") Long id) {

        User current = (User) authentication.getPrincipal();
        paymentService.executePayment(current, senderCardNumber, receiverCardNumber, sum, paymentService.show(id).get());
        return "redirect:/userPage";
    }


}

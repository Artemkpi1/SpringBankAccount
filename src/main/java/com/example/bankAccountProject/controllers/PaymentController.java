package com.example.bankAccountProject.controllers;

import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.PaymentDTO;
import com.example.bankAccountProject.model.Payment;
import com.example.bankAccountProject.model.User;
import com.example.bankAccountProject.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
    public String showAllPayments(Model model,
                                  @RequestParam("sortBy") Optional<String> sortBy,
                                  @RequestParam("direction") Optional<String> direction,
                                  @RequestParam("page") Optional<Integer> pageNo) {
//        String sort = sortBy.orElse("id");
//        String dir = "asc".equalsIgnoreCase(direction.orElse("asc")) ? "asc" : "desc";
//
//        Page<Payment> page = paymentService.getSorting(sort, dir);
        model.addAttribute("payments", paymentService.showAllPayments());

        int currentPage = pageNo.orElse(1);
        String sort = sortBy.orElse("id");
        String dir = "asc".equalsIgnoreCase(direction.orElse("asc")) ? "asc" : "desc";

        Page<Payment> page = paymentService.getPaginated(currentPage, sort, dir);
        List<Payment> payments = page.getContent();

        model.addAttribute("payments", payments);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortField", sort);
        model.addAttribute("direction", dir);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("reverseDirection", dir.equals("asc") ? "desc" : "asc");
        return "/allPayments";
    }

    @PatchMapping("/executePayment")
    public String addPayment(@RequestParam("senderCardNumber") String senderCardNumber,
                             @RequestParam("receiverCardNumber") String receiverCardNumber,
                             @RequestParam("sum")BigDecimal sum,
                             Authentication authentication,
                             @RequestParam("paymentId") Long id) {

        User current = (User) authentication.getPrincipal();
        paymentService.addPayment(current, senderCardNumber, receiverCardNumber, sum, paymentService.show(id).get());
        return "redirect:/allPayments";
    }

//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable ("pageNo") int pageNo,
//                                Model model) {
//        int pageSize = 5;
//        Page<Payment> page = paymentService.findPaginated(pageNo, pageSize);
//        List<Payment> listPayments = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("listPayments", listPayments);
//        return "allPayments";
//    }

}

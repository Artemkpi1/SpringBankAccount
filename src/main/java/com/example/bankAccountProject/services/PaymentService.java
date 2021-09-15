package com.example.bankAccountProject.services;

import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.PaymentDTO;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    public Optional<Payment> show(Long id) {
        return paymentRepository.findById(id);
    }


    public void addPayment(PaymentDTO paymentDTO) {

        Optional<CreditCard> senderCard1 = creditCardRepository.findCreditCardByCardNumberEquals(paymentDTO.getSender());

        System.out.println(paymentDTO.getSender());
        System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        if (senderCard1.isEmpty()) {
            return;
        }
        System.out.println("111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        if (senderCard1.get().getBalance().doubleValue()
                < paymentDTO.getSum().doubleValue()) {
            return;
        }

        Optional<CreditCard> receiverCard1 = creditCardRepository.findCreditCardByCardNumberEquals(paymentDTO.getReceiver());
        System.out.println(receiverCard1.isEmpty());
        System.out.println( receiverCard1.get()
                .getStatus().equals(CreditCard.Status.BLOCKED));
        if (receiverCard1.isEmpty() || receiverCard1.get()
                .getStatus().equals(CreditCard.Status.BLOCKED)) {
            return;
        }

        Payment payment = new Payment();
        payment.setSender(senderCard1.get());
        payment.setState(Payment.State.PREPARED);
        payment.setSum(paymentDTO.getSum());
        payment.setDate(new Date());
        payment.setReceiver(receiverCard1.get());
        paymentRepository.save(payment);

    }

//    senderCard.setBalance(senderCard.getBalance()
//            .subtract(paymentDTO.getSum()));
//            receiverCard.setBalance(receiverCard
//            .getBalance()
//            .add(paymentDTO.getSum()));
}




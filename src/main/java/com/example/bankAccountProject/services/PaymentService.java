package com.example.bankAccountProject.services;

import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.DAORepository.PaymentRepository;
import com.example.bankAccountProject.dto.PaymentDTO;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Payment;
import com.example.bankAccountProject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    public final static int PAGE_SIZE = 10;
    public Optional<Payment> show(Long id) {
        return paymentRepository.findById(id);
    }


    public void preparePayment(PaymentDTO paymentDTO) {

        Optional<CreditCard> senderCard1 = creditCardRepository.findCreditCardByCardNumber(paymentDTO.getSender());

        if (senderCard1.isEmpty() || senderCard1.get()
                .getStatus().equals(CreditCard.Status.BLOCKED)) {
            return;
        }
        if (senderCard1.get().getBalance().doubleValue()
                < paymentDTO.getSum().doubleValue()) {
            return;
        }

        Optional<CreditCard> receiverCard1 = creditCardRepository.findCreditCardByCardNumber(paymentDTO.getReceiver());

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

    public List<Payment> showAllPayments() {
        return paymentRepository.findAll();
    }

    public CreditCard matchesUserWithSenderCard(User user, String senderCard) {
        System.out.println(senderCard);
        Optional<CreditCard> senderCard1 = creditCardRepository.findCreditCardByOwnerAndCardNumberAndStatus(user, senderCard, CreditCard.Status.ACTIVE);

        return senderCard1.orElse(null);
    }


    public CreditCard isReceiverCardPresent(String receiverCard) {
        Optional<CreditCard> receiverCard1 = creditCardRepository.findCreditCardByCardNumberAndStatus(receiverCard, CreditCard.Status.ACTIVE);
        return receiverCard1.orElse(null);
    }

    public void addPayment(User user, String senderCard, String receiverCard, BigDecimal sum, Payment payment) {
        CreditCard senderCard1 = matchesUserWithSenderCard(user, senderCard);
        CreditCard receiverCard1 = isReceiverCardPresent(receiverCard);
        matchesUserWithSenderCard(user, senderCard).setBalance(senderCard1.getBalance().subtract(sum));
        isReceiverCardPresent(receiverCard).setBalance(receiverCard1.getBalance().add(sum));
        payment.setState(Payment.State.SENT);

        creditCardRepository.save(senderCard1);
        creditCardRepository.save(receiverCard1);
    }

    public Page<Payment> getPaginated(int pageNo, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, PAGE_SIZE, sort);
        return paymentRepository.findAll(pageable);
    }

//
//    public Page<Payment> getSorting(String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
//                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//
//        return orderRepository.findAll(pageable);
//    }
}




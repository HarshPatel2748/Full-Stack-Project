package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.CreatePaymentDTO;
import com.marketplace.marketplace_backend.dto.PaymentResponseDTO;
import com.marketplace.marketplace_backend.dto.PaymentVerificationDTO;
import com.marketplace.marketplace_backend.service.PaymentService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    // Create a payment order
    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDTO> createPayment(
            @RequestBody CreatePaymentDTO dto) throws Exception {
        return ResponseEntity.ok(paymentService.createPayment(dto));
    }


    // Verify payment
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(
            @RequestBody PaymentVerificationDTO dto){

        paymentService.verifyPayment(dto);
        return ResponseEntity.ok("Payment verified successfully");
    }
}

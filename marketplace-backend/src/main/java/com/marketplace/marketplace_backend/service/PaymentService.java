package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.CreatePaymentDTO;
import com.marketplace.marketplace_backend.dto.PaymentResponseDTO;
import com.marketplace.marketplace_backend.dto.PaymentVerificationDTO;

public interface PaymentService {

    PaymentResponseDTO createPayment(CreatePaymentDTO dto) throws Exception;

    void verifyPayment(PaymentVerificationDTO dto);
}

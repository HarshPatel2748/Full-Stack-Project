package com.marketplace.marketplace_backend.dto;

import com.marketplace.marketplace_backend.entity.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private String razorpayOrderId;
    private Double amount;
    private String currency;
}

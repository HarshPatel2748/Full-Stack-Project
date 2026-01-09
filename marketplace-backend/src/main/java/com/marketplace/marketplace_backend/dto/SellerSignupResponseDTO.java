package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerSignupResponseDTO {

    private Long selerId;
    private String status;
    private String message;
}

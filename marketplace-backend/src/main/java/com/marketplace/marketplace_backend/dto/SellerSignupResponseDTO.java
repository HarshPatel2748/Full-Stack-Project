package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerSignupResponseDTO {

    private Long sellerId;
    private String shopName;
    private String email;
    private String status;
}

package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerLoginResponseDTO {

    private Long sellerId;
    private String shopName;
    private String status;
}

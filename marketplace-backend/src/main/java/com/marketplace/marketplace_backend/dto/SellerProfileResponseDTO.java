package com.marketplace.marketplace_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerProfileResponseDTO {

    private Long id;
    private String shopName;
    private String ownerName;
    private String email;
    private String phone;
    private String address;
    private String status;
    private LocalDateTime approvedAt;
    private LocalDateTime createdAt;
}

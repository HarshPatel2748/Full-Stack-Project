package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SellerSignupRequestDTO {

    private String shopName;
    private String ownerName;
    private String email;
    private String password;
    private String phone;
    private String address;
}

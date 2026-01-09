package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginResponseDTO {

    private Long userId;
    private String role;
    private String message;
}

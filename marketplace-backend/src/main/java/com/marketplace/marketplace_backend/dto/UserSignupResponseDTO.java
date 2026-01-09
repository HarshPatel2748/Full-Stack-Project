package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupResponseDTO {

    private Long userId;
    private String message;
}

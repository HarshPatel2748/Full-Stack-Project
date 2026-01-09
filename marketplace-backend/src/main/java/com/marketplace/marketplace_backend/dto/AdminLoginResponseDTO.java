package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminLoginResponseDTO {

    private Long adminId;
    private String name;
    private String email;
}

package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignupResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}

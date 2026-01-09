package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSignupRequestDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
}

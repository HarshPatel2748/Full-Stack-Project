package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.UserLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.UserLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.UserSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.UserSignupResponseDTO;
import com.marketplace.marketplace_backend.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;


    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDTO> signup(
            @RequestBody UserSignupRequestDTO dto) {
        return ResponseEntity.ok(userAuthService.signup(dto));
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(
            @RequestBody UserLoginRequestDTO dto) {
        return ResponseEntity.ok(userAuthService.login(dto));
    }
}
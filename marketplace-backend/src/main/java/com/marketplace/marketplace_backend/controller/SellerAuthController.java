package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.SellerLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupResponseDTO;
import com.marketplace.marketplace_backend.service.SellerAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller/auth")
@RequiredArgsConstructor
public class SellerAuthController {

    private final SellerAuthService sellerAuthService;

    // Seller Signup
    @PostMapping("/signup")
    public ResponseEntity<SellerSignupResponseDTO> signup(
            @RequestBody SellerSignupRequestDTO request) {

        return ResponseEntity.ok(sellerAuthService.signup(request));
    }

    // Seller Login
    @PostMapping("/login")
    public ResponseEntity<SellerLoginResponseDTO> login(
            @RequestBody SellerLoginRequestDTO request) {

        return ResponseEntity.ok(sellerAuthService.login(request));
    }
}
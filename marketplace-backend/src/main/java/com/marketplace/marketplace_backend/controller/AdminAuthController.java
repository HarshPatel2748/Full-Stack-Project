package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.AdminLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.AdminLoginResponseDTO;
import com.marketplace.marketplace_backend.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/auth")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseDTO> login(
            @RequestBody AdminLoginRequestDTO request) {

        return ResponseEntity.ok(adminAuthService.login(request));
    }
}
package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.AdminLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.AdminLoginResponseDTO;
import com.marketplace.marketplace_backend.entity.Admin;
import com.marketplace.marketplace_backend.repository.AdminRepository;
import com.marketplace.marketplace_backend.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminAuthServiceImpl implements AdminAuthService {

    private final AdminRepository adminRepository;

    @Override
    public AdminLoginResponseDTO login(AdminLoginRequestDTO request) {

        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!admin.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return AdminLoginResponseDTO.builder()
                .adminId(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .build();
    }
}

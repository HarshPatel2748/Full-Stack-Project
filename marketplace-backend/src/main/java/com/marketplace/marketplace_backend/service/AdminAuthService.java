package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.AdminLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.AdminLoginResponseDTO;

public interface AdminAuthService {

    AdminLoginResponseDTO login(AdminLoginRequestDTO request);
}

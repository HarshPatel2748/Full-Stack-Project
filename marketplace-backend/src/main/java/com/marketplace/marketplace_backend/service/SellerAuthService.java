package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.SellerLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupResponseDTO;

public interface SellerAuthService {

    SellerSignupResponseDTO signup(SellerSignupRequestDTO request);

    SellerLoginResponseDTO login(SellerLoginRequestDTO request);
}

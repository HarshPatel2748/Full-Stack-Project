package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.UserLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.UserLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.UserSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.UserSignupResponseDTO;

public interface UserAuthService {

    UserSignupResponseDTO signup(UserSignupRequestDTO dto);

    UserLoginResponseDTO login(UserLoginRequestDTO dto);
}

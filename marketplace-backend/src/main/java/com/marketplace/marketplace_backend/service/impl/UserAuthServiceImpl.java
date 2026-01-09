package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.UserLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.UserLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.UserSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.UserSignupResponseDTO;
import com.marketplace.marketplace_backend.entity.User;
import com.marketplace.marketplace_backend.repository.UserRepository;
import com.marketplace.marketplace_backend.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserSignupResponseDTO signup(UserSignupRequestDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();

        user = userRepository.save(user);

        return UserSignupResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }


    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return UserLoginResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}

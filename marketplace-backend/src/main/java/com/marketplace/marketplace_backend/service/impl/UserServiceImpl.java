package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.UserLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.UserLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.UserSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.UserSignupResponseDTO;
import com.marketplace.marketplace_backend.entity.User;
import com.marketplace.marketplace_backend.repository.UserRepository;
import com.marketplace.marketplace_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserSignupResponseDTO signup(UserSignupRequestDTO dto) {

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();

        User savedUser = userRepository.save(user);

        return UserSignupResponseDTO.builder()
                .userId(savedUser.getId())
                .message("User registered successfully")
                .build();
    }


    @Override
    public UserLoginResponseDTO login(UserLoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return UserLoginResponseDTO.builder()
                .userId(user.getId())
                .role("USER")
                .message("Login successful")
                .build();
    }
}

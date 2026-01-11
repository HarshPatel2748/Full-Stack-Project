package com.marketplace.marketplace_backend.service.impl;

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
    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}

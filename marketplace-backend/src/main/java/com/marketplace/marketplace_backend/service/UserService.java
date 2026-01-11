package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.entity.User;

public interface UserService {

    User getUserProfile(Long userId);
}

package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.UserLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.UserLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.UserSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.UserSignupResponseDTO;
import com.marketplace.marketplace_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // User signup
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDTO> signup(
            @RequestBody UserSignupRequestDTO dto){

        UserSignupResponseDTO response = userService.signup(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // User Login
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(
            @RequestBody UserLoginRequestDTO dto){
        UserLoginResponseDTO response = userService.login(dto);
        return ResponseEntity.ok(response);
    }
}

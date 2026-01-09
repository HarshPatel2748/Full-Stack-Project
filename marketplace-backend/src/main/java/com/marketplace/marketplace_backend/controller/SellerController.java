package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.SellerLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupResponseDTO;
import com.marketplace.marketplace_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;


    //Signup Seller
    @PostMapping("/signup")
    public ResponseEntity<SellerSignupResponseDTO> signup(
            @RequestBody SellerSignupRequestDTO dto){

        return new ResponseEntity<>(sellerService.signup(dto), HttpStatus.CREATED);
    }


    //Login Seller
    @PostMapping("/login")
    public ResponseEntity<SellerLoginResponseDTO> login(
            @RequestBody SellerLoginRequestDTO dto){

        return  ResponseEntity.ok(sellerService.login(dto));
    }
}

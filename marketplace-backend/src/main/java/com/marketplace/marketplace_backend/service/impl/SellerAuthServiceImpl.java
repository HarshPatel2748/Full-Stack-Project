package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.SellerLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupResponseDTO;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.entity.SellerStatus;
import com.marketplace.marketplace_backend.repository.SellerRepository;
import com.marketplace.marketplace_backend.service.SellerAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerAuthServiceImpl implements SellerAuthService {

    private final SellerRepository sellerRepository;

    // Seller Signup
    @Override
    public SellerSignupResponseDTO signup(SellerSignupRequestDTO request) {

        if (sellerRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Seller seller = Seller.builder()
                .shopName(request.getShopName())
                .ownerName(request.getOwnerName())
                .email(request.getEmail())
                .password(request.getPassword()) // plain for now
                .phone(request.getPhone())
                .address(request.getAddress())
                .status(SellerStatus.PENDING)
                .build();

        Seller savedSeller = sellerRepository.save(seller);

        return SellerSignupResponseDTO.builder()
                .sellerId(savedSeller.getId())
                .shopName(savedSeller.getShopName())
                .email(savedSeller.getEmail())
                .status(savedSeller.getStatus().name())
                .build();
    }

    // Seller Login
    @Override
    public SellerLoginResponseDTO login(SellerLoginRequestDTO request) {

        Seller seller = sellerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!seller.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return SellerLoginResponseDTO.builder()
                .sellerId(seller.getId())
                .shopName(seller.getShopName())
                .status(seller.getStatus().name())
                .build();
    }
}
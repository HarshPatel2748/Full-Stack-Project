package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.SellerLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerLoginResponseDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupRequestDTO;
import com.marketplace.marketplace_backend.dto.SellerSignupResponseDTO;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.entity.SellerStatus;
import com.marketplace.marketplace_backend.repository.SellerRepository;
import com.marketplace.marketplace_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;


    @Override
    public SellerSignupResponseDTO signup(SellerSignupRequestDTO dto) {

        if(sellerRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("Email already registered");
        }

        Seller seller = Seller.builder()
                .shopName(dto.getShopName())
                .ownerName(dto.getOwnerName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();

        Seller savedSeller = sellerRepository.save(seller);

        return SellerSignupResponseDTO.builder()
                .selerId(savedSeller.getId())
                .status(savedSeller.getStatus().name())
                .message("Seller registered successfully, waiting for approval")
                .build();
    }


    @Override
    public SellerLoginResponseDTO login(SellerLoginRequestDTO dto) {

        Seller seller = sellerRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!seller.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        if(seller.getStatus() != SellerStatus.APPROVED){
            throw  new RuntimeException("Seller is not approved");
        }

        return SellerLoginResponseDTO.builder()
                .sellerId(seller.getId())
                .role("SELLER")
                .message("Login successful")
                .build();
    }
}

package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.SellerProfileResponseDTO;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.repository.SellerRepository;
import com.marketplace.marketplace_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;


    @Override
    public SellerProfileResponseDTO getSellerProfile(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        return new SellerProfileResponseDTO(
                seller.getId(),
                seller.getShopName(),
                seller.getOwnerName(),
                seller.getEmail(),
                seller.getPhone(),
                seller.getAddress(),
                seller.getStatus().name(),
                seller.getApprovedAt(),
                seller.getCreatedAt()
        );
    }
}

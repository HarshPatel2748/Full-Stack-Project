package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.SellerProfileResponseDTO;

public interface SellerService {

    SellerProfileResponseDTO getSellerProfile(Long sellerId);
}

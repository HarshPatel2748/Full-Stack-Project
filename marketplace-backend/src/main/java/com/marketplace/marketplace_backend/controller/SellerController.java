package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.SellerProfileResponseDTO;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;


    @GetMapping("/{sellerId}/profile")
    public ResponseEntity<SellerProfileResponseDTO> getSellerProfile(
            @PathVariable Long sellerId){

        return ResponseEntity.ok(sellerService.getSellerProfile(sellerId));
    }
}

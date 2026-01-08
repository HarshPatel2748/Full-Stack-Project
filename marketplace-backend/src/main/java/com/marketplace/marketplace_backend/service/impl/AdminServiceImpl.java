package com.marketplace.marketplace_backend.service.impl;


import com.marketplace.marketplace_backend.entity.Admin;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.entity.SellerStatus;
import com.marketplace.marketplace_backend.repository.AdminRepository;
import com.marketplace.marketplace_backend.repository.SellerRepository;
import com.marketplace.marketplace_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final SellerRepository sellerRepository;

    private final AdminRepository adminRepository;

    //Get all pending sellers
    public List<Seller> getPendingSellers(){
        return sellerRepository.findByStatus(SellerStatus.PENDING);
    }

    //Approve Seller
    public Seller approveSeller(Long adminId, Long sellerId){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        seller.setStatus(SellerStatus.APPROVED);
        seller.setApprovedBy(admin);
        seller.setApprovedAt(java.time.LocalDateTime.now());

        return sellerRepository.save(seller);
    }

    //Reject Seller
    public Seller rejectSeller(Long adminId, Long sellerId){
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        seller.setStatus(SellerStatus.REJECTED);
        seller.setApprovedBy(admin);
        seller.setApprovedAt(java.time.LocalDateTime.now());

        return sellerRepository.save(seller);
    }
}

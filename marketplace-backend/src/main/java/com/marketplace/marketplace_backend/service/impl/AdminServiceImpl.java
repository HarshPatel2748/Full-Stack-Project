package com.marketplace.marketplace_backend.service.impl;


import com.marketplace.marketplace_backend.dto.AdminLoginRequestDTO;
import com.marketplace.marketplace_backend.dto.AdminLoginResponseDTO;
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


    //Admin Login
    @Override
    public AdminLoginResponseDTO login(AdminLoginRequestDTO dto) {

        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!admin.getPassword().equals(dto.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        return AdminLoginResponseDTO.builder()
                .adminId(admin.getId())
                .role("ADMIN")
                .message("Login successful")
                .build();
    }

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

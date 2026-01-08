package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.service.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminServiceImpl adminService;

    //List pending sellers
    @GetMapping("/sellers/pending")
    public ResponseEntity<List<Seller>> getPendingSellers(){
        return ResponseEntity.ok(adminService.getPendingSellers());
    }

    //Approve seller
    @PutMapping("/sellers/{sellerId}/approve/{adminId}")
    public ResponseEntity<String> approveSeller(
            @PathVariable Long sellerId,
            @PathVariable Long adminId){
        adminService.approveSeller(adminId, sellerId);
        return ResponseEntity.ok("Seller approved successfully");
    }

    //Reject seller
    @PutMapping("/sellers/{sellerId}/reject/{adminId}")
    public ResponseEntity<String> rejectSeller(
            @PathVariable Long sellerId,
            @PathVariable Long adminId){
        adminService.rejectSeller(adminId, sellerId);
        return ResponseEntity.ok("Seller rejected successfully");
    }
}

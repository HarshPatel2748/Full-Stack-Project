package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.entity.Seller;

import java.util.List;

public interface AdminService {

    List<Seller> getPendingSellers();

    Seller approveSeller(Long adminId, Long sellerId);

    Seller rejectSeller(Long adminId, Long sellerId);
}

package com.marketplace.marketplace_backend.repository;

import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.entity.SellerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findByEmail(String email);

    List<Seller> findByStatus(SellerStatus status);

    boolean existsByEmail(String email);
}

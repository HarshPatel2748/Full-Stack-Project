package com.marketplace.marketplace_backend.repository;

import com.marketplace.marketplace_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

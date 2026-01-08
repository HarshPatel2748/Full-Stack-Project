package com.marketplace.marketplace_backend.repository;

import com.marketplace.marketplace_backend.entity.Order;
import com.marketplace.marketplace_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByUser(User user);
}

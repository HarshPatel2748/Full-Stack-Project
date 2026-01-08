package com.marketplace.marketplace_backend.repository;

import com.marketplace.marketplace_backend.entity.Category;
import com.marketplace.marketplace_backend.entity.Product;
import com.marketplace.marketplace_backend.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySeller(Seller seller);

    List<Product> findByCategory(Category category);
}

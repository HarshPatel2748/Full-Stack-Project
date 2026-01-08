package com.marketplace.marketplace_backend.service;

import com.marketplace.marketplace_backend.dto.ProductRequestDTO;
import com.marketplace.marketplace_backend.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Long sellerId, ProductRequestDTO dto);

    Product updateProduct(Long sellerId, Long productId, ProductRequestDTO dto);

    void deleteProduct(Long sellerId, Long productId);

    List<Product> getSellerProducts(Long sellerId);

    List<Product> getAllProducts();
}

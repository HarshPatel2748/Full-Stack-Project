package com.marketplace.marketplace_backend.service.impl;

import com.marketplace.marketplace_backend.dto.ProductRequestDTO;
import com.marketplace.marketplace_backend.entity.Category;
import com.marketplace.marketplace_backend.entity.Product;
import com.marketplace.marketplace_backend.entity.Seller;
import com.marketplace.marketplace_backend.entity.SellerStatus;
import com.marketplace.marketplace_backend.repository.CategoryRepository;
import com.marketplace.marketplace_backend.repository.ProductRepository;
import com.marketplace.marketplace_backend.repository.SellerRepository;
import com.marketplace.marketplace_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    //Add Product
    @Override
    public Product addProduct(Long sellerId, ProductRequestDTO dto) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if(seller.getStatus() != SellerStatus.APPROVED) {
            throw new RuntimeException("Seller is not approved");
        }

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .imageUrl(dto.getImageUrl())
                .category(category)
                .seller(seller)
                .build();

        return productRepository.save(product);
    }

    //Update Product
    @Override
    public Product updateProduct(Long sellerId, Long productId, ProductRequestDTO dto) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(!product.getSeller().getId().equals(sellerId)){
            throw new RuntimeException("Unauthorized to update this product");
        }

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);

        return productRepository.save(product);
    }

    //Delete Product
    @Override
    public void deleteProduct(Long sellerId, Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(!product.getSeller().getId().equals(sellerId)){
            throw new RuntimeException("Unauthorized to delete this product");
        }

        productRepository.delete(product);
    }

    //Get Seller Products
    @Override
    public List<Product> getSellerProducts(Long sellerId) {

        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        return productRepository.findBySeller(seller);
    }

    //Get All Products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}

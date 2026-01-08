package com.marketplace.marketplace_backend.controller;

import com.marketplace.marketplace_backend.dto.ProductRequestDTO;
import com.marketplace.marketplace_backend.entity.Product;
import com.marketplace.marketplace_backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ====================== Seller APIs ======================

    //Add Product
    @PostMapping("/seller/{sellerId}")
    public ResponseEntity<Product> addProduct(
            @PathVariable Long sellerId,
            @RequestBody ProductRequestDTO dto){

        return ResponseEntity.ok(productService.addProduct(sellerId, dto));
    }

    //Update Product
    @PutMapping("/seller/{sellerId}/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long sellerId,
            @PathVariable Long productId,
            @RequestBody ProductRequestDTO dto){

        return ResponseEntity.ok(productService.updateProduct(sellerId, productId, dto));
    }

    //Delete Product
    @DeleteMapping("/seller/{sellerId}/{productId}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long sellerId,
            @PathVariable Long productId) {

        productService.deleteProduct(sellerId, productId);
        return ResponseEntity.ok("Product deleted successfully");
    }

    //View Seller's Products
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getSellerProducts(
            @PathVariable Long sellerId) {

        return ResponseEntity.ok(productService.getSellerProducts(sellerId));
    }

    // ====================== Public APIs ======================

    //View All Products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}

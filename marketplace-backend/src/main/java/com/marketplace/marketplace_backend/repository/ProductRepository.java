package com.marketplace.marketplace_backend.repository;

import com.marketplace.marketplace_backend.entity.Category;
import com.marketplace.marketplace_backend.entity.Product;
import com.marketplace.marketplace_backend.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySellerId(Long sellerId);

    List<Product> findBySeller(Seller seller);

    List<Product> findByCategory(Category category);

    @Query("""
    SELECT p FROM Product p
    WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(p.category.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Product> searchProducts(@Param("keyword") String keyword);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    Collection<Object> findByNameContainingIgnoreCase(String keyword);
}

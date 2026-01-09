package com.marketplace.marketplace_backend.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;

    private Long sellerId;
    private String sellerShopName;

    private Long categoryId;
    private String categoryName;
}

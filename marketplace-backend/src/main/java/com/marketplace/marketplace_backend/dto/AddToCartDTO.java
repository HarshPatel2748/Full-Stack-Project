package com.marketplace.marketplace_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddToCartDTO {

    private Long userId;
    private Long productId;
    private Integer quantity;
}

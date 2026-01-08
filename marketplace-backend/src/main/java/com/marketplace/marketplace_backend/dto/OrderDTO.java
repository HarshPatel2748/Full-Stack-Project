package com.marketplace.marketplace_backend.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long userId;
    private List<Long> productIds;
    private List<Integer> quantities;
}

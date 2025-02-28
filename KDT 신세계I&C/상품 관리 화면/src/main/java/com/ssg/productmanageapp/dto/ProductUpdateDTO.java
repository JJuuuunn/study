package com.ssg.productmanageapp.dto;

import lombok.Builder;

@Builder
public record ProductUpdateDTO(
        Long productId,
        String productName,
        double price,
        int stockQuantity
) {
}

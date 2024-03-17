package com.ssg.productmanageapp.dto;

import lombok.Builder;

@Builder
public record ProductAddDTO(
        String productName,
        double price,
        int stockQuantity
) {

}

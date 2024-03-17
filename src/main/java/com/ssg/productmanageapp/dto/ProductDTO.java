package com.ssg.productmanageapp.dto;

import com.ssg.productmanageapp.domain.ProductVO;
import lombok.Builder;

@Builder
public record ProductDTO(
        Long productId,
        String productName,
        double price,
        int stockQuantity
) {
    public static ProductDTO from(ProductVO vo) {
        return ProductDTO.builder()
                .productId(vo.getProductId())
                .productName(vo.getProductName())
                .price(vo.getPrice())
                .stockQuantity(vo.getStockQuantity())
                .build();
    }
}

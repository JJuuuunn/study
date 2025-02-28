package com.ssg.productmanageapp.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductVO {
    private Long productId;
    private String productName;
    private double price;
    private int stockQuantity;
}

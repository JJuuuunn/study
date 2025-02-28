package com.ssg.productmanageapp.dto;

import lombok.Builder;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
public record ProductAddDTO(
        @NotEmpty
        String productName,

        @Positive
        double price,

        @Positive
        int stockQuantity
) {

}

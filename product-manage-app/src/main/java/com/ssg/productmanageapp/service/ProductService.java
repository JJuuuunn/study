package com.ssg.productmanageapp.service;

import com.ssg.productmanageapp.dto.*;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductAddDTO dto);
    ProductDTO searchProductById(Long id);
    ProductDTO searchProductByName(String name);
    List<ProductDTO> getAllProducts();
    void updateProduct(ProductUpdateDTO dto);
    void removeProduct(Long id);

    void getAllTestProducts();

    PageResponseDTO<ProductDTO> getAllProducts(PageRequestDTO pageRequestDTO);
}

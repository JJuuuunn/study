package com.ssg.productmanageapp.service;

import com.ssg.productmanageapp.dto.ProductAddDTO;
import com.ssg.productmanageapp.dto.ProductDTO;
import com.ssg.productmanageapp.dto.ProductUpdateDTO;

import java.util.List;

public interface ProductService {
    Long addProduct(ProductAddDTO dto);
    ProductDTO searchProductById(Long id);
    ProductDTO searchProductByName(String name);
    List<ProductDTO> getAllProducts();
    void updateProduct(ProductUpdateDTO dto);
    void removeProduct(Long id);

    void getAllTestProducts();
}

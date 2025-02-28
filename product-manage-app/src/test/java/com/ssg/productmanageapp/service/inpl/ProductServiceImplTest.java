package com.ssg.productmanageapp.service.inpl;

import com.ssg.productmanageapp.dto.ProductAddDTO;
import com.ssg.productmanageapp.dto.ProductDTO;
import com.ssg.productmanageapp.dto.ProductUpdateDTO;
import com.ssg.productmanageapp.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@DisplayName("ProductServiceImpl 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @AfterEach
    void tearDown() {
        productService.getAllTestProducts();
    }


//    Long addProduct(ProductAddDTO dto);
//    ProductDTO searchProductById(Long id);
//    ProductDTO searchProductByName(String name);
//    List<ProductDTO> getAllProducts();
//    void updateProduct(ProductUpdateDTO dto);
//    void removeProduct(Long id);

    @Test
    @DisplayName("상품을 추가합니다.")
    void givenProductAddDTO_whenAddProduct_thenSucceed() {
        // given
        ProductAddDTO dto = ProductAddDTO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();

        // when
        Long result = productService.addProduct(dto);

        // then
        assertNotNull(result);
        log.info("추가된 상품의 id: {}", result);
    }

    @Test
    @DisplayName("상품을 id로 조회합니다.")
    void givenId_whenSearchProductById_thenSucceed() {
        // given
        ProductAddDTO dto = ProductAddDTO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();
        Long id = productService.addProduct(dto);

        // when
        ProductDTO result = productService.searchProductById(id);

        // then
        assertNotNull(result);
        log.info("조회된 상품: {}", result);
    }

    @Test
    @DisplayName("상품을 이름으로 조회합니다.")
    void givenName_whenSearchProductByName_thenSucceed() {
        // given
        ProductAddDTO dto = ProductAddDTO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();
        productService.addProduct(dto);

        // when
        ProductDTO result = productService.searchProductByName("test_커피");

        // then
        assertNotNull(result);
        log.info("조회된 상품: {}", result);
    }

    @Test
    @DisplayName("모든 상품을 조회합니다.")
    void givenNothing_whenGetAllProducts_thenSucceed() {
        // given
        ProductAddDTO dto1 = ProductAddDTO.builder()
                .productName("test_커피1")
                .price(3000)
                .stockQuantity(10)
                .build();
        productService.addProduct(dto1);

        ProductAddDTO dto2 = ProductAddDTO.builder()
                .productName("test_커피2")
                .price(3000)
                .stockQuantity(10)
                .build();
        productService.addProduct(dto2);

        // when
        List<ProductDTO> result = productService.getAllProducts();

        // then
        assertNotNull(result);
        log.info("조회된 상품 목록: {}", result);
    }

    @Test
    @DisplayName("상품을 수정합니다.")
    void givenProductUpdateDTO_whenUpdateProduct_thenSucceed() {
        // given
        ProductAddDTO dto = ProductAddDTO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();
        Long id = productService.addProduct(dto);

        ProductUpdateDTO updateDTO = ProductUpdateDTO.builder()
                .productId(id)
                .productName("test_커피2")
                .price(4000)
                .stockQuantity(20)
                .build();

        // when
        productService.updateProduct(updateDTO);

        // then
        ProductDTO result = productService.searchProductById(id);
        assertNotNull(result);
        log.info("수정된 상품: {}", result);
    }

    @Test
    @DisplayName("상품을 삭제합니다.")
    void givenId_whenRemoveProduct_thenSucceed() {
        // given
        ProductAddDTO dto = ProductAddDTO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();
        Long id = productService.addProduct(dto);

        // when
        productService.removeProduct(id);

        // then
        assertThrows(NullPointerException.class, () -> productService.searchProductById(id));
    }
}
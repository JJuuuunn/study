package com.ssg.productmanageapp.mapper;

import com.ssg.productmanageapp.domain.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@DisplayName("ProductMapper 테스트")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
class ProductMapperTest {

    @Autowired(required = false)
    ProductMapper productMapper;

    @Test
    @DisplayName("상품을 추가합니다.")
    void givenProductVO_whenInsert_thenSucceed() {
        // given
        ProductVO productVO = ProductVO.builder()
                .productName("test_커피")
                .price(3000)
                .stockQuantity(10)
                .build();

        // when
        Long result = productMapper.addProduct(productVO);

        // then
        Long id = productVO.getProductId();
        assertNotNull(result);
        log.info("추가된 상품의 id: {}", id);
    }

    @Test
    @DisplayName("전체 상품 목록을 조회합니다.")
    void givenNothing_whenFindAll_thenSucceed() {
        // given
        for (int i = 0; i < 3; i++) {
            ProductVO productVO = ProductVO.builder()
                    .productName("test_findAll_" + i)
                    .price(3000)
                    .stockQuantity(10)
                    .build();
            productMapper.addProduct(productVO);
        }

        // when
        List<ProductVO> voList = productMapper.findAllProducts();

        // then
        voList.forEach(vo -> log.info("전체 상품 목록: {}", vo));
        assertNotNull(voList);
    }

    @Test
    @DisplayName("상품 id로 상품을 조회합니다.")
    void givenProductId_whenFindById_thenSucceed() {
        // given
        ProductVO productVO = ProductVO.builder()
                .productName("test_findById")
                .price(3000)
                .stockQuantity(10)
                .build();
        productMapper.addProduct(productVO);
        Long id = productVO.getProductId();

        // when
        ProductVO result = productMapper.findProductById(id);

        // then
        assertNotNull(result);
        log.info("조회된 상품 정보: {}", result);

        assertEquals(id, result.getProductId());
        assertEquals("test_findById", result.getProductName());
        assertEquals(3000, result.getPrice());
        assertEquals(10, result.getStockQuantity());
    }

    @Test
    @DisplayName("상품 이름으로 상품을 검색합니다.")
    void givenProductName_whenFindByName_thenSucceed() {
        // given
        ProductVO productVO = ProductVO.builder()
                .productName("test_findByName")
                .price(3000)
                .stockQuantity(10)
                .build();
        productMapper.addProduct(productVO);
        Long id = productVO.getProductId();

        // when
        ProductVO result = productMapper.findProductByName("test_findByName");

        // then
        assertNotNull(result);
        log.info("검색된 상품 정보: {}", result);

        assertEquals(id, result.getProductId());
        assertEquals("test_findByName", result.getProductName());
        assertEquals(3000, result.getPrice());
        assertEquals(10, result.getStockQuantity());
    }

    @Test
    @DisplayName("상품 정보를 수정합니다.")
    void givenProductVO_whenUpdate_thenSucceed() {
        // given
        ProductVO productVO = ProductVO.builder()
                .productName("test_커피2")
                .price(3000)
                .stockQuantity(10)
                .build();
        productMapper.addProduct(productVO);
        Long id = productVO.getProductId();

        ProductVO updatedProductVO = ProductVO.builder()
                .productId(id)
                .productName("test_커피2")
                .price(4000)
                .stockQuantity(20)
                .build();

        // when
        productMapper.updateProduct(updatedProductVO);

        // then
        ProductVO result = productMapper.findProductById(id);
        assertEquals(updatedProductVO.getProductId(), result.getProductId());
        assertEquals(updatedProductVO.getProductName(), result.getProductName());
        assertEquals(updatedProductVO.getPrice(), result.getPrice());
        assertEquals(updatedProductVO.getStockQuantity(), result.getStockQuantity());
        log.info("수정된 상품 정보: {}", result);
    }

    @Test
    @DisplayName("상품을 제거합니다.")
    void givenProductId_whenDelete_thenSucceed() {
        // given
        ProductVO productVO = ProductVO.builder()
                .productName("test_커피3")
                .price(3000)
                .stockQuantity(10)
                .build();
        productMapper.addProduct(productVO);
        Long id = productVO.getProductId();

        // when
        productMapper.deleteProduct(id);

        // then
        ProductVO result = productMapper.findProductById(id);
        assertNull(result);
        log.info("삭제된 상품의 id: {}", id);
    }

}
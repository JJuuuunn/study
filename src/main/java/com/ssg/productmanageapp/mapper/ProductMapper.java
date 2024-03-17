package com.ssg.productmanageapp.mapper;

import com.ssg.productmanageapp.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ProductMapper {
    /**
     * 상품을 추가합니다.
     * @param productVO
     */
    Long addProduct(ProductVO productVO);

    /**
     * 상품 정보를 수정합니다.
     * @param productVO
     */
    void updateProduct(ProductVO productVO);

    /**
     * 상품을 제거합니다.
     * @param id
     */
    void deleteProduct(Long id);

    /**
     * 상품을 조회합니다.
     * @param id
     * @return
     */
    ProductVO findProductById(Long id);

    /**
     * 전체 상품 목록을 조회합니다.
     * @return
     */
    List<ProductVO> findAllProducts();

    /**
     * 상품 이름으로 상품을 검색합니다.
     * @param name
     * @return
     */
    ProductVO findProductByName(String name);

    /**
     * 테스트 데이터들을 삭제합니다.
     */
    void deleteTestDatas();
}

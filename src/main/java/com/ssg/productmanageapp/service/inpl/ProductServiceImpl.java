package com.ssg.productmanageapp.service.inpl;

import com.ssg.productmanageapp.domain.ProductVO;
import com.ssg.productmanageapp.dto.ProductAddDTO;
import com.ssg.productmanageapp.dto.ProductDTO;
import com.ssg.productmanageapp.dto.ProductUpdateDTO;
import com.ssg.productmanageapp.mapper.ProductMapper;
import com.ssg.productmanageapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public Long addProduct(ProductAddDTO dto) {
        // dto를 vo로 변환
        ProductVO vo = ProductVO.builder()
                .productName(dto.productName())
                .price(dto.price())
                .stockQuantity(dto.stockQuantity())
                .build();
        productMapper.addProduct(vo);

        // 생성된 상품의 id를 반환.
        return vo.getProductId();
    }

    @Override
    public ProductDTO searchProductById(Long id) {
        ProductVO vo = productMapper.findProductById(id);

        // vo를 dto로 변환
        ProductDTO dto = ProductDTO.from(vo);

        return dto;
    }

    @Override
    public ProductDTO searchProductByName(String name) {
        ProductVO vo = productMapper.findProductByName(name);

        // vo를 dto로 변환
        ProductDTO dto = ProductDTO.from(vo);

        return dto;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductVO> voList = productMapper.findAllProducts();

        List<ProductDTO> dtoList = voList.stream().map(ProductDTO::from).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void updateProduct(ProductUpdateDTO dto) {
        ProductVO vo = ProductVO.builder()
                .productId(dto.productId())
                .productName(dto.productName())
                .price(dto.price())
                .stockQuantity(dto.stockQuantity())
                .build();
        productMapper.updateProduct(vo);
    }

    @Override
    public void removeProduct(Long id) {
        productMapper.deleteProduct(id);
    }


    // 테스트 데이터 제거용 메서드
    @Override
    public void getAllTestProducts() {
        productMapper.deleteTestDatas();
    }
}

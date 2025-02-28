package com.yang.kakaomap.pharmacy.service;

import com.yang.kakaomap.api.dto.DocumentDto;
import com.yang.kakaomap.api.dto.KakaoApiResponseDto;
import com.yang.kakaomap.api.service.KakaoAddressSearchService;

import com.yang.kakaomap.direction.dto.FindType;
import com.yang.kakaomap.direction.dto.OutputDto;
import com.yang.kakaomap.direction.entity.Direction;
import com.yang.kakaomap.direction.service.Base62Service;
import com.yang.kakaomap.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yang.kakaomap.direction.dto.FindType.PHARMACY;

@Service
@Slf4j
@RequiredArgsConstructor
public class PharmacyRecommendationService {

    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;

    @Value("${use.kakaomap.base.url}")
    private String baseUrl;

    public List<OutputDto> recommendPharmacyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[PharmacyRecommendationService.recommendPharmacyList fail] Input address: {}", address);
            return Collections.emptyList();
        }
        log.info("[PharmacyRecommendationService.recommendPharmacyList success] Input address: {}", address);

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        log.info("====== DocumentDto ======");
        log.info("PlaceName : " + documentDto.getPlaceName());
        log.info("AddressName : " + documentDto.getAddressName());
        log.info("Latitude : " + documentDto.getLatitude());
        log.info("Longitude : " + documentDto.getLongitude());
        log.info("Distance : " + documentDto.getDistance());
        log.info("=========================");

        List<Direction> directionList = directionService.buildDirectionList(documentDto, PHARMACY);

        log.info("===== Direction =====");
        for (Direction direction : directionList) {
            System.out.println("InputAddress : " + direction.getInputAddress());
            System.out.println("InputLatitude : " + direction.getInputLatitude());
            System.out.println("InputLongitude : " + direction.getInputLongitude());
            System.out.println("TargetPharmacyName : " + direction.getTargetPharmacyName());
            System.out.println("TargetAddress : " + direction.getTargetAddress());
            System.out.println("TargetLatitude : " + direction.getTargetLatitude());
            System.out.println("TargetLongitude : " + direction.getTargetLongitude());
            System.out.println("Distance : " + direction.getDistance());
        }
        log.info("=======================");

        return directionService.saveAll(directionList)
                .stream()
                .map(this::convertToOutputDto)
                .collect(Collectors.toList());
    }

    private OutputDto convertToOutputDto(Direction direction) {

        return OutputDto.builder()
                .name(direction.getTargetPharmacyName())
                .address(direction.getTargetAddress())
                .directionUrl(baseUrl + base62Service.encodeDirectionId(direction.getId()))
                .roadViewUrl(ROAD_VIEW_BASE_URL + direction.getTargetLatitude() + "," + direction.getTargetLongitude())
                .distance(String.format("%.2f km", direction.getDistance()))
                .build();
    }
}

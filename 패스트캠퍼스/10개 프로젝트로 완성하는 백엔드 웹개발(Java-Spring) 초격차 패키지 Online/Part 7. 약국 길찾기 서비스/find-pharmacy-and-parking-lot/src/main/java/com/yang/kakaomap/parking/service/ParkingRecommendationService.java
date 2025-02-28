package com.yang.kakaomap.parking.service;

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

import static com.yang.kakaomap.direction.dto.FindType.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParkingRecommendationService {

    private static final String ROAD_VIEW_BASE_URL = "https://map.kakao.com/link/roadview/";

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;
    private final Base62Service base62Service;

    @Value("${use.kakaomap.base.url}")
    private String baseUrl;

    public List<OutputDto> recommendParkingList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())) {
            log.error("[ParkingRecommendationService.recommendParkingList fail] Input address: {}", address);
            return Collections.emptyList();
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto, PARKING);

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

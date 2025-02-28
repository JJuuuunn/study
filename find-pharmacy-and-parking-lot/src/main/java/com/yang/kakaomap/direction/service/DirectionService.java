package com.yang.kakaomap.direction.service;

import com.yang.kakaomap.api.dto.DocumentDto;
import com.yang.kakaomap.api.service.KakaoCategorySearchService;
import com.yang.kakaomap.direction.dto.FindType;
import com.yang.kakaomap.direction.entity.Direction;
import com.yang.kakaomap.direction.repository.DirectionRepository;
import com.yang.kakaomap.parking.service.ParkingSearchService;
import com.yang.kakaomap.pharmacy.service.PharmacySearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionService {
    private static final int MAX_SEARCH_COUNT = 5; // 최대 검색 갯수
    private static final double RADIUS_KM = 100.0; // 반경 10 km
    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    private final PharmacySearchService pharmacySearchService;
    private final ParkingSearchService parkingSearchService;
    private final DirectionRepository directionRepository;
    private final Base62Service base62Service;

    private final KakaoCategorySearchService kakaoCategorySearchService;

    @Transactional
    public List<Direction> saveAll(List<Direction> directionList) {
        if (CollectionUtils.isEmpty(directionList)) return Collections.emptyList();
        return directionRepository.saveAll(directionList);
    }

    @Transactional(readOnly = true)
    public String findDirectionUrlById(String encodedId) {

        Long decodedId = base62Service.decodeDirectionId(encodedId);
        Direction direction = directionRepository.findById(decodedId).orElse(null);

        String params = String.join(",", direction.getTargetPharmacyName(),
                String.valueOf(direction.getTargetLatitude()), String.valueOf(direction.getTargetLongitude()));
        String result = UriComponentsBuilder.fromHttpUrl(DIRECTION_BASE_URL + params)
                .toUriString();

        return result;
    }

    public List<Direction> buildDirectionList(DocumentDto documentDto, FindType type) {
        if(Objects.isNull(documentDto)) return Collections.emptyList();

        log.info("[buildDirectionList Type : " + type);

        switch (type) {
            case PHARMACY:
                List<Direction> result = pharmacySearchService.searchPharmacyDtoList()
                        .stream().map(pharmacyDto ->
                                Direction.builder()
                                        .inputAddress(documentDto.getAddressName())
                                        .inputLatitude(documentDto.getLatitude())
                                        .inputLongitude(documentDto.getLongitude())
                                        .targetPharmacyName(pharmacyDto.getPharmacyName())
                                        .targetAddress(pharmacyDto.getPharmacyAddress())
                                        .targetLatitude(pharmacyDto.getLatitude())
                                        .targetLongitude(pharmacyDto.getLongitude())
                                        .distance(
                                                calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                                        pharmacyDto.getLatitude(), pharmacyDto.getLongitude()))
                                        .build())
                        .filter(direction -> direction.getDistance() <= RADIUS_KM)
                        .sorted(Comparator.comparing(Direction::getDistance))
                        .limit(MAX_SEARCH_COUNT)
                        .collect(Collectors.toList());
                log.info("buildDirectionList Pharmacy : succes stream.map.filter");
                log.info("===== Direction =====");
                for (Direction direction : result) {
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
                return result;

            case PARKING:
                return parkingSearchService.searchParkingDtoList()
                        .stream().map(parkingDto ->
                                Direction.builder()
                                        .inputAddress(documentDto.getAddressName())
                                        .inputLatitude(documentDto.getLatitude())
                                        .inputLongitude(documentDto.getLongitude())
                                        .targetPharmacyName(parkingDto.getParkingName())
                                        .targetAddress(parkingDto.getParkingAddress())
                                        .targetLatitude(parkingDto.getLatitude())
                                        .targetLongitude(parkingDto.getLongitude())
                                        .distance(
                                                calculateDistance(documentDto.getLatitude(), documentDto.getLongitude(),
                                                        parkingDto.getLatitude(), parkingDto.getLongitude()))
                                        .build())
                        .filter(direction -> direction.getDistance() <= RADIUS_KM)
                        .sorted(Comparator.comparing(Direction::getDistance))
                        .limit(MAX_SEARCH_COUNT)
                        .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    // pharmacy search by category kakao api
    public List<Direction> buildDirectionListByCategoryApi(DocumentDto inputDocumentDto) {
        if(Objects.isNull(inputDocumentDto)) return Collections.emptyList();

        return kakaoCategorySearchService
                .requestPharmacyCategorySearch(inputDocumentDto.getLatitude(), inputDocumentDto.getLongitude(), RADIUS_KM)
                .getDocumentList()
                .stream().map(resultDocumentDto ->
                        Direction.builder()
                                .inputAddress(inputDocumentDto.getAddressName())
                                .inputLatitude(inputDocumentDto.getLatitude())
                                .inputLongitude(inputDocumentDto.getLongitude())
                                .targetPharmacyName(resultDocumentDto.getPlaceName())
                                .targetAddress(resultDocumentDto.getAddressName())
                                .targetLatitude(resultDocumentDto.getLatitude())
                                .targetLongitude(resultDocumentDto.getLongitude())
                                .distance(resultDocumentDto.getDistance() * 0.001) // km 단위
                                .build())
                .limit(MAX_SEARCH_COUNT)
                .collect(Collectors.toList());
    }

    // 최단거리 구하기, 하버사인 공식(Haversine formula)
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371; // 지구 반지름(km)

        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        return earthRadius * Math.acos(
                Math.sin(lat1) * Math.sin(lat2) +
                        Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2)
        );
    }
}

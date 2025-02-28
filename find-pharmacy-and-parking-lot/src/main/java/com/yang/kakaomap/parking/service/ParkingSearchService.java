package com.yang.kakaomap.parking.service;

import com.yang.kakaomap.parking.cache.ParkingRedisTemplateService;
import com.yang.kakaomap.parking.dto.ParkingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingSearchService {

    private final ParkingService parkingService;
    private final ParkingRedisTemplateService parkingRedisTemplateService;

    public List<ParkingDto> searchParkingDtoList() {
        // redis
        List<ParkingDto> parkingDtoList = parkingRedisTemplateService.findAll();
        if(CollectionUtils.isNotEmpty(parkingDtoList)) return parkingDtoList;

        // db
        return parkingService.findAll()
                .stream()
                .map(ParkingDto::from)
                .collect(Collectors.toList());
    }
}
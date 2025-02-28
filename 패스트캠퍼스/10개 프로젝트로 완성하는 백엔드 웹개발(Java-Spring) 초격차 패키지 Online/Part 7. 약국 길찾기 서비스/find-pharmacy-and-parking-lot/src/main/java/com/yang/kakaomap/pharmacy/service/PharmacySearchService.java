package com.yang.kakaomap.pharmacy.service;

import com.yang.kakaomap.pharmacy.cache.PharmacyRedisTemplateService;
import com.yang.kakaomap.pharmacy.dto.PharmacyDto;
import com.yang.kakaomap.pharmacy.entity.Pharmacy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyService pharmacyService;
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;

    public List<PharmacyDto> searchPharmacyDtoList() {
        // redis
        List<PharmacyDto> pharmacyDtoList = pharmacyRedisTemplateService.findAll();
        if(CollectionUtils.isNotEmpty(pharmacyDtoList)) return pharmacyDtoList;

        log.info("searchPharmacyDtoList() : No Redis, Yes Database");

        // db
        List<Pharmacy> all = pharmacyService.findAll();
        log.info("find all : " + all.size());
        return all
                .stream()
                .map(this::convertToPharmacyDto)
                .collect(Collectors.toList());
    }

    private PharmacyDto convertToPharmacyDto(Pharmacy pharmacy) {
        return PharmacyDto.builder()
                .id(pharmacy.getId())
                .pharmacyName(pharmacy.getName())
                .pharmacyAddress(pharmacy.getAddress())
                .latitude(nullCheck(pharmacy.getLatitude()))
                .longitude(nullCheck(pharmacy.getLongitude()))
                .build();
    }

    private double nullCheck(String column) {
        if (column == null || column.isEmpty()) return 0;
        else return Double.valueOf(column);
    }
}
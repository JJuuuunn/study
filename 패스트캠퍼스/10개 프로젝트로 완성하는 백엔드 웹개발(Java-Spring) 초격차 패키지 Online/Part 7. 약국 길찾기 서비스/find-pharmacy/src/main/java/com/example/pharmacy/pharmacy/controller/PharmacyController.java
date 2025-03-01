package com.example.pharmacy.pharmacy.controller;

import com.example.pharmacy.pharmacy.cache.PharmacyRedisTemplateService;
import com.example.pharmacy.pharmacy.dto.PharmacyDto;
import com.example.pharmacy.pharmacy.entity.Pharmacy;
import com.example.pharmacy.pharmacy.service.PharmacyRepositoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PharmacyController {

    private final PharmacyRepositoryService pharmacyRepositoryService;
    private final PharmacyRedisTemplateService pharmacyRedisTemplateService;

    // 데이터 초기 셋팅을 위한 임시 메서드
    @GetMapping("/redis/save")
    public String save() {
        List<PharmacyDto> pharmacyDtoList = pharmacyRepositoryService.findAll()
                .stream().map(pharmacy -> PharmacyDto.builder()
                        .id(pharmacy.getId())
                        .pharmacyName(pharmacy.getPharmacyName())
                        .pharmacyAddress(pharmacy.getPharmacyAddress())
                        .latitude(pharmacy.getLatitude())
                        .longitude(pharmacy.getLongitude())
                        .build())
                .collect(Collectors.toList());

        pharmacyDtoList.forEach(pharmacyRedisTemplateService::save);

        return "success";
    }

//    // 데이터 초기 셋팅을 위한 임시 메소드
//    @GetMapping("/csv/save")
//    public String saveCsv() {
//        //saveCsvToDatabase();
//        saveCsvToRedis();
//
//        return "success save";
//    }
//
//    public void saveCsvToDatabase() {
//
//        List<Pharmacy> pharmacyList = loadPharmacyList();
//        pharmacyRepositoryService.saveAll(pharmacyList);
//
//    }
//
//    public void saveCsvToRedis() {
//        List<PharmacyDto> pharmacyDtoList = pharmacyRepositoryService.findAll()
//                .stream().map(pharmacy -> PharmacyDto.builder()
//                        .id(pharmacy.getId())
//                        .pharmacyName(pharmacy.getPharmacyName())
//                        .pharmacyAddress(pharmacy.getPharmacyAddress())
//                        .latitude(pharmacy.getLatitude())
//                        .longitude(pharmacy.getLongitude())
//                        .build())
//                .collect(Collectors.toList());
//
//        pharmacyDtoList.forEach(pharmacyRedisTemplateService::save);
//
//
//    }
//
//    private List<Pharmacy> loadPharmacyList() {
//        return CsvUtils.convertToPharmacyDtoList()
//                .stream().map(pharmacyDto ->
//                        Pharmacy.builder()
//                                .id(pharmacyDto.getId())
//                                .pharmacyName(pharmacyDto.getPharmacyName())
//                                .pharmacyAddress(pharmacyDto.getPharmacyAddress())
//                                .latitude(pharmacyDto.getLatitude())
//                                .longitude(pharmacyDto.getLongitude())
//                                .build())
//                .collect(Collectors.toList());
//    }
}
package com.example.pharmacy.pharmacy.service

import com.example.pharmacy.pharmacy.cache.PharmacyRedisTemplateService
import com.example.pharmacy.pharmacy.dto.PharmacyDto
import com.example.pharmacy.pharmacy.entity.Pharmacy
import com.google.common.collect.Lists
import spock.lang.Specification

class PharmacySearchServiceTest extends Specification {

    private PharmacySearchService pharmacySearchService

    private PharmacyRedisTemplateService pharmacyRedisTemplateService = Mock()
    private PharmacyRepositoryService pharmacyRepositoryService = Mock()

    private List<Pharmacy> pharmacyList

    def setup() {
        pharmacySearchService = new PharmacySearchService(pharmacyRepositoryService, pharmacyRedisTemplateService)

        pharmacyList = Lists.newArrayList(
                Pharmacy.builder()
                        .id(1L)
                        .pharmacyName("호수온누리약국")
                        .latitude(37.60894036)
                        .longitude(127.029052)
                        .build(),
                Pharmacy.builder()
                        .id(2L)
                        .pharmacyName("돌곶이온누리약국")
                        .latitude(37.61040424)
                        .longitude(127.0569046)
                        .build()
        )
    }

    def "레디스 장애시 DB를 이용하여 약국 데이터 조회"() {
        when:
        pharmacyRedisTemplateService.findAll() >> []
        pharmacyRepositoryService.findAll() >> pharmacyList

        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 2
    }

    def "searchPharmacyDtoList convert pharmacyList to pharmacyDtoList"() {
        when:
        pharmacyRepositoryService.findAll() >> pharmacyList
        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 2
        result.get(0).getId() == 1
        result.get(0).getPharmacyName() == "호수온누리약국"
        result.get(1).getId() == 2
        result.get(1).getPharmacyName() == "돌곶이온누리약국"
    }

    def "searchPharmacyDtoList return empty list if pharmacyList is empty"() {
        when:
        pharmacyRepositoryService.findAll() >> []
        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 0
        result.empty
    }
}

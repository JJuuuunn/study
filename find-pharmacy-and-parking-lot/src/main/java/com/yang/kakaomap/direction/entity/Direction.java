package com.yang.kakaomap.direction.entity;

import com.yang.kakaomap.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "direction")
@Getter
@NoArgsConstructor
public class Direction extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 목적지
    private String inputAddress;
    private double inputLatitude;
    private double inputLongitude;

    // 주변 위치(주차장 or 약국)
    private String targetPharmacyName;
    private String targetAddress;
    private double targetLatitude;
    private double targetLongitude;

    // 목적지과 주변 위치(주차장 or 약국) 간의 거리
    private double distance;

    @Builder
    public Direction(Long id, String inputAddress, double inputLatitude, double inputLongitude, String targetPharmacyName, String targetAddress, double targetLatitude, double targetLongitude, double distance) {
        this.id = id;
        this.inputAddress = inputAddress;
        this.inputLatitude = inputLatitude;
        this.inputLongitude = inputLongitude;
        this.targetPharmacyName = targetPharmacyName;
        this.targetAddress = targetAddress;
        this.targetLatitude = targetLatitude;
        this.targetLongitude = targetLongitude;
        this.distance = distance;
    }
}
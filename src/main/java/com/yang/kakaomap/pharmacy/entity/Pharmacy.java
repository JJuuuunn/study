package com.yang.kakaomap.pharmacy.entity;

import com.yang.kakaomap.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity(name = "pharmacy")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pharmacy extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hpId; // 기관 Id (hpId)
    private String name; // 이름
    private String info; // 상세설명
    private String tel; // 전화번호
    private String address; // 주소
    private String zipCode; // 우편번호 (postCdn1 + postCdn2)
    private double latitude; // 위도
    private double longitude; // 경도일

    // 요일별(+공휴일) 영엄시간 (오픈시간 ~ 마감시간)
    private LocalTime monOpenTime;
    private LocalTime monCloseTime;
    private LocalTime tueOpenTime;
    private LocalTime tueCloseTime;
    private LocalTime wedOpenTime;
    private LocalTime wedCloseTime;
    private LocalTime thuOpenTime;
    private LocalTime thuCloseTime;
    private LocalTime friOpenTime;
    private LocalTime friCloseTime;
    private LocalTime satOpenTime;
    private LocalTime satCloseTime;
    private LocalTime sunOpenTime;
    private LocalTime sunCloseTime;
    private LocalTime holOpenTime;
    private LocalTime holCloseTime;

    @Builder
    public Pharmacy(Long id, String hpId, String name, String info, String tel, String address, String zipCode, double latitude, double longitude, LocalTime monOpenTime, LocalTime monCloseTime, LocalTime tueOpenTime, LocalTime tueCloseTime, LocalTime wedOpenTime, LocalTime wedCloseTime, LocalTime thuOpenTime, LocalTime thuCloseTime, LocalTime friOpenTime, LocalTime friCloseTime, LocalTime satOpenTime, LocalTime satCloseTime, LocalTime sunOpenTime, LocalTime sunCloseTime, LocalTime holOpenTime, LocalTime holCloseTime) {
        this.id = id;
        this.hpId = hpId;
        this.name = name;
        this.info = info;
        this.tel = tel;
        this.address = address;
        this.zipCode = zipCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.monOpenTime = monOpenTime;
        this.monCloseTime = monCloseTime;
        this.tueOpenTime = tueOpenTime;
        this.tueCloseTime = tueCloseTime;
        this.wedOpenTime = wedOpenTime;
        this.wedCloseTime = wedCloseTime;
        this.thuOpenTime = thuOpenTime;
        this.thuCloseTime = thuCloseTime;
        this.friOpenTime = friOpenTime;
        this.friCloseTime = friCloseTime;
        this.satOpenTime = satOpenTime;
        this.satCloseTime = satCloseTime;
        this.sunOpenTime = sunOpenTime;
        this.sunCloseTime = sunCloseTime;
        this.holOpenTime = holOpenTime;
        this.holCloseTime = holCloseTime;
    }
}
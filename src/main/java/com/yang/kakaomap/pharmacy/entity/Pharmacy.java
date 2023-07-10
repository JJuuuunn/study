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
    private String info; // 상세설명 x
    private String tel; // 전화번호
    private String address; // 주소
    private String zipCode; // 우편번호 (postCdn1 + postCdn2)
    private String latitude; // 위도
    private String longitude; // 경도일

    // 요일별(+공휴일) 영엄시간 (오픈시간 ~ 마감시간)
    private String monOpenTime;
    private String monCloseTime;
    private String tueOpenTime;
    private String tueCloseTime;
    private String wedOpenTime;
    private String wedCloseTime;
    private String thuOpenTime;
    private String thuCloseTime;
    private String friOpenTime;
    private String friCloseTime;
    private String satOpenTime;
    private String satCloseTime;
    private String sunOpenTime;
    private String sunCloseTime;
    private String holOpenTime;
    private String holCloseTime;

    @Builder
    public Pharmacy(Long id, String hpId, String name, String info, String tel, String address, String zipCode, String latitude, String longitude, String monOpenTime, String monCloseTime, String tueOpenTime, String tueCloseTime, String wedOpenTime, String wedCloseTime, String thuOpenTime, String thuCloseTime, String friOpenTime, String friCloseTime, String satOpenTime, String satCloseTime, String sunOpenTime, String sunCloseTime, String holOpenTime, String holCloseTime) {
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
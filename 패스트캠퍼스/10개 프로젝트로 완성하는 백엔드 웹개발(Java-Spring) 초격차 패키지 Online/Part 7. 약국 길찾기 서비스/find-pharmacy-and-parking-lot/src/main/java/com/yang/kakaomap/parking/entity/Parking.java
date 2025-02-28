package com.yang.kakaomap.parking.entity;

import com.yang.kakaomap.common.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity(name = "parking")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parking extends BaseTimeEntity {

//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id; // 주차장 관리번호
//    private String parkingNo; // 주차장 관리번호 (prkplceNo)
//    private String parkingName; // 주차장 명 (prkplceNm)
//    private String stNameAddress; // 도로명주소 (rdnmadr)
//    private String rdNumAddress; // 지번주소 (lnmadr)
//    private String latitude; // 위도 (latitude)
//    private String longitude; // 경도 (longitude)
//    private String tel; // 전화번호 (phoneNumber)
//    private String numParking; // 주차구획수 (prkcmprt)
//    private String info; // 상세설명 (parkingchrgeInfo)
//
//    // 요금, 시간, 결제방법
//    private LocalTime basicTime; // 기본시간
//    private Integer basicCharge; // 기본요금
//    private LocalTime addUnitTime; // 추가단위시간
//    private Integer addUnitCharge; // 추가단위요금
//    private LocalTime dayTime; // 1일주차권요금적용시간
//    private Integer dayCharge; // 1일주차권요금
//    private Integer monthCharge; // 월정기권요금
//    private String payType; // 결제방법
//
//    // 영업요일, 평일/주말 영업시간 (오픈시간 ~ 마감시간)
//    private String operateDay;
//    private LocalTime weekdayOpenTime;
//    private LocalTime weekdayCloseTime;
//    private LocalTime satOpenTime;
//    private LocalTime satCloseTime;
//    private LocalTime holOpenTime;
//    private LocalTime holCloseTime;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 주차장 관리번호
    private String parkingNo; // 주차장 관리번호 (prkplceNo)
    private String parkingName; // 주차장 명 (prkplceNm)
    private String stNameAddress; // 도로명주소 (rdnmadr)
    private String rdNumAddress; // 지번주소 (lnmadr)
    private String latitude; // 위도 (latitude)
    private String longitude; // 경도 (longitude)
    private String tel; // 전화번호 (phoneNumber)
    private String numParking; // 주차구획수 (prkcmprt)
    private String info; // 상세설명 (parkingchrgeInfo)

    // 요금, 시간, 결제방법
    private String basicTime; // 기본시간
    private String basicCharge; // 기본요금
    private String addUnitTime; // 추가단위시간
    private String addUnitCharge; // 추가단위요금
    private String dayTime; // 1일주차권요금적용시간
    private String dayCharge; // 1일주차권요금
    private String monthCharge; // 월정기권요금
    private String payType; // 결제방법

    // 영업요일, 평일/주말 영업시간 (오픈시간 ~ 마감시간)
    private String operateDay;
    private String weekdayOpenTime;
    private String weekdayCloseTime;
    private String satOpenTime;
    private String satCloseTime;
    private String holOpenTime;
    private String holCloseTime;

    @Builder

    public Parking(Long id, String parkingNo, String parkingName, String stNameAddress, String rdNumAddress, String latitude, String longitude, String tel, String numParking, String info, String basicTime, String basicCharge, String addUnitTime, String addUnitCharge, String dayTime, String dayCharge, String monthCharge, String payType, String operateDay, String weekdayOpenTime, String weekdayCloseTime, String satOpenTime, String satCloseTime, String holOpenTime, String holCloseTime) {
        this.id = id;
        this.parkingNo = parkingNo;
        this.parkingName = parkingName;
        this.stNameAddress = stNameAddress;
        this.rdNumAddress = rdNumAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tel = tel;
        this.numParking = numParking;
        this.info = info;
        this.basicTime = basicTime;
        this.basicCharge = basicCharge;
        this.addUnitTime = addUnitTime;
        this.addUnitCharge = addUnitCharge;
        this.dayTime = dayTime;
        this.dayCharge = dayCharge;
        this.monthCharge = monthCharge;
        this.payType = payType;
        this.operateDay = operateDay;
        this.weekdayOpenTime = weekdayOpenTime;
        this.weekdayCloseTime = weekdayCloseTime;
        this.satOpenTime = satOpenTime;
        this.satCloseTime = satCloseTime;
        this.holOpenTime = holOpenTime;
        this.holCloseTime = holCloseTime;
    }
//    public Parking(Long id, String parkingNo, String parkingName, String stNameAddress, String rdNumAddress, String latitude, String longitude, String tel, String numParking, String info, LocalTime basicTime, Integer basicCharge, LocalTime addUnitTime, Integer addUnitCharge, LocalTime dayTime, Integer dayCharge, Integer monthCharge, String payType, String operateDay, LocalTime weekdayOpenTime, LocalTime weekdayCloseTime, LocalTime satOpenTime, LocalTime satCloseTime, LocalTime holOpenTime, LocalTime holCloseTime) {
//        this.id = id;
//        this.parkingNo = parkingNo;
//        this.parkingName = parkingName;
//        this.stNameAddress = stNameAddress;
//        this.rdNumAddress = rdNumAddress;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.tel = tel;
//        this.numParking = numParking;
//        this.info = info;
//        this.basicTime = basicTime;
//        this.basicCharge = basicCharge;
//        this.addUnitTime = addUnitTime;
//        this.addUnitCharge = addUnitCharge;
//        this.dayTime = dayTime;
//        this.dayCharge = dayCharge;
//        this.monthCharge = monthCharge;
//        this.payType = payType;
//        this.operateDay = operateDay;
//        this.weekdayOpenTime = weekdayOpenTime;
//        this.weekdayCloseTime = weekdayCloseTime;
//        this.satOpenTime = satOpenTime;
//        this.satCloseTime = satCloseTime;
//        this.holOpenTime = holOpenTime;
//        this.holCloseTime = holCloseTime;
//    }
}

package com.yang.kakaomap.parkingArea.entity;

import com.yang.kakaomap.common.entity.BaseTimeEntity;

import javax.persistence.*;

@Entity
public class ParkingArea extends BaseTimeEntity {

    @Id
    private String id; // 주차장 관리번호
    private String stNameAddress; // 도로명주소
    private String rdNumAddress; // 지번주소
    private double latitude; // 위도
    private double longitude; // 경도
    private String tel; // 전화번호
    private Integer prkcmprt; // 주차구획수
    private String info; // 상세설명

    // 요금, 시간, 결제방법
    private String basicTime; // 기본시간
    private Integer basicCharge; // 기본요금
    private String addUnitTime; // 추가단위시간
    private Integer addUnitCharge; // 추가단위요금
    private String dayCmmtktAdjTime; // 1일주차권요금적용시간
    private Integer dayCmmtkt; // 1일주차권요금
    private Integer monthCmmtkt; // 월정기권요금
    private String metpay; // 결제방법

    // 영업요일, 평일/주말 영업시간 (오픈시간 ~ 마감시간)
    private String operateDay;
    private String weekdayOpenTime;
    private String weekdayCloseTime;
    private String satOpenTime;
    private String satCloseTime;
    private String holOpenTime;
    private String holCloseTime;
    private String holidayTime;
}
// todo
// 잘못생각했다..... dto로 출력할때 할껄 entity에다가 저장 할려고 햇다... 나는 멍청이다.

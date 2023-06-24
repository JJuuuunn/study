package com.yang.kakaomap.pharmacy.entity;

import com.yang.kakaomap.common.entity.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pharmacy extends BaseTimeEntity {

    @Id
    private String id; // 기관 Id (hpId)
    private String name; // 이름
    private String info; // 상세설명
    private String tel; // 전화번호
    private String address; // 주소
    private String zipCode; // 우편번호 (postCdn1 + postCdn2)
    private double latitude; // 위도
    private double longitude; // 경도일

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
}
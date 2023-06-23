package com.yang.kakaomap.pharmacy.entity;

import javax.persistence.*;

@Entity
public class Pharmacy {

    @Id
    private String hpId; // 기관 Id
    private String name; // 이름
    private String info; // 상세설명
    private String tel; // 전화번호
    private String time; // 운영시간 (dutyTime1S + " ~ " + dutyTime1C + "\n" + ・・・・・・
    private String address; // 주소
    private String zipCode; // 우편번호 (postCdn1 + postCdn2)
    private double latitude; // 위도
    private double longitude; // 경도
}

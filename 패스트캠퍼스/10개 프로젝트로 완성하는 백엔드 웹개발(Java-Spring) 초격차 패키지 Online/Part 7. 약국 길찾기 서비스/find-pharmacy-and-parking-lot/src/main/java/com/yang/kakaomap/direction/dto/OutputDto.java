package com.yang.kakaomap.direction.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutputDto {

    private String name;            // 이름
    private String address;         // 주소
    private String directionUrl;    // 길안내 url
    private String roadViewUrl;     // 로드뷰 url
    private String distance;        // 고객 주소와 약국 주소의 거리
}
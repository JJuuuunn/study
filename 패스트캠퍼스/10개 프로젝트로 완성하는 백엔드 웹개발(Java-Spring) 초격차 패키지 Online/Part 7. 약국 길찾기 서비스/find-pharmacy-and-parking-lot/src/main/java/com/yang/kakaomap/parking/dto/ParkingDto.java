package com.yang.kakaomap.parking.dto;

import com.yang.kakaomap.parking.entity.Parking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingDto {
    private Long id;
    private String parkingName;
    private String parkingAddress;
    private double latitude;
    private double longitude;

    public static ParkingDto from(Parking parking) {
        return ParkingDto.builder()
                .id(parking.getId())
                .parkingName(parking.getParkingName())
                .parkingAddress(parking.getStNameAddress())
                .latitude(nullCheck(parking.getLatitude()))
                .longitude(nullCheck(parking.getLongitude()))
                .build();
    }

    private static double nullCheck(String column) {
        if (column == null || column.isEmpty()) return 0;
        else return Double.valueOf(column);
    }
}
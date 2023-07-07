package com.yang.kakaomap.common.controller;

import com.yang.kakaomap.parking.entity.Parking;
import com.yang.kakaomap.parking.service.ParkingParsingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParsingController {
    private final ParkingParsingService parkingParsingService;

    @PostMapping("/parsing/parking")
    public void parsingParking() throws Exception {
        parkingParsingService.save();
    }
}

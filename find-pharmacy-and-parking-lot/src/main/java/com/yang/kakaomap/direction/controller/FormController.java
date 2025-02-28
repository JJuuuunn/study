package com.yang.kakaomap.direction.controller;

import com.yang.kakaomap.direction.dto.InputDto;
import com.yang.kakaomap.parking.service.ParkingRecommendationService;
import com.yang.kakaomap.pharmacy.service.PharmacyRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class FormController {
    private final PharmacyRecommendationService pharmacyRecommendationService;
    private final ParkingRecommendationService parkingRecommendationService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostMapping("/search/pharmacy")
    public ModelAndView postPharmacyDirection(@ModelAttribute InputDto inputDto)  {

        System.out.println("pharmacy : " + inputDto.getAddress());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList",
                pharmacyRecommendationService.recommendPharmacyList(inputDto.getAddress()));

        return modelAndView;
    }

    @PostMapping("/search/parking")
    public ModelAndView postParkingDirection(@ModelAttribute InputDto inputDto)  {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList",
                parkingRecommendationService.recommendParkingList(inputDto.getAddress()));

        return modelAndView;
    }

//    @PostMapping("/search/parking")
//    public String postParkingDirection(@ModelAttribute InputDto inputDto)  {
//        return inputDto.getAddress();
//    }
//
//    @PostMapping("/search/pharmacy")
//    public String postPharmacyDirection(@ModelAttribute InputDto inputDto)  {
//        return inputDto.getAddress();
//    }
}
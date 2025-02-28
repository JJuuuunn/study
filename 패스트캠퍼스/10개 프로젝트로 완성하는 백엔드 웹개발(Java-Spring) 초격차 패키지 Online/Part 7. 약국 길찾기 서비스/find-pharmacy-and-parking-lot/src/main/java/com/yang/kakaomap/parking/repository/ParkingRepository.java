package com.yang.kakaomap.parking.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yang.kakaomap.parking.entity.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}
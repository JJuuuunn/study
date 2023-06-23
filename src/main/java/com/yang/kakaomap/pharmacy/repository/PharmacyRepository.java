package com.yang.kakaomap.pharmacy.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.yang.kakaomap.pharmacy.entity.Pharmacy;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
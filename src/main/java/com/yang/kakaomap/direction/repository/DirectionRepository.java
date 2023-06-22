package com.yang.kakaomap.direction.repository;

import com.yang.kakaomap.direction.entity.Direction;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
}
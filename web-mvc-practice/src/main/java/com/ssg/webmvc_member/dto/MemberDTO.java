package com.ssg.webmvc_member.dto;

import com.ssg.webmvc_member.vo.MemberVO;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberDTO(
        String id,
        String name,
        String email,
        String password,
        LocalDate createdAt,
        LocalDate modifiedAt
) {
    public static MemberDTO from(MemberVO vo) {
        return MemberDTO.builder()
                .id(vo.getId())
                .name(vo.getName())
                .email(vo.getEmail())
                .password(vo.getPassword())
                .createdAt(vo.getCreatedAt())
                .modifiedAt(vo.getModifiedAt())
                .build();
    }
}

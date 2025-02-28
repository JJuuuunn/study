package com.ssg.webmvc_member.vo;

import com.ssg.webmvc_member.dto.SignInDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberVO {
    private String id;
    private String password;
    private String name;
    private String email;
    private LocalDate createdAt;
    private LocalDate modifiedAt;
}

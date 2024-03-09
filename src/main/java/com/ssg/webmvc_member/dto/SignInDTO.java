package com.ssg.webmvc_member.dto;

import lombok.Builder;

@Builder
public record SignInDTO(
        String id,
        String password
) {
}

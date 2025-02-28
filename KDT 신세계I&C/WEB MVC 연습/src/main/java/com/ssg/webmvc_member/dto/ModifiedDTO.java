package com.ssg.webmvc_member.dto;

import lombok.Builder;

@Builder
public record ModifiedDTO(
        String id,
        String password,
        String name,
        String email
) {
}

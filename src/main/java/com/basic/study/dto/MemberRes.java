package com.basic.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberRes {
    @Schema(description = "회원의 id", example = "1")
    private Long memberId;
    @Schema(description = "회원의 email", example = "asdf")
    private String email;
}

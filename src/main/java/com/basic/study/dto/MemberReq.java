package com.basic.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원가입 및 로그인")
public class MemberReq {
    @Schema(description = "회원의 email", example = "asdf")
    private String email;
    @Schema(description = "회원의 password", example = "asdf")
    private String password;
}

package com.basic.study.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "todo 생성")
public class TodoReq {
    @Schema(description = "회원의 id", example = "1")
    private Long memberId;
    @Schema(description = "todo 내용", example = "할 일")
    private String content;
    @Schema(description = "todo 끝나는 날짜", example = "2024-12-25")
    private LocalDate deadLine;
}

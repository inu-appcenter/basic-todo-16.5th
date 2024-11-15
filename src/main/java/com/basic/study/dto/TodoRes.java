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
public class TodoRes {
    @Schema(description = "todo의 id", example = "1")
    private Long todoId;
    @Schema(description = "todo 내용", example = "할 일")
    private String content;
    @Schema(description = "todo 끝나는 날짜", example = "2024-12-25")
    private LocalDate deadLine;
    @Builder.Default
    @Schema(description = "todo 완료 여부 / 기본값 = false", example = "false")
    private Boolean isCompleted = false;
}

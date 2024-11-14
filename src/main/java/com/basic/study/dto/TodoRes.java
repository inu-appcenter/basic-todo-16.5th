package com.basic.study.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoRes {
    private Long memberId;
    private Long todoId;
    private String content;
    private LocalDate deadLine;
    @Builder.Default
    private Boolean isCompleted = false;
}

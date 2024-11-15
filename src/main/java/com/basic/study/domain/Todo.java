package com.basic.study.domain;

import com.basic.study.dto.TodoUpdateReq;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDate deadLine;

    private Boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Todo(String content, LocalDate deadLine, Member member) {
        this.content = content;
        this.deadLine = deadLine;
        this.isCompleted = false;
        this.member = member;
    }

    public void update(TodoUpdateReq todoReq) {
        this.content = todoReq.getContent();
        this.deadLine = todoReq.getDeadLine();
    }

    public void toggle() {
        this.isCompleted = !this.isCompleted;
    }
}


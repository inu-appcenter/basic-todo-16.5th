package com.basic.study.service;

import com.basic.study.domain.Todo;
import com.basic.study.dto.TodoReq;
import com.basic.study.dto.TodoRes;
import com.basic.study.dto.TodoUpdateReq;
import com.basic.study.repository.MemberRepository;
import com.basic.study.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    final private MemberRepository memberRepository;
    final private TodoRepository todoRepository;

    public TodoRes createTodo(TodoReq todoReq) {
        if(!memberRepository.existsById(todoReq.getMemberId())) throw new NullPointerException();
        Todo todo = Todo.builder()
                .content(todoReq.getContent())
                .deadLine(todoReq.getDeadLine())
                .member(memberRepository.findById(todoReq.getMemberId()).get())
                .build();
        todoRepository.save(todo);

        return TodoRes.builder()
                .todoId(todo.getId())
                .content(todo.getContent())
                .deadLine(todo.getDeadLine())
                .build();
    }

    public List<TodoRes> readTodos(Long memberId) {
        return todoRepository.findAllByMemberId(memberId).stream()
                .map(todo -> TodoRes.builder()
                        .todoId(todo.getId())
                        .content(todo.getContent())
                        .deadLine(todo.getDeadLine())
                        .isCompleted(todo.getIsCompleted())
                        .build()).toList();
    }

    public TodoRes updateTodo(Long todoId, TodoUpdateReq todoUpdateReq) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                NullPointerException::new
        );
        todo.update(todoUpdateReq);
        todoRepository.save(todo);
        return TodoRes.builder()
                .todoId(todo.getId())
                .content(todo.getContent())
                .deadLine(todo.getDeadLine())
                .isCompleted(todo.getIsCompleted())
                .build();
    }

    public TodoRes toggleTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                NullPointerException::new
        );
        todo.toggle();
        todoRepository.save(todo);
        return TodoRes.builder()
                .todoId(todo.getId())
                .content(todo.getContent())
                .deadLine(todo.getDeadLine())
                .isCompleted(todo.getIsCompleted())
                .build();
    }

    public boolean deleteTodo(Long todoId) {
        if (!todoRepository.existsById(todoId)) throw new NullPointerException();
        todoRepository.deleteById(todoId);
        return true;
    }
}

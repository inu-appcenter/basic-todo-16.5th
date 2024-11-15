package com.basic.study.controller;

import com.basic.study.dto.TodoReq;
import com.basic.study.dto.TodoRes;
import com.basic.study.dto.TodoUpdateReq;
import com.basic.study.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/todos")
@Tag(name = "Todo", description = "Todo API")
public class TodoController {
    final private TodoService todoService;

    @PostMapping("")
    @Operation(description = "todo 생성")
    @ApiResponse(responseCode = "200", description = "todo 생성 성공", content = @Content(schema = @Schema(implementation = TodoRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 memberId")
    public ResponseEntity<TodoRes> postTodo(@RequestBody TodoReq todoReq) {
        return ResponseEntity.ok(todoService.createTodo(todoReq));
    }

    @GetMapping("")
    @Operation(description = "todo 조회")
    @ApiResponse(responseCode = "200", description = "todo 조회 성공", content = @Content(schema = @Schema(implementation = TodoRes.class)))
    @ApiResponse(responseCode = "200", description = "생성된 todo 없음", content = @Content(schema = @Schema(type = "array")))
    public ResponseEntity<List<TodoRes>> getTodos(@RequestParam Long memberId) {
        return ResponseEntity.ok(todoService.readTodos(memberId));
    }

    @PutMapping("/{todoId}")
    @Operation(description = "todo 내용 및 기간 수정")
    @ApiResponse(responseCode = "200", description = "todo 수정 성공", content = @Content(schema = @Schema(implementation = TodoRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 todoId")
    public ResponseEntity<TodoRes> putTodo(@PathVariable Long todoId, @RequestBody TodoUpdateReq todoUpdateReq) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoUpdateReq));
    }

    @PatchMapping("/{todoId}")
    @Operation(description = "todo 완료 여부 수정")
    @ApiResponse(responseCode = "200", description = "todo 수정 성공", content = @Content(schema = @Schema(implementation = TodoRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 todoId")
    public ResponseEntity<TodoRes> patchTodo(@PathVariable Long todoId) {
        return ResponseEntity.ok(todoService.toggleTodo(todoId));
    }

    @DeleteMapping("/{todoId}")
    @Operation(description = "todo 삭제")
    @ApiResponse(responseCode = "200", description = "todo 삭제 성공", content = @Content(schema = @Schema(implementation = TodoRes.class)))
    @ApiResponse(responseCode = "500", description = "존재하지 않는 todoId")
    public ResponseEntity<String> deleteTodo(@PathVariable Long todoId) {
        return todoService.deleteTodo(todoId)
                ? ResponseEntity.ok("todo 삭제 성공")
                : ResponseEntity.ok("todo 삭제 실패");
    }

}

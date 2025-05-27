package com.catchme.todolist.controller;

import com.catchme.todolist.dto.ToDoDto;
import com.catchme.todolist.entity.ToDoEntity;
import com.catchme.todolist.service.ToDoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Controller + @ResponseBody가 합쳐진 버전
@RequiredArgsConstructor // final로 선언한 toDoservice의 생성자를 자동으로 주입해줌
@RequestMapping("/api/todos")
@Tag(name = "ToDo", description = "ToDo API")
public class ToDoController {

    private final ToDoService toDoService;

    @Operation(summary = "할 일 생성", description = "새로운 할 일을 생성합니다.")
    @PostMapping
    public ResponseEntity<ToDoEntity> createTodo(@RequestBody ToDoDto dto) { //사용자가 json 형식으로 보낸 데이터를 객체로 바꿔줌 / ResponseEntity - 상태 코드 + 데이터를 같이 보낼 수 있음
        ToDoEntity created = toDoService.createTodo(dto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "할 일 조회", description = "특정 날짜 기준 할 일의 목록을 조회합니다.")
    @GetMapping
    public List<ToDoDto> getToDoByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return toDoService.getTodosByDate(date);
    }

    @Operation(summary = "할 일 수정", description = "할 일을 수정합니다.")
    @PutMapping
    public ResponseEntity<ToDoEntity> updateTodo(@RequestBody ToDoDto dto) {
        ToDoEntity updated = toDoService.updateTodo(dto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "할 일 삭제", description = "새로운 할 일을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        toDoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
    //void - 응답 본문에 아무 내용이 없다.
    //noContent - 204(내용 없음) 반환 build - 응답 자체를 최종적으로 만들어줘~ 이 느

    @Operation(summary = "할 일 완료 상태 수정", description = "등록된 할 일의 완료여부를 수정합니다.")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleComplete(@PathVariable Long id) {
        toDoService.toggleTodoCompletion(id);
        return ResponseEntity.noContent().build();
    }
}

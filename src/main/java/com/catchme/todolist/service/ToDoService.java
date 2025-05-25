package com.catchme.todolist.service;


import com.catchme.todolist.dto.ToDoDto;
import com.catchme.todolist.entity.ToDoEntity;
import com.catchme.todolist.repository.ToDoRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoEntity createTodo(ToDoDto dto) {
        ToDoEntity todo = ToDoEntity.builder()
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .dueDate(dto.getDueDate())
                .isCompleted(false)
                .isDeleted(false)
                .build();
        return toDoRepository.save(todo);
    }

    public List<ToDoDto> getTodosByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<ToDoEntity> todos = toDoRepository.findByStartDateBetweenAndIsDeletedFalse(startOfDay,
                endOfDay);
        return todos.stream().map(ToDoDto::fromEntity).collect(Collectors.toList());

    }
//사용자가 입력하는 값은 dto.get으로 받음

    @Transactional
    public ToDoEntity updateTodo(ToDoDto dto) {
        ToDoEntity todo = toDoRepository.findById(dto.getId())
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다. id=" + dto.getId()));

        // service에서 entity 필드 직접 수정 x
        todo.update(dto.getTitle(), dto.getStartDate(), dto.getDueDate());
        return todo;
    }

    @Transactional
    public void deleteTodo(Long id){
        ToDoEntity todo = toDoRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당 할 일이 존재하지 않습니다. id=" + id));

        todo.softDelete();
    }

}



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
        List<ToDoEntity> todos = toDoRepository.findByStartDateAndIsDeletedFalse(date);
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
        toDoRepository.save(todo);
    }

    @Transactional //toDoRepository.save() 이런 코드를 쓰지 않아도 entity의 영속성을 이용하여 자동으로 저장되게 할 수 있도록 하는 애너테이션
    public void toggleTodoCompletion(Long id) {
        ToDoEntity todo = toDoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 할 일을 찾을 수 없습니다."));

        todo.toggleCompletion();
        toDoRepository.save(todo);
    }

}



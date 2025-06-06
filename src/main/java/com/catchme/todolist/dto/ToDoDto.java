package com.catchme.todolist.dto;


import com.catchme.todolist.entity.ToDoEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDoDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate  dueDate;
    private Boolean isCompleted;

    public static ToDoDto fromEntity(ToDoEntity entity) {
        return ToDoDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .startDate(entity.getStartDate())
                .dueDate(entity.getDueDate())
                .isCompleted(entity.getIsCompleted())
                .build();
    }
}

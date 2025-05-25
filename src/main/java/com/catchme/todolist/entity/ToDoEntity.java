package com.catchme.todolist.entity;

import com.catchme.todolist.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import lombok.experimental.SuperBuilder;

@Entity // JPA에서 DB 테이블과 매핑된다는 뜻
@Table(name = "todo") // 테이블 이름을 명시적으로 지정
@Getter
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자 자동 생성
@SuperBuilder
public class ToDoEntity extends BaseEntity {

    @Column(nullable = false, length = 255)
    private String title;
    // 제목은 null이 되면 안 되기 때문에 nullable = false
    // 최대 글자수 지정

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Column(name = "is_completed", nullable = false)
    private Boolean isCompleted = false;
    // 기본값 false로 설정

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
    // 소프트 삭제용 필드. 삭제 시 DB에서 지우는 대신 true로 바꿈
    // 기본값 false로 설정

    public void update(String title, LocalDateTime startDate, LocalDateTime dueDate) {
        this.title = title;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
}

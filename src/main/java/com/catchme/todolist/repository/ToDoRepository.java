package com.catchme.todolist.repository;

import com.catchme.todolist.entity.ToDoEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDoEntity, Long> {
    List<ToDoEntity> findByStartDateBetweenAndIsDeletedFalse(LocalDateTime startOfDay, LocalDateTime endOfDay);
    //AndIsDeletedFalse - 소프트 삭제 안 된 것만 찾기
    List<ToDoEntity> findByStartDateAndIsDeletedFalse(LocalDate date);

}
//JpaRepository<엔티  티, ID타입>을 상속하면 save(), findAll() 같은 CURD 함수들을 자동으로 쓸 수 있음.
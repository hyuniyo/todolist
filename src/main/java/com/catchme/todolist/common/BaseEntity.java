package com.catchme.todolist.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받는 클래스가 이 필드를 그대로 DB 테이블에 포함
@EntityListeners(AuditingEntityListener.class) // createdAt, updatedAt 자동 관리
@NoArgsConstructor // 파라미터 없는 기본 생성자 자동 생성
@AllArgsConstructor
@SuperBuilder
public abstract class BaseEntity { //abstract는 객체 생성이 안되고 하면 안되니까 BaseEntity는 abstract로 한다

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT처럼 자동 번호 증가
    private Long id;

    @CreatedDate
    @Column(name= "created_at", updatable = false) // 생성 시간은 수정 X
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

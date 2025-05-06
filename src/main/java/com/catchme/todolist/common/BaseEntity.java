package com.catchme.todolist.common;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 상속받는 클래스가 이 필드를 그대로 DB 테이블에 포함
@EntityListeners(AuditingEntityListener.class) // createdAt, updatedAt 자동 관리
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT처럼 자동 번호 증가
    private Long id;

    @CreatedDate
    @Column(updatable = false) // 생성 시간은 수정 X
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}

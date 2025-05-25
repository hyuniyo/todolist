package com.catchme.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //추가해야 BaseEntity 작동
public class TodolistApplication {
//실행하기 전에 작업관리자>mariaDB 중지해야함. 충돌나서 db 실행 안됨
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}

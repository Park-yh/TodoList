package com.example.todolist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String author;
    private String password;

    @ManyToOne
    @JoinColumn(name = "todolist_id", nullable = false)
    private TodoList todoList;

    public Comment(String content, String author, String password, TodoList todoList) {
        this.content = content;
        this.author = author;
        this.password = password;
        this.todoList = todoList;
    }
}
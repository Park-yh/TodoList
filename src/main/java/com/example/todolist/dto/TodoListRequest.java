package com.example.todolist.dto;

import lombok.Getter;

@Getter
public class TodoListRequest {
    private String title;
    private String content;
    private String author;
    private String password;
}
